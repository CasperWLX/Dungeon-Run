package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;

public class Menu
{
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private final String SMALL_DIVIDER = "----------------------------";
    private final String STATS_DIVIDER = SMALL_DIVIDER + SMALL_DIVIDER + SMALL_DIVIDER + SMALL_DIVIDER;

    public void welcomeMessage()
    {
        String DIVIDER = "+---------------------------------+";
        System.out.println("\t\t\tWelcome to\t\t\t\n" + DIVIDER);
        printGreen("\t\t+++DUNGEON RUN+++\t\t");
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
    public void noSaveFile(){
        System.out.println("Whoops, looks like there is no save character on this PC");
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
                characterManager.getMonster().getExperience(), characterManager.getMonster().getGold());
    }

    public void printPlayerStats(CharacterManager characterManager)
    {
        String stats = characterManager.getPlayer().getStats();
        System.out.println(STATS_DIVIDER + "\n" + stats + STATS_DIVIDER + "\n");

    }

    public void printMonsterStats(CharacterManager characterManager)
    {
        String stats = characterManager.getMonster().getStats();
        System.out.println(STATS_DIVIDER + "\n" + stats + STATS_DIVIDER + "\n");
    }

    public void printMonsterName(CharacterManager characterManager)
    {
        System.out.printf("A level %d %s appeared!\n", characterManager.getMonster().getLevel(), characterManager.getMonster().getName());
    }

    public void levelUpMessage(CharacterManager characterManager)
    {
        int level = characterManager.getPlayer().getLevel();
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
        System.out.printf("You got %d amount of kills\n",characterManager.getPlayer().getNoOfKills());
        System.out.printf("%s will now be deleted, thanks for playing\n",characterManager.getPlayer().getName());
    }

    public void printGreen(String text)
    {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

    public void printRed(String text)
    {
        System.out.println(ANSI_RED + text + ANSI_RESET);
    }

    public void printYellow(String text)
    {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    public void printBlue(String text)
    {
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

}
