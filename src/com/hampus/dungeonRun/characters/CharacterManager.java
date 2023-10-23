package com.hampus.dungeonRun.characters;

public class CharacterManager
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
