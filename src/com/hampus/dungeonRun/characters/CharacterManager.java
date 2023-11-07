package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.shop_logic.Shop;

import java.io.Serializable;

public class CharacterManager implements Serializable
{
    private final Player PLAYER;
    private final Monster MONSTER;
    private final Shop SHOP;

    public CharacterManager(Player player, Monster monster, Shop shop)
    {
        this.PLAYER = player;
        this.MONSTER = monster;
        this.SHOP = shop;
    }

    public Player getPLAYER()
    {
        return PLAYER;
    }

    public Monster getMONSTER()
    {
        return MONSTER;
    }

    public Shop getSHOP()
    {
        return SHOP;
    }
}
