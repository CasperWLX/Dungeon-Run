package com.hampus.dungeonRun.logic;

public class Colorize
{
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    public static final String WHITE = "\033[0;37m";
    private static final String RESET = "\u001B[0m";
    public static final String PURPLE_BOLD = "\033[1;35m";
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
    public String printPurpleBold(String text){
        return PURPLE_BOLD + text + RESET;
    }
}
