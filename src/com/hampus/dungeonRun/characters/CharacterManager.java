package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class CharacterManager implements Serializable
{
    private Player player;
    private Monster monster;

    public CharacterManager(Player player)
    {
        this.player = player;
    }
    public CharacterManager(Monster monster){
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
