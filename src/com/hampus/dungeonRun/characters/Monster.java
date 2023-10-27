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
        int randomDamage = characterManager.getPlayer().getStrength() + (int)(Math.random() * 3);
        String monsterName = characterManager.getMonster().getName();

        if(didDodge(characterManager))
        {
            System.out.printf("The %s dodged your attack!\n",monsterName);
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
        System.out.printf("You did %d damage against the %s\n", randomDamage,monsterName);
        characterManager.getMonster().setHealth(characterManager.getMonster().getHealth() - randomDamage);
    }

    @Override
    public boolean isItACrit(CharacterManager characterManager)
    {
        int randomizer = (int)(Math.random() * 100 + 1);

        return randomizer < characterManager.getMonster().getCriticalRate();
    }

    @Override
    public boolean didDodge(CharacterManager characterManager)
    {
        int randomizer = (int)(Math.random() * 100 + 1);

        return randomizer < characterManager.getMonster().getAgility();
    }
}
