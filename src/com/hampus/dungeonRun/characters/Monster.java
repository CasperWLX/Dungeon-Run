package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class Monster extends ACharacter implements Serializable
{
    public Monster(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    @Override
    public void fight()
    {
        System.out.println("GROOOOOWL!");
    }
}
