package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains the game flow and required methods for the Chuck-A-Luck die game.
 */
public class ChuckALuck extends Casino
{
    private final CasinoMenu MENU = new CasinoMenu();
    private final Input INPUT = new Input();
    private final List<Integer> LIST_OF_DICES = new ArrayList<>();
    private boolean playerWon;

    public void run(Player player)
    {
        MENU.welcomeToChuckALuck();
        boolean isPlaying = true;
        do
        {
            if(player.getGold() > 0)
            {
                playerWon = false;
                MENU.goldInInventory(player.getGold());
                MENU.chuckALuckMenu();

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
                MENU.kickedFromGame(2);
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
        if(playerWon)
        {
            playerWon(player, bet, amountOfRight);
        }
        else
        {
            playerLost(player, bet);
        }
        INPUT.pressEnterToContinue();
    }

    public void fieldBet(Player player)
    {
        int bet = playerBet(player);
        int sum = 0;
        MENU.chuckALuckBets(1);
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            sum += dice;
        }
        switch(sum)
        {
            case 5, 6, 7, 8, 13, 14, 15, 16 -> playerWon(player, bet, 1);
            default -> playerLost(player, bet);
        }
        INPUT.pressEnterToContinue();
    }

    public void highBet(Player player)
    {
        int bet = playerBet(player);
        int sum = 0;
        MENU.chuckALuckBets(2);
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            sum += dice;
        }
        if(sum > 11)
        {
            playerWon(player, bet, 1);
        }
        else
        {
            playerLost(player, bet);
        }
        INPUT.pressEnterToContinue();
    }

    public void lowBet(Player player)
    {
        int bet = playerBet(player);
        int sum = 0;
        MENU.chuckALuckBets(3);
        rollDices();
        for(int dice : LIST_OF_DICES)
        {
            sum += dice;
        }
        if(sum < 10)
        {
            playerWon(player, bet, 1);
        }
        else
        {
            playerLost(player, bet);
        }
        INPUT.pressEnterToContinue();
    }

    public void tripleBet(Player player)
    {
        int bet = playerBet(player);
        MENU.chuckALuckBets(4);
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
        if(counter == 3)
        {
            playerWon(player, bet, 30);
        }
        else
        {
            playerLost(player, bet);
        }
        INPUT.pressEnterToContinue();
    }

    public void rollDices()
    {
        LIST_OF_DICES.clear();
        for(int i = 0; i < 3; i++)
        {
            LIST_OF_DICES.add((int) (Math.random() * 6 + 1));
        }
        for(int dice : LIST_OF_DICES)
        {
            randomizeAnimation(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6")));
            System.out.print(dice);
        }
        System.out.println();

    }
}
