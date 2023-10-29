package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class Monster extends ACharacter implements Serializable
{
    public Monster(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    @Override
    public void takeDamage(CharacterManager characterManager)
    {
        int randomDamage = characterManager.getPLAYER().getStrength() + (int)(Math.random() * 3);

        if(didDodge())
        {
            System.out.printf("The %s dodged your attack!\n",super.getName());
            return;
        }
        if(isItACrit(characterManager))
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

    @Override
    public boolean isItACrit(CharacterManager characterManager)
    {
        int randomizer = (int)(Math.random() * 100 + 1);

        return randomizer < characterManager.getPLAYER().getCriticalRate();
    }

    @Override
    public boolean didDodge()
    {
        int randomizer = (int)(Math.random() * 100 + 1);

        return randomizer < super.getAgility();
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
            randomNumber = (int)((Math.random() * (3-1)) + 1);
        }
        return randomNumber;
    }
}
