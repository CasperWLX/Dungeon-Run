package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class CharacterManager implements Serializable
{
    private Player player;
    private Monster monster;

    public CharacterManager(Player player, Monster monster)
    {
        this.player = player;
        this.monster = monster;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Monster getMonster()
    {
        return monster;
    }

}
