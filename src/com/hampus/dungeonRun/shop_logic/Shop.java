package com.hampus.dungeonRun.shop_logic;

import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.dbLogic.DBConnection;
import com.hampus.dungeonRun.logic.Colorize;
import com.hampus.dungeonRun.logic.Input;
import com.hampus.dungeonRun.logic.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The shop class which contains all the related methods.
 */
public class Shop implements Serializable
{
    private final Menu MENU = new Menu();
    private final Colorize COLORIZE = new Colorize();
    private final List<Item> listOfItems = new ArrayList<>();
    private final DBConnection CONNECTION = new DBConnection();

    public Shop()
    {
        listOfItems.add(new Potion(COLORIZE.printGreen("Small health potion"), 30, ": A potion that instantly heals your HP by ", 20, 1000));
        listOfItems.add(new Potion(COLORIZE.printGreen("Medium Health Potion"), 100, ": A potion that instantly heals your HP by ", 40, 1000));
        listOfItems.add(new Potion(COLORIZE.printGreen("Large Health Potion"), 300, ": A potion that instantly heals your HP by ", 100, 1000));
        listOfItems.add(new Weapon(COLORIZE.printRed("Knife"), 5, ": A small thieves knife, it will increase your strength by ", 50, 1));
        listOfItems.add(new Weapon(COLORIZE.printRed("Greatsword"), 15, ": A Sword from a fallen knight, it will increase your strength by ", 100, 1));
        listOfItems.add(new Weapon(COLORIZE.printRed("Excalibur"), 50, ": A Mythical sword with magic powers, it will increase your strength by ", 1000, 1));
    }

    public void buyItems(Player player, Input input)
    {
        CONNECTION.openConnection();
        MENU.welcomeToTheShop();
        int userChoice;
        boolean userIsShopping = true;
        do
        {
            MENU.shopMenu(player.getGold(), listOfItems);
            switch(userChoice = input.getInt())
            {
                case 1, 2, 3 -> purchasedHeal(userChoice, player);
                case 4, 5, 6 -> purchasedWeapon(userChoice, player);
                case 7 -> devToolLevelUp(player, input); // dev tool
                case 8 -> player.setGold(player.getGold() + 1000); // dev tool
                case 9 -> userIsShopping = false;
                default -> MENU.outOfScopeChoice();
            }
        } while(userIsShopping);

    }

    public void purchasedHeal(int i, Player player)
    {
        i--;
        if(listOfItems.get(i).getStockAmount() == 0)
        {
            MENU.outOfStock();
        }
        else if(player.getGold() >= listOfItems.get(i).getCost())
        {
            listOfItems.get(0).boughtItem();
            player.heal(listOfItems.get(i).getValue(), listOfItems.get(i).getCost());

            MENU.successfulTransaction(listOfItems.get(i).getName(), listOfItems.get(i).getCost());
            CONNECTION.boughtItem(player,listOfItems.get(i).getItemID());
        }
        else
        {
            MENU.notEnoughGold();
        }
    }

    public void purchasedWeapon(int i, Player player)
    {
        i--;
        if(listOfItems.get(i).getStockAmount() == 0)
        {
            MENU.outOfStock();
        }
        else if(player.getGold() >= listOfItems.get(i).getCost())
        {
            listOfItems.get(i).boughtItem();
            player.buyWeapon(listOfItems.get(i), listOfItems.get(i).getCost());
            MENU.successfulTransaction(listOfItems.get(i).getName(), listOfItems.get(i).getCost());

            CONNECTION.boughtItem(player,listOfItems.get(i).getItemID());
        }
        else
        {
            MENU.notEnoughGold();
        }
    }

    public void devToolLevelUp(Player player, Input input)
    {
        System.out.println("What level do you wanna go to?");
        int desiredLevel = input.getInt();
        while(player.getLevel() < desiredLevel)
        {
            player.setExperience(player.getRequiredExperience());
            player.levelUp(new Monster(0, 0, 0, 0, 0, 0, 0));
        }
    }
    public List<Item> getListOfItems()
    {
        return listOfItems;
    }
}
