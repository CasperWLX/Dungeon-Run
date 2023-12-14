package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.dbLogic.MonsterDAO;
import com.hampus.dungeonRun.logic.Colorize;
import com.hampus.dungeonRun.logic.Menu;
import com.hampus.dungeonRun.shop_logic.Item;

import java.io.Serializable;

/**
 * A class which creates Monsters for the game
 */
public class Monster extends ACharacter implements Serializable
{
    private final Menu MENU = new Menu();
    private final Colorize COLORIZE = new Colorize();

    public Monster(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }
    public Monster(){}

    public void calculateDamage(int playerStrength, int criticalRate, Item weapon, String playerName)
    {
        int randomDamage = randomizeStats(playerStrength);
        try
        {
            randomDamage += weapon.getValue();
        }
        catch(NullPointerException ignored)
        {

        }
        takeDamage(randomDamage, super.getName(), playerName, criticalRate);
    }

    public void takeDamage(int damage, String characterTakingDamage, String characterDealingDamage, int opponentCritRate)
    {
        if(super.didDodge(1))
        {
            MENU.characterDodged(characterTakingDamage, characterDealingDamage);
            return;
        }
        if(super.isItACriticalHit(opponentCritRate))
        {
            damage = damage * 2;
            MENU.characterGotACrit(characterDealingDamage, characterTakingDamage);
        }
        MENU.monsterTookDamage(COLORIZE.printBlue(characterDealingDamage), COLORIZE.printRed(String.valueOf(damage)), COLORIZE.printBlue(characterTakingDamage));
        setHealth(getHealth() - damage);
    }

    /**
     * Method that generates a monster based on the players stats
     *
     * @param player          - The current player
     * @param statsMultiplier - A stats multiplier that dictates the range of stats the monster can have
     * @param goldMultiplier  - A multiplier that dictates the amount of gold a player can get from defeating the monster
     * @param criticalHitRate - The chance for the monster to land a critical hit
     * @param name            - Name of the monster
     */
    public void generateMonster(Player player, double statsMultiplier, int goldMultiplier, int criticalHitRate, String name)
    {
        super.setName(name);
        super.setHealth(randomizeStats((int) (player.getMaxHealth() * statsMultiplier)));
        super.setMaxHealth(super.getHealth());
        super.setStrength(randomizeStats((int) (player.getStrength() * statsMultiplier)));
        super.setAgility(randomizeStats((int) (player.getAgility() * statsMultiplier)));
        super.setLevel(randomizeStats((int) (player.getLevel() * statsMultiplier)));
        super.setExperience(randomizeStats((int) (super.getLevel() * 8.3)));
        super.setRequiredExperience(super.getExperience());
        super.setGold(randomizeStats(goldMultiplier));
        super.setCriticalRate(randomizeStats(criticalHitRate));

        if(super.getAgility() > 8)
        {
            super.setAgility(8);
        }

    }

    public void createBoss(int playerLevel)
    {
        switch(playerLevel)
        {
            case 10 -> super.setStats("Orc", 120, 8, 10, 100, 12, 100, 7);
            case 20 -> super.setStats("Ogre", 250, 15, 0, 400, 25, 300, 5);
            case 30 -> super.setStats("Giant", 500, 25, 0, 3000, 40, 1000, 0);
        }
    }
}
