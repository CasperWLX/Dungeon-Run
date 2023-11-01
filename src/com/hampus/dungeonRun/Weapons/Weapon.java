package com.hampus.dungeonRun.Weapons;

import com.hampus.dungeonRun.shop_logic.Item;

import java.io.Serializable;

public class Weapon extends Item implements Serializable
{
    public Weapon(String name, int damage, String description, int cost, int itemsInStock)
    {
        super(name,damage,description, cost, itemsInStock);
    }
}
