package com.hampus.dungeonRun.shop_logic;

import java.io.Serializable;

/**
 * A weapon class to keep track of weapons
 */
public class Weapon extends Item implements Serializable
{
    public Weapon(String name, int damage, String description, int cost, int itemsInStock)
    {
        super(name, damage, description, cost, itemsInStock);
    }
}
