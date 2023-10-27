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
        String monsterName = characterManager.getMonster().getName();

        if (didDodge(characterManager))
        {
            System.out.printf("%s dodged the %s's attack!\n",characterManager.getPlayer().getName(), monsterName);
            return;
        }
        if (isItACrit(characterManager))
        {
            randomDamage = randomDamage * 2;
            if (randomDamage != 0)
            {
                System.out.printf("THE %s GOT A CRITICAL HIT ON YOU!\n",monsterName);
            }
        }
        System.out.printf("You took %d damage from the %s's attack\n", randomDamage,monsterName);
        characterManager.getPlayer().setHealth(characterManager.getPlayer().getHealth() - randomDamage);

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
