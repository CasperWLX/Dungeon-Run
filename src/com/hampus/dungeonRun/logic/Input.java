package com.hampus.dungeonRun.logic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input
{
    Scanner input = new Scanner(System.in);

    /**
     * Metod som tar emot inmatning från användaren men endast returnerar ifall det är ett heltal
     *
     * @return - heltal
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
     * Metod som kontrollerar ifall det strängen är tom
     *
     * @param number - sträng med inmatning från användaren
     * @return - 0 eller inmatat nummer
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
     * Metod som kontrollerar ifall det strängen endast innehåller tal
     *
     * @param number - sträng med inmatning från användaren
     * @return - 0 eller inmatat nummer
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
     * Metod som kontrollerar att inmatade talet är positivt
     *
     * @param number - sträng med inmatning från användaren
     * @return - 0 eller inmatat nummer
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
     * Metod som endast returnerar en String med alfabetiska bokstäver mellan A-Ö
     *
     * @return - String med spelarens namn
     */
    public String getStringInput()
    {
        while(true)
        {
            //Sparar input i en String
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
        //Sets up allowed characters
        Pattern special = Pattern.compile("[^a-ö0-9]", Pattern.CASE_INSENSITIVE);
        Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

        Matcher matcher = special.matcher(userName);
        Matcher numberMatcher = number.matcher(userName);

        //Checks if string contains non-allowed characters
        boolean containsSymbols = matcher.find();
        boolean containsNumbers = numberMatcher.find();

        //Returns name if input is correct, else blank space
        if(containsSymbols || containsNumbers)
        {
            System.out.println("The name can't contain special characters or numbers");
            return " ";
        }
        else
        {
            System.out.println("Player " + userName + " has been added");
            return userName;
        }
    }
}
