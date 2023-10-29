package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class Player extends ACharacter implements Serializable
{
    private int noOfKills = 0;

    public Player(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    public void killedMonster()
    {
        noOfKills++;
    }

    public int getNoOfKills()
    {
        return noOfKills;
    }

    @Override
    public void takeDamage(CharacterManager characterManager)
    {
        int randomDamage = characterManager.getMONSTER().getStrength() + (int) (Math.random() * 3);
        String monsterName = characterManager.getMONSTER().getName();

        if(didDodge())
        {
            System.out.printf("%s dodged the %s's attack!\n", super.getName(), monsterName);
            return;
        }
        if(IsItACriticalHit(characterManager))
        {
            randomDamage = randomDamage * 2;
            if(randomDamage != 0)
            {
                System.out.printf("THE %s GOT A CRITICAL HIT ON YOU!\n", monsterName);
            }
        }
        System.out.printf("You took %d damage from the %s's attack\n", randomDamage, monsterName);
        super.setHealth(super.getHealth() - randomDamage);

    }

    public boolean IsItACriticalHit(CharacterManager characterManager)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < characterManager.getMONSTER().getCriticalRate();
    }

    public boolean didDodge()
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < super.getAgility();
    }

    public void heal(int healValue, int cost)
    {
        super.setHealth(super.getHealth() + healValue);
        super.setGold(super.getGold() - cost);
        if(super.getHealth() > super.getMaxHealth())
        {
            super.setHealth(super.getMaxHealth());
        }
    }
}
