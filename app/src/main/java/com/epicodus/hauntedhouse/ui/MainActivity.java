package com.epicodus.hauntedhouse.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.epicodus.hauntedhouse.R;
import com.epicodus.hauntedhouse.models.Item;
import com.epicodus.hauntedhouse.models.Player;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private Player mPlayer;
    private Item mStartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("haunted_house", Context.MODE_PRIVATE);


        if (!inProgress()) {
            Intent intent = new Intent(this, NewGameActivity.class);
            startActivity(intent);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
