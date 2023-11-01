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
    private final List<String> ITEMS = new ArrayList<>();
    private final List<Integer> ITEM_VALUE = new ArrayList<>();
    private final List<Integer> ITEM_COST = new ArrayList<>();
    private final List<Integer> AMOUNT_OF_ITEMS = new ArrayList<>();

    public Shop()
    {
        ITEMS.add(MENU.printGreen("Small Health Potion") + ": A potion that heals your HP by 30");
        ITEM_VALUE.add(30);
        ITEM_COST.add(20);
        AMOUNT_OF_ITEMS.add(1000);

        ITEMS.add(MENU.printGreen("Medium Health Potion") + ": A potion that heals your HP by 100");
        ITEM_VALUE.add(100);
        ITEM_COST.add(40);
        AMOUNT_OF_ITEMS.add(1000);

        ITEMS.add(MENU.printGreen("Large Health Potion") + ": A potion that heals your HP by 300");
        ITEM_VALUE.add(300);
        ITEM_COST.add(80);
        AMOUNT_OF_ITEMS.add(1000);

        ITEMS.add(MENU.printRed("Small Dagger") + ": A small thieves dagger, it will increase your strength by 5");
        ITEM_VALUE.add(5);
        ITEM_COST.add(30);
        AMOUNT_OF_ITEMS.add(1);

        ITEMS.add("xp potion: 1000 xp");
        ITEM_VALUE.add(1000);
        ITEM_COST.add(0);
        AMOUNT_OF_ITEMS.add(1000);

        ITEMS.add("get 1000 gold");
        ITEM_VALUE.add(1000);
        ITEM_COST.add(0);
        AMOUNT_OF_ITEMS.add(1000);
    }

    public void buyItems(Input INPUT, Player player)
    {
        int userChoice;
        boolean userIsShopping = true;
        do
        {
            MENU.shopMenu(player.getGold(), ITEMS, ITEM_COST, AMOUNT_OF_ITEMS);
            switch(userChoice = INPUT.getInt())
            {
                case 1, 2, 3 -> purchasedHeal(userChoice, player);
                case 4 -> purchasedItem(userChoice, player);
                case 5 -> player.setExperience(player.getExperience() + 1000);
                case 6 -> player.setGold(1000);
                case 7 -> userIsShopping = false;
                default -> System.out.println("That is not a choice");
            }

        } while(userIsShopping);

    }

    public void purchasedHeal(int i, Player player)
    {
        i--;
        int playerGold = player.getGold();
        int cost = ITEM_COST.get(i);
        int healValue = ITEM_VALUE.get(i);
        if(AMOUNT_OF_ITEMS.get(i) == 0)
        {
            MENU.outOfStock();
        }
        else if(playerGold > ITEM_COST.get(i))
        {
            AMOUNT_OF_ITEMS.set(i, AMOUNT_OF_ITEMS.get(i) - 1);
            player.heal(healValue, cost);

            MENU.successfulTransaction(ITEMS.get(i), cost);
        }
        else
        {
            MENU.notEnoughGold();
        }
    }

    public void purchasedItem(int i, Player player)
    {


    }

}
