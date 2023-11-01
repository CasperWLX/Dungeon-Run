package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.shop_logic.Item;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable
{
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    public static final String WHITE = "\033[0;37m";
    private static final String RESET = "\u001B[0m";
    private final String SMALL_DIVIDER = "----------------------------";
    private final String STATS_DIVIDER = SMALL_DIVIDER + SMALL_DIVIDER + SMALL_DIVIDER + SMALL_DIVIDER;

    public void welcomeMessage()
    {
        String DIVIDER = "+---------------------------------+";
        System.out.println("\t\t\tWelcome to\t\t\t\n" + DIVIDER);
        System.out.println(printGreen("\t\t+++DUNGEON RUN+++\t\t"));
        System.out.println(DIVIDER);
    }

    public void mainMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1. Start new game\n2. Load game\n3. Exit app");
    }

    public void gameMenu()
    {
        System.out.println("Please select one of the following options");
        System.out.println("1. Fight against new monster\n2. Character sheet\n3. Shop\n4. Exit game");
    }
    public void loadedCharacter(Player player){
        System.out.printf("-Welcome back %s-\n", player.getName());
    }

    public void outOfScopeChoice()
    {
        System.out.println("That is not a choice.");
    }

    public void combatMenu()
    {
        System.out.println("1. Attack!\n2. Run!\n3. Player stats");
    }

    public void fleeSuccess()
    {
        System.out.println("You escaped! Phew hat was a close one...");
    }

    public void fleeFailed()
    {
        System.out.println("Oh no, you could not run away...");
    }

    public void enterName()
    {
        System.out.println("Please enter your name");
    }

    public void exitGame()
    {
        System.out.println("Thanks for playing!");
    }

    public void combatSuccess(Monster monster)
    {
        System.out.printf("YOU WON THE FIGHT! GREAT JOB!\nYou gained %d experience and %d gold\n",
                monster.getExperience(), monster.getGold());
    }

    public void printPlayerStats(Player player)
    {
        String stats = player.getStats();
        System.out.println(STATS_DIVIDER + "\n" + stats + STATS_DIVIDER + "\n");

    }

    public void printMonsterStats(CharacterManager characterManager)
    {
        String stats = characterManager.getMONSTER().getStats();
        System.out.println(STATS_DIVIDER + "\n" + stats + STATS_DIVIDER + "\n");
    }

    public void printMonsterName(CharacterManager characterManager)
    {
        System.out.printf("A level %d %s appeared!\n\n", characterManager.getMONSTER().getLevel(), characterManager.getMONSTER().getName());
    }

    public void incomingBossBattle()
    {
        System.out.println(printRed("WATCH OUT! THE NEXT FIGHT WILL BE A BOSS FIGHT!"));
    }

    public void levelUpMessage(Player player)
    {
        int level = player.getLevel();
        System.out.println(printGreen("\nCONGRATS YOU'VE LEVELED UP!\nHere are your new stats!\n"));
        printPlayerStats(player);
        switch(level)
        {
            case 11 -> System.out.println("You can run into Trolls and Cyclopes now!");
            case 21 -> System.out.println("Be careful, now you can run into Golems and Minotaurs");
            case 31 -> System.out.println("Woah great job getting to level 30. But be careful... the Dragons are coming...");
        }

    }

    public void gameOver(Player player)
    {
        System.out.println("OH NO YOU DIED!\nThese are your final stats:");
        printPlayerStats(player);
        System.out.printf("You got %d kills\n", player.getNoOfKills());
        System.out.printf("%s will now be deleted, thanks for playing\n", player.getName());
    }

    public void shopMenu(int playerGold, List<Item> itemList)
    {
        System.out.println("--Welcome to the shop, let me present to you all our items and what they cost--");
        for(int i = 0; i < itemList.size(); i++)
        {
            System.out.printf(i + 1 + ": %s%s, cost : %d, stock : %d\n",
                    itemList.get(i).getNAME(),
                    itemList.get(i).getDESCRIPTION(),
                    itemList.get(i).getCOST(),
                    itemList.get(i).getSTOCK_AMOUNT());
        }
        System.out.println("6: Buy xp\n7: Buy gold\n8: Exit shop");
        System.out.printf("You currently have: %s gold\n", printYellow(String.valueOf(playerGold)));
    }

    public void successfulTransaction(String item, int cost)
    {
        System.out.printf("You bought %s for %s gold\n", item, printYellow(String.valueOf(cost)));
        System.out.println("Pleasure to do business with you, is there anything else you would like to buy?");
    }

    public void outOfStock()
    {
        System.out.println("Sorry that item is sold out, but you could buy something else.");
    }

    public void notEnoughGold()
    {
        System.out.println("Looks like you don't have enough money");
    }

    public String printGreen(String text)
    {
        return GREEN + text + RESET;
    }

    public String printRed(String text)
    {
        return RED + text + RESET;
    }

    public String printYellow(String text)
    {
        return YELLOW + text + RESET;
    }

    public String printBlue(String text)
    {
        return BLUE + text + RESET;
    }

    public String printWhite(String text)
    {
        return WHITE + text + RESET;
    }
}
