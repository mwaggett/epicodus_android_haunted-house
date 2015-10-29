package com.epicodus.hauntedhouse.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.hauntedhouse.R;
import com.epicodus.hauntedhouse.models.Player;

public class BigRoomActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private Player mPlayer;
    private TextView mInventoryText;
    private TextView mRoomText;
    private ImageView mRoomImage;
    private Button mYesButton;
    private Button mNoButton;
    private Button mHallwayButton;
    private Button mStairsButton;
    private Button mExploreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_room);

        mPreferences = getApplicationContext().getSharedPreferences("haunted_house", Context.MODE_PRIVATE);
        mPlayer = Player.find(mPreferences.getString("player_name", null));

        mInventoryText = (TextView) findViewById(R.id.inventory);
        mInventoryText.setText("Inventory: " + mPlayer.inventoryString());

        mRoomText = (TextView) findViewById(R.id.roomText);
        mRoomImage = (ImageView) findViewById(R.id.roomImage);
        mYesButton = (Button) findViewById(R.id.yesButton);
        mNoButton = (Button) findViewById(R.id.noButton);
        mHallwayButton = (Button) findViewById(R.id.hallwayButton);
        mStairsButton = (Button) findViewById(R.id.roomToStairsButton);
        mExploreButton = (Button) findViewById(R.id.exploreRoomButton);

        if (mPlayer.checkInventory("flashlight")) {
            mRoomText.setText("It is dark. Do you turn on your flashlight?");
            toggleButtonVisibility();

            mYesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRoomImage.setImageResource(R.drawable.big_room);
                    mRoomText.setText("There is a dusty old couch, an antique piano, and sheet music scattering the floor.");
                    toggleButtonVisibility();
                }
            });
            mNoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRoomText.setText("You cannot see.");
                    toggleButtonVisibility();
                }
            });
        }

        mHallwayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BigRoomActivity.this, HallwayActivity.class);
                startActivity(intent);
            }
        });

        mStairsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BigRoomActivity.this, StaircaseActivity.class);
                startActivity(intent);
            }
        });

    }

    private void toggleButtonVisibility() {
        if (mYesButton.getVisibility()==View.VISIBLE) {
            mYesButton.setVisibility(View.INVISIBLE);
            mNoButton.setVisibility(View.INVISIBLE);
            mHallwayButton.setVisibility(View.VISIBLE);
            mStairsButton.setVisibility(View.VISIBLE);
            mExploreButton.setVisibility(View.VISIBLE);
        } else {
            mYesButton.setVisibility(View.VISIBLE);
            mNoButton.setVisibility(View.VISIBLE);
            mHallwayButton.setVisibility(View.INVISIBLE);
            mStairsButton.setVisibility(View.INVISIBLE);
            mExploreButton.setVisibility(View.INVISIBLE);
        }
    }
}
