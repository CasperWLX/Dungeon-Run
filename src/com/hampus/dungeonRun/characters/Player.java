package com.hampus.dungeonRun.characters;

public class Player extends ACharacter
{
    private int noOfKills;
    public Player(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }


    @Override
    public void fight()
    {
        System.out.println("ARRRRRRRGH!");
    }
}
