package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;

/**
 * A class that runs the Black Jack card game.
 * The class handles the flow of the game
 */
public class BlackJack extends Casino
{
    private final CasinoMenu MENU = new CasinoMenu();
    private final Input INPUT = new Input();
    public void run(Player player, Gambler user)
    {
        MENU.welcomeToBlackJack();
        BlackJackLogic playBlackJack = new BlackJackLogic();
        Gambler house = new Gambler("House");
        boolean isPlaying = true;
        MENU.goldInInventory(player.getGold());
        do
        {
            MENU.blackJackMenu();
            switch(INPUT.getInt())
            {
                case 1 -> playBlackJack(playBlackJack,user,house, player);
                case 2 -> isPlaying = false;
                default -> MENU.outOfScopeChoice();
            }
            if(player.getGold() < 1)
            {
                isPlaying = false;
                MENU.kickedFromGame(1);
            }
        }while(isPlaying);

    }
    public void playBlackJack(BlackJackLogic playBlackJack, Gambler user, Gambler house, Player player)
    {
        boolean someoneLost = false;

        int bet = playerBet(player);
        playBlackJack.dealCards(house, user);
        MENU.printStartingHands(user,house);

        if(house.sumOfCards() == 21)
        {
            MENU.houseGotBlackJack(house);
            someoneLost = true;
        }
        while(!someoneLost)
        {
            MENU.drawOrStay();
            switch(INPUT.getInt())
            {
                case 1 ->
                {
                    playBlackJack.drawCard(user);
                    someoneLost = user.didPlayerBust();
                    MENU.printHand(user);
                }
                case 2 ->
                {
                    someoneLost = playBlackJack.playerStays(house);
                    MENU.printHand(house);
                    if(playBlackJack.didHouseStay())
                    {
                        someoneLost = true;
                    }
                }
                default -> MENU.outOfScopeChoice();
            }
        }

        if(house.didPlayerBust())
        {
            playerWon(player,bet, 1);
        }
        else if(user.didPlayerBust())
        {
            playerLost(player,bet);
        }
        else if(user.sumOfCards() > house.sumOfCards())
        {
            playerWon(player,bet, 1);
        }
        else if(house.sumOfCards() > user.sumOfCards())
        {
            playerLost(player,bet);
        }
        else
        {
            MENU.draw();
        }
        user.clearCards();
        house.clearCards();
    }
}
