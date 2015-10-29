package com.epicodus.hauntedhouse.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.hauntedhouse.R;
import com.epicodus.hauntedhouse.models.Item;
import com.epicodus.hauntedhouse.models.Player;

public class BigRoomActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private Player mPlayer;
    private TextView mInventoryText;
    private TextView mRoomText;
    private ImageView mRoomImage;
    private Button mYesButton;
    private Button mNoButton;

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

        if (mPlayer.checkInventory("flashlight")) {
            mRoomText.setText("It is dark. Do you turn on your flashlight?");
            mYesButton.setVisibility(View.VISIBLE);
            mNoButton.setVisibility(View.VISIBLE);

            mYesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRoomImage.setImageResource(R.drawable.big_room);
                    mRoomText.setText("There is a dusty old couch, an antique piano, and sheet music scattering the floor.");
                    mYesButton.setVisibility(View.INVISIBLE);
                    mNoButton.setVisibility(View.INVISIBLE);
                }
            });
            mNoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRoomText.setText("You cannot see.");
                    mYesButton.setVisibility(View.INVISIBLE);
                    mNoButton.setVisibility(View.INVISIBLE);
                }
            });
        }

    }

//    private void displayRoomChoice() {
//        mRoomText.setText("something else");
//        mYesButton.setVisibility(View.INVISIBLE);
//        mNoButton.setVisibility(View.INVISIBLE);
//    }
}
