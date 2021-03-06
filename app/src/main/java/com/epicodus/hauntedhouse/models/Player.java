package com.epicodus.hauntedhouse.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

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

    public List<Item> getItems() {
        return new Select().from(Item.class).where("Carrier = ?", this.getId()).execute();
    }

    public String inventoryString() {
        String inventory = "";
        for (int index = 0; index < this.getItems().size(); index++) {
            inventory = inventory + this.getItems().get(index).getType();
            if (index < this.getItems().size()-1) {
                inventory = inventory + ", ";
            }
        }
        return inventory;
    }

    public boolean checkInventory(String itemType) {
        for (Item item : this.getItems()) {
            if (item.getType().equals(itemType)) {
                return true;
            }
        }
        return false;
    }

    public static Player find(String name) {
        return new Select().from(Player.class).where("Name = ?", name).executeSingle();
    }
}
