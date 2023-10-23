package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.ICombat;

public abstract class ACharacter implements ICombat
{
    private int health;
    private int strength;
    private int agility;
    private int experience;
    private int level;
    private int gold;
    private int criticalRate;
    Player player;
    Monster monster;

    public ACharacter(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.experience = experience;
        this.level = level;
        this.gold = gold;
        this.criticalRate = criticalRate;
    }

    public void getStats()
    {
        System.out.printf(
                "|\tHP: %d\t|\tStr: %d\t|\tAgility: %d\t|\tEXP: %d\t|\tLVL: %d\t|\tGold: %d\t|\tCrit: %d%%|\t\n",
                health,strength,agility,experience,level,gold,criticalRate);
    }
}
