package com.epicodus.hauntedhouse.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.hauntedhouse.R;
import com.epicodus.hauntedhouse.models.Item;
import com.epicodus.hauntedhouse.models.Player;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private Player mPlayer;
    private Item mStartItem;
    private TextView mInventoryText;
    private Button mRoomButton;
    private Button mHallwayButton;
    private Button mStaircaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("haunted_house", Context.MODE_PRIVATE);

        if (!inProgress()) {
            Intent intent = new Intent(this, NewGameActivity.class);
            startActivity(intent);
        }

        mInventoryText = (TextView) findViewById(R.id.inventory);
        mInventoryText.setText("Inventory: " + inventoryString());

        mRoomButton = (Button) findViewById(R.id.roomButton);
        mHallwayButton = (Button) findViewById(R.id.hallwayButton);
        mStaircaseButton = (Button) findViewById(R.id.staircaseButton);

        mRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BigRoomActivity.class);
                startActivity(intent);
            }
        });

        mHallwayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HallwayActivity.class);
                startActivity(intent);
            }
        });

        mStaircaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaircaseActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean inProgress() {
        String playerName = mPreferences.getString("player_name", null);
        if (playerName == null) {
            return false;
        } else {
            setPlayer(playerName);
            Toast.makeText(this, "Welcome, " + mPlayer.getName(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "You are carrying a " + mStartItem.getType(), Toast.LENGTH_LONG).show();
            return true;
        }
    }

    private void setPlayer(String playerName) {
        Player player = Player.find(playerName);
        if (player != null) {
            mPlayer = player;
            mStartItem = player.getItems().get(0);
        } else {
            mPlayer = new Player(playerName);
            mPlayer.save();
            addStartItem();
        }
    }

    private void addStartItem() {
        String startItem = mPreferences.getString("start_item", "nothing");
        mStartItem = new Item(startItem, mPlayer);
        mStartItem.save();
    }

    private String inventoryString() {
        String inventory = "";
        for (int index = 0; index < mPlayer.getItems().size(); index++) {
            inventory = inventory + mPlayer.getItems().get(index).getType();
            if (index < mPlayer.getItems().size()-1) {
                inventory = inventory + ", ";
            }
        }
        return inventory;
    }
}
