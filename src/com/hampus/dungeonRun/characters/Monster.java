package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.Colorize;
import com.hampus.dungeonRun.logic.Menu;
import com.hampus.dungeonRun.shop_logic.Item;

import java.io.Serializable;

public class Monster extends ACharacter implements Serializable
{
    private final Menu MENU = new Menu();
    private final Colorize COLORIZE = new Colorize();
    public Monster(int health, int strength, int agility, int experience, int level, int gold, int criticalRate)
    {
        super(health, strength, agility, experience, level, gold, criticalRate);
    }

    public void calculateDamage(int playerStrength, int criticalRate, Item weapon, String playerName)
    {
        int randomDamage = randomizeStats(playerStrength);
        try
        {
            randomDamage += weapon.getVAlUE();
        }catch(NullPointerException ignored)
        {

        }
        takeDamage(randomDamage,super.getName(),playerName,criticalRate);
    }
    public int randomDamage(int playerStrength)
    {
        return playerStrength + (int) (Math.random() * 3 + 1);
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
            damage = damage* 2;
            MENU.characterGotACrit(characterDealingDamage,characterTakingDamage);
        }
        MENU.monsterTookDamage(COLORIZE.printBlue(characterDealingDamage), COLORIZE.printRed(String.valueOf(damage)), COLORIZE.printBlue(characterTakingDamage));
        setHealth(getHealth() - damage);
    }

    public void generateMonster(Player player, double statsMultiplier, int gold, int criticalHitRate, String name)
    {
        super.setName(name);
        super.setHealth(randomizeStats((int) (player.getMaxHealth() * statsMultiplier)));
        super.setMaxHealth(super.getHealth());
        super.setStrength(randomizeStats((int) (player.getStrength() * statsMultiplier)));
        super.setAgility(randomizeStats((int) (player.getAgility() * statsMultiplier)));
        super.setLevel(randomizeStats((int) (player.getLevel() * statsMultiplier)));
        super.setExperience(randomizeStats((int) (super.getLevel() * 8.3)));
        super.setRequiredExperience(super.getExperience());
        super.setGold(randomizeStats(gold));
        super.setCriticalRate(randomizeStats(criticalHitRate));

        if(super.getAgility() > 8)
        {
            super.setAgility(8);
        }
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
