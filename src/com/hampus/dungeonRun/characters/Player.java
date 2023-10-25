package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class Player extends ACharacter implements Serializable
{
    private int noOfKills = 0;
    public Player(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    public void setNoOfKills(int noOfKills)
    {
        this.noOfKills = noOfKills;
    }

    public int getNoOfKills()
    {
        return noOfKills;
    }
}
