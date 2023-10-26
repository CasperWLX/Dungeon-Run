package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.ICombat;

import java.io.Serializable;

public abstract class ACharacter implements ICombat, Serializable
{
    private int health;
    private int strength;
    private int agility;
    private int experience;
    private int level;
    private int gold;
    private int criticalRate;
    private String name;

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
                "|\tHP: %d\t|\tStr: %d\t|\tAgility: %d\t|\tEXP: %d\t|\tLVL: %d\t|\tGold: %d\t|\tCrit: %d%%\t|\n",
                health,strength,agility,experience,level,gold,criticalRate);
    }
    public void setStats(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.experience = experience;
        this.level = level;
        this.gold = gold;
        this.criticalRate = criticalRate;

    }



    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getAgility()
    {
        return agility;
    }

    public void setAgility(int agility)
    {
        this.agility = agility;
    }

    public int getExperience()
    {
        return experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public int getCriticalRate()
    {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate)
    {
        this.criticalRate = criticalRate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
