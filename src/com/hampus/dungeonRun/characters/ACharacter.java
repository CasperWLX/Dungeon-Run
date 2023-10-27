package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.ICombat;
import com.hampus.dungeonRun.logic.Menu;

import java.io.Serializable;

public abstract class ACharacter implements ICombat, Serializable
{
    private String name;
    private int health;
    private int maxHealth = 100;
    private int strength;
    private int agility;
    private int requiredExperience = 50;
    private int experience;
    private int level;
    private int gold;
    private int criticalRate;


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

    public String getStats()
    {
        return String.format("|\tHP: %d\t|\tStr: %d\t|\tAgility: %d\t|\tEXP: %d/%d\t|\tLVL: %d\t|\tGold: %d\t|\tCrit: %d%%\t|\n",
                health,strength,agility,experience,requiredExperience,level,gold,criticalRate);
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

    @Override
    public void levelUp(Menu menu, CharacterManager characterManager)
    {
        experience += characterManager.getMonster().getExperience();
        gold += characterManager.getMonster().getGold();
        if(experience >= requiredExperience)
        {
            level++;
            maxHealth+= (int) (maxHealth * 0.05);
            health = maxHealth;
            experience -= requiredExperience;
            requiredExperience += (int)(requiredExperience * 0.05);
            strength+= (int)(strength * 0.1);
            if(level % 10 == 0)
            {
                agility++;
            }
            if(level % 5 == 0)
            {
                criticalRate++;
            }
            menu.levelUpMessage(characterManager);
        }
    }
}
