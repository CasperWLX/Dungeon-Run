package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class Monster extends ACharacter implements Serializable
{
    public Monster(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    public void takeDamage(Player player)
    {
        int randomDamage = player.getStrength() + (int)(Math.random() * 3);
        try
        {
            randomDamage += player.getWeapon().getVAlUE();
        }catch(NullPointerException ignored)
        {

        }

        if(didDodge(1))
        {
            System.out.printf("The %s dodged your attack!\n",super.getName());
            return;
        }
        if(isItACriticalHit(player.getCriticalRate()))
        {
            randomDamage = randomDamage* 2;
            if(randomDamage != 0)
            {
                System.out.println("NICE! THAT'S A CRITICAL HIT!");
            }
        }
        System.out.printf("You did %d damage against the %s\n", randomDamage,super.getName());
        super.setHealth(super.getHealth() - randomDamage);
    }

    public void generateMonster(Player player, double statsMultiplier, int gold, int criticalHitRate, String name)
    {
        super.setName(name);
        super.setHealth(generateNumberInRange((int) (player.getMaxHealth() * statsMultiplier)));
        super.setMaxHealth(super.getHealth());
        super.setStrength(generateNumberInRange((int) (player.getStrength() * statsMultiplier)));
        super.setAgility(generateNumberInRange((int) (player.getAgility() * statsMultiplier)));
        super.setLevel(generateNumberInRange((int) (player.getLevel() * statsMultiplier)));
        super.setExperience(generateNumberInRange((int) (super.getLevel() * 6.3)));
        super.setRequiredExperience(super.getExperience());
        super.setGold(generateNumberInRange(gold));
        super.setCriticalRate(generateNumberInRange(criticalHitRate));

        if(super.getAgility() > 8)
        {
            super.setAgility(8);
        }
    }
    public int generateNumberInRange(int baseValue)
    {
        double lowerPercent = 0.8;
        double upperPercent = 1.2;
        int lowerBound = (int)(baseValue * lowerPercent);
        int upperBound = (int)(baseValue * upperPercent);
        int randomNumber = (int)((Math.random() * (upperBound - lowerBound)) + lowerBound);
        if(randomNumber == 1)
        {
            randomNumber = (int)((Math.random() * (5-1)) + 1);
        }
        return randomNumber;
    }
    public void createBoss(Player player)
    {
        switch(player.getLevel())
        {
            case 10 -> super.setStats("Orc", 120, 8, 10, 100, 12, 100, 7);
            case 20 -> super.setStats("Ogre", 250, 15, 0, 400, 25, 300, 5);
            case 30 -> super.setStats("Giant", 500, 25, 0, 3000, 40, 1000, 0);
        }
    }
}
