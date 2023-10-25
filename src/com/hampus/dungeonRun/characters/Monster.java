package com.hampus.dungeonRun.characters;

import java.io.Serializable;

public class Monster extends ACharacter implements Serializable
{
    public Monster(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    @Override
    public void scream()
    {
        System.out.println("GROOOOOWL!");
    }

    @Override
    public void takeDamage(CharacterManager characterManager)
    {
        int randomDamage = characterManager.getPlayer().getStrength() + (int)(Math.random() * 3);

        if(didDodge(characterManager))
        {
            System.out.println("The monster dodged!");
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
        System.out.printf("You did %d damage against the monster\n", randomDamage);
        characterManager.getMonster().setHealth(characterManager.getMonster().getHealth() - randomDamage);
    }

    @Override
    public void levelUp()
    {

    }

    @Override
    public boolean isItACrit(CharacterManager characterManager)
    {
        int randomizer = (int)(Math.random() * 100 + 1);
        if(randomizer < characterManager.getMonster().getCriticalRate())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean didDodge(CharacterManager characterManager)
    {
        int randomizer = (int)(Math.random() * 100 + 1);
        if(randomizer < characterManager.getMonster().getAgility())
        {
            return true;
        }
        return false;
    }
}
