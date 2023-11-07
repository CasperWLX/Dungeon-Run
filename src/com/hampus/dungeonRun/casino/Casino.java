package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;

public class Casino
{
    //TODO - GÖR ALLA PRINTS FINARE
    private final CasinoMenu MENU = new CasinoMenu();
    private final Input INPUT = new Input();

    public void casinoMenu(Player player)
    {

        boolean isPlayingAtTheCasino = true;
        MENU.welcomeToTheCasino();
        do
        {
            MENU.chooseGame();

            switch(INPUT.getInt())
            {
                case 1 ->
                {
                    BlackJack blackjack = new BlackJack();
                    Gambler user = new Gambler(player.getName());
                    blackjack.run(player, user);
                }
                case 2 ->
                {
                    ChuckALuck chuckALuck = new ChuckALuck();
                    chuckALuck.run(player);
                }
                case 3 ->
                {
                    SlotMachine slots = new SlotMachine();
                    slots.run(player);
                }
                case 4 -> isPlayingAtTheCasino = false;
                default -> MENU.outOfScopeChoice();
            }
            if(player.getGold() < 1)
            {
                isPlayingAtTheCasino = false;
                System.out.println("You feel a tap on your shoulder. Behind you stands a large bodyguard with a mean face.");
                System.out.println("You got kicked out of the Casino...");
            }
        } while(isPlayingAtTheCasino);
    }

    public int playerBet(Player player)
    {
        MENU.betAmount();
        while(true)
        {
            int betAmount = INPUT.getInt();
            if(player.getGold() - betAmount < 0)
            {
                System.out.println("Sorry you don't have enough gold, try a smaller amount");
            }
            else
            {
                return betAmount;
            }
        }
    }

    public void playerWon(Player player, int bet, int multiplier)
    {
        MENU.playerWon(bet * multiplier);
        player.setGold(player.getGold() + bet * multiplier);
    }

    public void playerLost(Player player, int bet)
    {
        MENU.playerLost(bet);
        player.setGold(player.getGold() - bet);
    }
}
