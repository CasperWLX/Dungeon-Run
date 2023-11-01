package com.hampus.dungeonRun.shop_logic;

import java.io.Serializable;

public class Item implements Serializable
{
    private final String NAME;
    private final int VAlUE;
    private final String DESCRIPTION;
    private final int COST;
    private int STOCK_AMOUNT;

    public Item(String name, int value, String description, int cost, int stock)
    {
        this.NAME = name;
        this.VAlUE = value;
        this.DESCRIPTION = description;
        this.COST = cost;
        this.STOCK_AMOUNT = stock;
    }

    public String getNAME()
    {
        return NAME;
    }

    public int getVAlUE()
    {
        return VAlUE;
    }

    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }

    public int getCOST()
    {
        return COST;
    }

    public int getSTOCK_AMOUNT()
    {
        return STOCK_AMOUNT;
    }
    public void boughtItem()
    {
        STOCK_AMOUNT--;
    }
}
