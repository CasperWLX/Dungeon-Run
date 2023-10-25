package com.hampus.dungeonRun.logic;

import java.util.Scanner;

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
}
