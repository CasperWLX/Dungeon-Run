package com.hampus.dungeonRun.shop_logic;

import java.io.Serializable;

/**
 * A class which contains all necessary info of any item
 */
public class Item implements Serializable
{
    private String name;
    private int value;
    private String description;
    private int cost;
    private int stockAmount;
    private int itemID;

    public Item(String name, int value, String description, int cost, int stock)
    {
        this.name = name;
        this.value = value;
        this.description = description;
        this.cost = cost;
        this.stockAmount = stock;
    }

    public String getName()
    {
        return name;
    }

    public int getValue()
    {
        return value;
    }

    public String getDescription()
    {
        return description;
    }

    public int getCost()
    {
        return cost;
    }

    public int getStockAmount()
    {
        return stockAmount;
    }

    public void boughtItem()
    {
        stockAmount--;
    }

    public void setStockAmount(int stockAmount)
    {
        this.stockAmount = stockAmount;
    }

    public int getItemID()
    {
        return itemID;
    }

    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }
}
