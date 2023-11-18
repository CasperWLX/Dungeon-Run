package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.Colorize;
import com.hampus.dungeonRun.logic.Input;
import com.hampus.dungeonRun.logic.Menu;
import com.hampus.dungeonRun.shop_logic.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that contains the Player and all related methods and variables
 */

public class Player extends ACharacter implements Serializable
{

    private final Colorize COLORIZE = new Colorize();
    private int noOfKills = 0;
    private final List<Item> LIST_OF_WEAPONS = new ArrayList<>();
    private Item equippedItem;
    private final Menu MENU = new Menu();

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

    public void calculateDamage(int monsterStrength, String monsterName, int opponentCritRate)
    {
        int randomDamage = randomizeStats(monsterStrength);

        takeDamage(randomDamage,super.getName(),monsterName, opponentCritRate);
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
        MENU.playerTookDamage(COLORIZE.printBlue(characterDealingDamage),
                COLORIZE.printRed(String.valueOf(damage)),
                COLORIZE.printBlue(characterTakingDamage));
        setHealth(getHealth() - damage);
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
    public List<Item> getLIST_OF_WEAPONS(){
        return LIST_OF_WEAPONS;
    }
    public void buyWeapon(Item weapon, int cost)
    {
        LIST_OF_WEAPONS.add(weapon);
        super.setGold(super.getGold() - cost);
    }
    public Item getWeapon()
    {
        return equippedItem;
    }

    public void setEquippedItem(Input input)
    {
        MENU.playerWeapons(LIST_OF_WEAPONS);
        int playerChoice = input.restrictedInput(1, LIST_OF_WEAPONS.size());
        equippedItem = LIST_OF_WEAPONS.get(playerChoice - 1);
        MENU.equippedItem(equippedItem);
    }
}
