package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.Input;
import com.hampus.dungeonRun.logic.Menu;
import com.hampus.dungeonRun.shop_logic.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player extends ACharacter implements Serializable
{

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

    public void takeDamage(Monster monster)
    {
        int randomDamage = monster.getStrength() + (int) (Math.random() * 3);
        String monsterName = monster.getName();

        if(didDodge())
        {
            System.out.printf("%s dodged the %s's attack!\n", super.getName(), monsterName);
            return;
        }
        if(IsItACriticalHit(monster))
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

    public boolean IsItACriticalHit(Monster monster)
    {
        int randomizer = (int) (Math.random() * 100 + 1);

        return randomizer < monster.getCriticalRate();
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
