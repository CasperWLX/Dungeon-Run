package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.ICombat;
import com.hampus.dungeonRun.logic.Menu;

import java.io.Serializable;

public abstract class ACharacter implements ICombat, Serializable
{
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

    public void setStats(String name, int maxHealth, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
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
                MENU.printGreen(String.valueOf(health)),
                MENU.printGreen(String.valueOf(maxHealth)),
                MENU.printRed(String.valueOf(strength)),
                MENU.printBlue(String.valueOf(agility)),
                MENU.printWhite(String.valueOf(experience)),
                MENU.printWhite(String.valueOf(requiredExperience)),
                MENU.printWhite(String.valueOf(level)),
                MENU.printYellow(String.valueOf(gold)),
                MENU.printRed(String.valueOf(criticalRate)));
    }
    public boolean isItACriticalHit(int criticalRate)
    {
        int randomizer = (int)(Math.random() * 100 + 1);

        return randomizer < criticalRate;
    }
    public boolean didDodge(int multiplier)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < agility * multiplier;
    }

    public void levelUp(Menu menu, Player player, Monster monster)
    {
        experience += monster.getExperience();
        gold += monster.getGold();
        while(experience >= requiredExperience)
        {
            increaseStats();
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
        MENU.levelUpMessage(getStats(),level);
        if(level == 10 || level == 20 || level == 30)
        {
            itIsBossTime();
        }

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

}
