package com.hampus.dungeonRun.logic;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class that handles all input
 */
public class Input
{
    private final Scanner INPUT = new Scanner(System.in);
    private final InputMenu INPUT_MENU = new InputMenu();

    public void pressEnterToContinue()
    {
        INPUT_MENU.pressEnter();
        INPUT.nextLine();
    }

    /**
     * A method which restricts the amount of choices the user has
     *
     * @param min - minimum choice
     * @param max - maximum choice
     * @return - integer with user input
     */
    public int restrictedInput(int min, int max)
    {
        int input;
        while(true)
        {
            input = getInt();
            if(input < min || input > max)
            {
                INPUT_MENU.notAnOption();
            }
            else
            {
                return input;
            }
        }
    }

    /**
     * A method that makes sure the input is only positive integers
     *
     * @return - integer with user input
     */
    public int getInt()
    {
        //Anger variabler
        boolean loopIsRunning = true;
        int number = 0;

        //Startar loop för kontroll
        while(loopIsRunning)
        {
            String choice = INPUT.nextLine();
            number = isInputEmpty(choice);
            if(number != 0)
            {
                loopIsRunning = false;
            }
        }
        return number;
    }

    /**
     * A method which checks if the string is empty
     *
     * @param number - String containing user input
     * @return - 0 or user input
     */

    public int isInputEmpty(String number)
    {
        if(number.isEmpty())
        {
            INPUT_MENU.nothingEntered();
            return 0;
        }
        return isNumberAnInt(number);
    }

    /**
     * A Method which denies any special characters from passing through
     *
     * @param number - String containing user input
     * @return - 0 or user input
     */
    public int isNumberAnInt(String number)
    {
        try
        {
            return isNumberPositive(number);
        }
        catch(NumberFormatException nfe)
        {
            INPUT_MENU.onlyNumbers();
            return 0;
        }
    }

    /**
     * Method which checks if number is positive
     *
     * @param number - String containing user input
     * @return - 0 or user input
     */
    public int isNumberPositive(String number)
    {
        int result = Integer.parseInt(number);
        if(result < 1)
        {
            INPUT_MENU.numberMustBePositive();
            return 0;
        }
        return result;
    }

    /**
     * Method which returns a string with any letter or combination of letters between A-Ö
     *
     * @return - String with player name
     */
    public String getStringInput()
    {
        while(true)
        {
            //Saves input in a String
            String userName = INPUT.nextLine();

            userName = hasSpecialCharacterOrNumber(userName);

            if(userName.equals(" "))
            {
                INPUT_MENU.invalidName();
            }
            else
            {
                return userName;
            }
        }
    }

    /**
     * A method that controls if a string contains special characters or numbers
     *
     * @param userName - String based on user input
     * @return - A string with blank space
     */
    public String hasSpecialCharacterOrNumber(String userName)
    {
        //Set up restrictions
        String myRegex = "([a-öA-Ö])*";
        boolean containsCorrectCharacters = Pattern.matches(myRegex, userName);

        if(containsCorrectCharacters)
        {
            INPUT_MENU.correctName(userName);
            return userName;
        }
        else
        {
            INPUT_MENU.incorrectUsername();
            return " ";
        }
    }
}
