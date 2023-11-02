package com.hampus.dungeonRun.shop_logic;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;
import com.hampus.dungeonRun.logic.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable
{
    private final Menu MENU = new Menu();
    private final List<Item> listOfItems = new ArrayList<>();

    public Shop()
    {
        listOfItems.add(new Potion(MENU.printGreen("Small health potion"),30,": A potion that heals your HP by ",20,1000));
        listOfItems.add(new Potion(MENU.printGreen("Medium Health Potion"),100,": A potion that heals your HP by ",40,1000));
        listOfItems.add(new Potion(MENU.printGreen("Large Health Potion"),300,": A potion that heals your HP by ", 100,1000));
        listOfItems.add(new Weapon(MENU.printRed("Knife"),5,": A small thieves knife, it will increase your strength by ",50,1));
        listOfItems.add(new Weapon(MENU.printRed("Greatsword"),10,": A Sword from a fallen knight, it will increase your strength by ",100,1));
        listOfItems.add(new Weapon(MENU.printRed("Excalibur"),50,": A Mythical sword with magic powers, it will increase your strength by ",1000,1));
    }

    public void buyItems(Input INPUT, Player player)
    {
        int userChoice;
        boolean userIsShopping = true;
        do
        {
            MENU.shopMenu(player.getGold(), listOfItems);
            switch(userChoice = INPUT.getInt())
            {
                case 1, 2, 3 -> purchasedHeal(userChoice, player);
                case 4,5,6 -> purchasedWeapon(userChoice, player);
                case 7 -> player.setExperience(player.getExperience() + 1000);
                case 8 -> player.setGold(1000);
                case 9 -> userIsShopping = false;
                default -> System.out.println("That is not a choice");
            }
        } while(userIsShopping);

    }

    public void purchasedHeal(int i, Player player)
    {
        i--;
        int playerGold = player.getGold();
        int cost = listOfItems.get(i).getCOST();
        int healValue = listOfItems.get(i).getVAlUE();
        if(listOfItems.get(i).getSTOCK_AMOUNT() == 0)
        {
            MENU.outOfStock();
        }
        else if(playerGold >= cost)
        {
            listOfItems.get(0).boughtItem();
            player.heal(healValue, cost);

            MENU.successfulTransaction(listOfItems.get(i).getNAME(), cost);
        }
        else
        {
            MENU.notEnoughGold();
        }
    }
    public void purchasedWeapon(int i, Player player)
    {
        i--;
        int playerGold = player.getGold();
        int cost = listOfItems.get(i).getCOST();
        if(listOfItems.get(i).getSTOCK_AMOUNT() == 0)
        {
            MENU.outOfStock();
        }
        else if(playerGold >= cost)
        {
            listOfItems.get(i).boughtItem();
            player.buyWeapon(listOfItems.get(i), cost);
            MENU.successfulTransaction(listOfItems.get(i).getNAME(), cost);
        }
        else
        {
            MENU.notEnoughGold();
        }
    }
}
