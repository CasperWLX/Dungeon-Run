package com.hampus.dungeonRun.logic;

import java.io.Serializable;

public class InputMenu
{
    /**
     * Class that handles all Input messages
     */
    private final Colorize COLORIZE = new Colorize();

    public void pressEnter()
    {
        String text = COLORIZE.printWhite("Press enter to continue");
        System.out.print(COLORIZE.printBlackBackground(text));
    }

    public void notAnOption()
    {
        System.out.println("That's not an option, try again");
    }

    public void nothingEntered()
    {
        System.out.println("Nothing entered, try again");
    }

    public void onlyNumbers()
    {
        System.out.println("Please only enter numbers");
    }

    public void numberMustBePositive()
    {
        System.out.println("Please enter a positive number");
    }

    public void invalidName()
    {
        System.out.println("Please enter a valid name");
    }

    public void correctName(String userName)
    {
        System.out.println("Player " + userName + " has been added");
    }

    public void incorrectUsername()
    {
        System.out.println("The name can't contain special characters or numbers");
    }
}
