package com.hampus.dungeonRun.shop_logic;

import java.io.Serializable;

/**
 * A potion class to keep track of potions
 */
public class Potion extends Item implements Serializable
{
    public Potion(String name, int value, String description, int cost, int itemsInStock)
    {
        super(name, value, description, cost, itemsInStock);
    }
}
