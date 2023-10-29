package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;

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
        System.out.println("You escaped! Pheew hat was a close one...");
    }

    public void fleeFailed()
    {
        System.out.println("Oh no, looks like you could not run away...");
    }

    public void enterName()
    {
        System.out.println("Please enter your name");
    }

    public void exitGame()
    {
        System.out.println("Thanks for playing!");
    }

    public void combatSuccess(CharacterManager characterManager)
    {
        System.out.printf("YOU WON THE FIGHT! GREAT JOB!\nYou gained %d experience and %d gold\n",
                characterManager.getMONSTER().getExperience(), characterManager.getMONSTER().getGold());
    }

    public void printPlayerStats(CharacterManager characterManager)
    {
        String stats = characterManager.getPLAYER().getStats();
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

    public void levelUpMessage(CharacterManager characterManager)
    {
        int level = characterManager.getPLAYER().getLevel();
        System.out.println("CONGRATS YOU'VE LEVELED UP!\nHere are your new stats!\n");
        printPlayerStats(characterManager);
        switch(level)
        {
            case 10 -> System.out.println("You can run into Trolls now!");
            case 20 -> System.out.println("Be careful, now you can run into Golems");
            case 30 ->
                    System.out.println("Woah great job getting to level 30. But be careful... the Dragons are coming...");
        }

    }
    public void gameOver(CharacterManager characterManager)
    {
        System.out.println("OH NO LOOKS LIKE YOU DIED!\nThese are your final stats:");
        printPlayerStats(characterManager);
        System.out.printf("You got %d kills\n",characterManager.getPLAYER().getNoOfKills());
        System.out.printf("%s will now be deleted, thanks for playing\n",characterManager.getPLAYER().getName());
    }

    public void shopMenu(int playerGold,List<String> item, List<Integer> itemCost, List<Integer> amountOfItems)
    {
        System.out.println("--Welcome to the shop, let me present to you all our items and what they cost--");
        for(int i = 0; i < item.size(); i++){
            System.out.printf(i + 1 + ": %s, cost : %d, stock : %d\n",item.get(i),itemCost.get(i),amountOfItems.get(i));
        }
        System.out.println("7: Exit shop");
        System.out.printf("You currently have: %s gold\n",printYellow(String.valueOf(playerGold)));
    }
    public void successfulTransaction(String item, int cost)
    {
        System.out.printf("You bought %s for %s gold\n",item,printYellow(String.valueOf(cost)));
        System.out.println("Pleasure to do business with you, is there anything else you would like to buy?");
    }
    public void outOfStock(){
        System.out.println("Sorry that items seems to sold out, but you could buy something else.");
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
    public String printWhite(String text){
        return WHITE + text + RESET;
    }
}
