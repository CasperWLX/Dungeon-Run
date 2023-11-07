package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;

public class BlackJack extends Casino
{
    private final CasinoMenu MENU = new CasinoMenu();
    private final Input INPUT = new Input();
    public void run(Player player, Gambler user)
    {
        BlackJackLogic playBlackJack = new BlackJackLogic();
        Gambler house = new Gambler("House");
        boolean isPlaying = true;
        System.out.printf("You have %d gold left\n", player.getGold());

        do
        {
            System.out.println("1. Play a round of Black Jack\n2. Leave the table");
            switch(INPUT.getInt())
            {
                case 1 -> playBlackJack(playBlackJack,user,house, player);
                case 2 -> isPlaying = false;
                default -> MENU.outOfScopeChoice();
            }
            if(player.getGold() < 1)
            {
                isPlaying = false;
                System.out.println("Look's like you're out of gold kid, your gonna have to leave the table now");
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
            System.out.println("What would you like to do?\n1. Draw card\n2. Stay");
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
