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
import android.widget.EditText;
import android.widget.RadioButton;

import com.epicodus.hauntedhouse.R;
import com.epicodus.hauntedhouse.models.Item;

public class NewGameActivity extends AppCompatActivity {

    private EditText mNameText;
    private Button mStartButton;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        mNameText = (EditText) findViewById(R.id.nameText);
        mStartButton = (Button) findViewById(R.id.enterButton);
        mPreferences = getApplicationContext().getSharedPreferences("haunted_house", Context.MODE_PRIVATE);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameText.getText().toString();
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("player_name", name);
                editor.commit();
                Intent intent = new Intent(NewGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String item = "";

        switch (view.getId()) {
            case R.id.flashlightRadio:
                if (checked) {
                    item = "flashlight";
                }
                break;
            case R.id.weaponRadio:
                if (checked) {
                    item = "weapon";
                }
                break;
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("start_item", item);
        editor.commit();
    }
}
