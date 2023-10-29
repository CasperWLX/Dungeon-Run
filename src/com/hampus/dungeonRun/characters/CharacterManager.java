package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class CharacterManager implements Serializable
{
    private final Player PLAYER;
    private final Monster MONSTER;

    public CharacterManager(Player player, Monster monster)
    {
        this.PLAYER = player;
        this.MONSTER = monster;
    }

    public Player getPLAYER()
    {
        return PLAYER;
    }

    public Monster getMONSTER()
    {
        return MONSTER;
    }
}
