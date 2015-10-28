package com.epicodus.hauntedhouse.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items", id = "_id")
public class Item extends Model {

    @Column(name = "Type")
    private String mType;

    @Column(name = "Carrier")
    private Player mCarrier;

    public Item() {
        super();
    }

    public Item(String type, Player carrier) {
        mType = type;
        mCarrier = carrier;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public Player getCarrier() {
        return mCarrier;
    }

    public void setCarrier(Player carrier) {
        mCarrier = carrier;
    }
}
