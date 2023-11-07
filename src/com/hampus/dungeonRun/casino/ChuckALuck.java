package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;

import java.util.ArrayList;
import java.util.List;

public class ChuckALuck extends Casino
{
    private final CasinoMenu MENU = new CasinoMenu();
    private final Input INPUT = new Input();
    private final List<Integer> LIST_OF_DICES = new ArrayList<>();
    private boolean playerWon;

    public void run(Player player)
    {
        boolean isPlaying = true;
        do
        {
            if(player.getGold() > 0)
            {
                playerWon = false;
                System.out.printf("You have %d gold left\n", player.getGold());
                System.out.println("What would you like to bet on?");
                System.out.println("1. Numbers bet\n2. Field bet\n3. High bet (Over 11)\n4. Low bet (Under 10)\n5. Any triple\n6. Stop playing");
                switch(INPUT.getInt())
                {
                    case 1 -> numbersBet(player);
                    case 2 -> fieldBet(player);
                    case 3 -> highBet(player);
                    case 4 -> lowBet(player);
                    case 5 -> tripleBet(player);
                    case 6 -> isPlaying = false;
                    default -> MENU.outOfScopeChoice();
                }
            }
            else
            {
                System.out.println("Looks like you're out of gold buddy, can't play here anymore");
                isPlaying = false;
            }
        } while(isPlaying);
    }

    public void numbersBet(Player player)
    {
        int bet = playerBet(player);
        int amountOfRight = 0;

        System.out.println("Please choose a number between 1-6");
        int chosenNumber = INPUT.restrictedInput(1, 6);
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            if(dice == chosenNumber)
            {
                amountOfRight++;
                playerWon = true;
            }
        }
        System.out.println(LIST_OF_DICES);
        if(playerWon)
        {
            playerWon(player, bet, amountOfRight);
        }
        else
        {
            playerLost(player, bet);
        }
    }
    public void fieldBet(Player player)
    {
        int bet = playerBet(player);
        int sum = 0;
        System.out.println("You bet that the sum of the dices will be between 5-8 or 13-16");
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            sum += dice;
        }
        System.out.println(LIST_OF_DICES);
        switch(sum)
        {
            case 5, 6, 7, 8, 13, 14, 15, 16 -> playerWon(player, bet, 1);
            default -> playerLost(player, bet);
        }
    }

    public void highBet(Player player)
    {
        int bet = playerBet(player);
        int sum = 0;
        System.out.println("You bet that the sum of the dices would be higher than 11");
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            sum += dice;
        }
        System.out.println(LIST_OF_DICES);
        if(sum > 11)
        {
            playerWon(player, bet, 1);
        }
        else
        {
            playerLost(player, bet);
        }
    }

    public void lowBet(Player player)
    {
        int bet = playerBet(player);
        int sum = 0;
        System.out.println("You bet that the sum of the dices would be lower than 10");
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            sum += dice;
        }
        System.out.println(LIST_OF_DICES);
        if(sum < 10)
        {
            playerWon(player, bet, 1);
        }
        else
        {
            playerLost(player, bet);
        }
    }

    public void tripleBet(Player player)
    {
        int bet = playerBet(player);
        System.out.println("You bet there will be a triple of any number");
        int counter = 0;
        rollDices();
        int firstDice = LIST_OF_DICES.get(0);
        for(int dice : LIST_OF_DICES)
        {
            if(dice == firstDice)
            {
                counter++;
            }
        }
        System.out.println(LIST_OF_DICES);
        if(counter == 3)
        {
            playerWon(player, bet, 30);
        }
        else
        {
            playerLost(player, bet);
        }
    }

    public void rollDices()
    {
        LIST_OF_DICES.clear();
        for(int i = 0; i < 3; i++)
        {
            LIST_OF_DICES.add((int) (Math.random() * 6 + 1));
        }
    }
}
