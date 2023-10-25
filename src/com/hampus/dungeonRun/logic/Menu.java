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
    public void firstMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1.Start new game\n2.Load game\n3.Exit app");
    }
}
