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

    @Override
    public void takeDamage(CharacterManager characterManager)
    {
        int randomDamage = characterManager.getMonster().getStrength() + (int) (Math.random() * 3);

        if (didDodge(characterManager))
        {
            System.out.println("The player dodged!");
            return;
        }
        if (isItACrit(characterManager))
        {
            randomDamage = randomDamage * 2;
            if (randomDamage != 0)
            {
                System.out.println("THE MONSTER GOT A CRITICAL HIT ON YOU!");
            }
        }
        System.out.printf("You took %d damage from the monsters attack\n", randomDamage);
        characterManager.getPlayer().setHealth(characterManager.getPlayer().getHealth() - randomDamage);

    }

    @Override
    public void levelUp()
    {

    }

    public boolean isItACrit(CharacterManager characterManager)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < characterManager.getPlayer().getCriticalRate();
    }

    public boolean didDodge(CharacterManager characterManager)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < characterManager.getPlayer().getAgility();
    }
}
