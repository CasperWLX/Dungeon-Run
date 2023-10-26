package com.hampus.dungeonRun.logic;

public class Menu
{
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private final String DIVIDER = "+---------------------------------+";
    public void welcomeMessage()
    {
        System.out.printf("\t\t\tWelcome to\t\t\t\n%s%s\n\t\t+++DUNGEON RUN+++\t\t\n%s%s\n", DIVIDER,ANSI_GREEN,ANSI_RESET,DIVIDER);
    }
    public void mainMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1.Start new game\n2.Load game\n3.Exit app");
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
    public void combatMenu(){
        System.out.println("1. Attack!\n2. Run!\n3. Check status");
    }
    public void fleeSuccess(){
        System.out.println("You escaped! Pheew hat was a close one...");
    }
    public void fleeFailed(){
        System.out.println("Oh no, looks like you could not run away...");
    }
    public void enterName(){
        System.out.println("Please enter your name");
    }
    public void exitGame(){
        System.out.println("Thanks for playing!");
    }
}
