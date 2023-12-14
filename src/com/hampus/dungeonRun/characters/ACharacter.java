package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.Colorize;
import com.hampus.dungeonRun.logic.ICombat;
import com.hampus.dungeonRun.logic.Menu;

import java.io.Serializable;

/**
 * An abstract class which defines player and monster methods/variables
 */
public abstract class ACharacter implements ICombat, Serializable
{
    private final Colorize COLORIZE = new Colorize();
    private final Menu MENU = new Menu();
    private String name;
    private int health;
    private int maxHealth;
    private int strength;
    private int agility;
    private int requiredExperience = 50;
    private int experience;
    private int level;
    private int gold;
    private int criticalRate;
    private boolean bossTime = false;
    private int id;


    public ACharacter(int maxHealth, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.agility = agility;
        this.experience = experience;
        this.level = level;
        this.gold = gold;
        this.criticalRate = criticalRate;
    }
    public ACharacter(){

    }

    public void setStats(String name, int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.experience = experience;
        this.level = level;
        this.gold = gold;
        this.criticalRate = criticalRate;
    }

    public String getStats()
    {
        return String.format("|\tHP: %s/%s\t|\tStr: %s\t|\tAgility: %s\t|\tEXP: %s/%s\t|\tLVL: %s\t|\tGold: %s\t|\tCrit: %s%%\t|\n",
                COLORIZE.printGreen(String.valueOf(health)),
                COLORIZE.printGreen(String.valueOf(maxHealth)),
                COLORIZE.printRed(String.valueOf(strength)),
                COLORIZE.printBlue(String.valueOf(agility)),
                COLORIZE.printWhite(String.valueOf(experience)),
                COLORIZE.printWhite(String.valueOf(requiredExperience)),
                COLORIZE.printWhite(String.valueOf(level)),
                COLORIZE.printYellow(String.valueOf(gold)),
                COLORIZE.printRed(String.valueOf(criticalRate)));
    }

    public boolean isItACriticalHit(int criticalRate)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < criticalRate;
    }

    public boolean didDodge(int multiplier)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < agility * multiplier;
    }

    public void levelUp(Monster monster)
    {
        experience += monster.getExperience();
        gold += monster.getGold();
        while(experience >= requiredExperience)
        {
            increaseStats();
            if(level == 10 || level == 20 || level == 30)
            {
                itIsBossTime();
            }
        }
    }

    public void increaseStats()
    {
        level++;
        maxHealth += (int) (maxHealth * 0.05);
        health = maxHealth;
        experience -= requiredExperience;
        requiredExperience += (int) (requiredExperience * 0.15);
        strength += (int) (strength * 0.02 + 1);
        if(level % 5 == 0)
        {
            agility++;
        }
        if(level % 5 == 0)
        {
            criticalRate++;
        }
        MENU.levelUpMessage(getStats(), level);
    }

    public int randomizeStats(int baseValue)
    {
        double lowerPercent = 0.8;
        double upperPercent = 1.2;
        int lowerBound = (int) (baseValue * lowerPercent);
        int upperBound = (int) (baseValue * upperPercent);
        int randomNumber = (int) ((Math.random() * (upperBound - lowerBound)) + lowerBound);
        if(randomNumber == 1)
        {
            randomNumber = (int) ((Math.random() * (5 - 1)) + 1);
        }
        return randomNumber;
    }

    public void itIsBossTime()
    {
        bossTime = true;
        MENU.incomingBossBattle();
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
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

    public void setRequiredExperience(int requiredExperience)
    {
        this.requiredExperience = requiredExperience;
    }

    public int getRequiredExperience()
    {
        return requiredExperience;
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


    public boolean itsBossTime()
    {
        return bossTime;
    }

    public void setBossTime(boolean bossTime)
    {
        this.bossTime = bossTime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
