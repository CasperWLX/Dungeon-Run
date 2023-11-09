package com.hampus.dungeonRun.logic;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input
{
    Scanner input = new Scanner(System.in);

    /**
     * A method which restricts the amount of choices the user has
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
                System.out.println("That's not an option, try again");
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
            String choice = input.nextLine();
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
            System.out.println("Nothing entered, try again");
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
            System.out.println("Please only enter numbers");
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
            System.out.println("Please enter a positive number");
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
            String userName = input.nextLine();

            userName = hasSpecialCharacterOrNumber(userName);

            if(userName.equals(" "))
            {
                System.out.println("Please enter a valid name");
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
        boolean containsCorrectCharacters = Pattern.matches(myRegex,userName);

        if(containsCorrectCharacters)
        {
            System.out.println("Player " + userName + " has been added");
            return userName;
        }
        else
        {
            System.out.println("The name can't contain special characters or numbers");
            return " ";
        }
    }
}
