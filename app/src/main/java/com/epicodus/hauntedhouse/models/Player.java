package com.epicodus.hauntedhouse.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Players", id = "_id")
public class Player extends Model {

    @Column(name = "Name")
    private String mName;

    public Player() {
        super();
    }

    public Player(String name) {
        super();
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public static Player find(String name) {
        return new Select().from(Player.class).where("Name = ?", name).executeSingle();
    }
}