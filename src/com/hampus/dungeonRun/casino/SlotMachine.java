package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.logic.Input;

import java.util.ArrayList;
import java.util.List;

public class SlotMachine extends Casino
{
    private final List<String> LIST_OF_SYMBOLS = new ArrayList<>();
    private final List<String> RANDOMIZED_SYMBOLS = new ArrayList<>();
    private final CasinoMenu MENU = new CasinoMenu();
    private int betAmount = 1;
    private final Input INPUT = new Input();
    public SlotMachine()
    {
        String fire = "️\uD83D\uDD25";
        String clover = "\uD83C\uDF40";
        String sunflower = "\uD83C\uDF3B";
        String heart = "❤️️";
        String star = "⭐";
        String strawberry = "\uD83C\uDF53";
        String headphones = "\uD83C\uDFA7";
        String mushroom = "\uD83C\uDF44";
        String lightning = "⚡";
        LIST_OF_SYMBOLS.add(fire);
        LIST_OF_SYMBOLS.add(clover);
        LIST_OF_SYMBOLS.add(sunflower);
        LIST_OF_SYMBOLS.add(heart);
        LIST_OF_SYMBOLS.add(star);
        LIST_OF_SYMBOLS.add(strawberry);
        LIST_OF_SYMBOLS.add(headphones);
        LIST_OF_SYMBOLS.add(mushroom);
        LIST_OF_SYMBOLS.add(lightning);
    }
    public void run(Player player)
    {
        MENU.welcomeToSlots();
        boolean isPlaying = true;
        do
        {
            MENU.slotsMenu();
            switch(INPUT.getInt())
            {
                case 1 -> pullLever(player);
                case 2 -> changeBetAmount(player);
                case 3 -> isPlaying = false;
                default -> MENU.outOfScopeChoice();
            }
            if (player.getGold() == 0)
            {
                MENU.kickedFromGame(3);
                isPlaying = false;
            }
            MENU.goldInInventory(player.getGold());
        }while(isPlaying);
    }
    public void pullLever(Player player)
    {
        if (player.getGold() - betAmount < 0)
        {
            MENU.notEnoughGold();
            return;
        }
        RANDOMIZED_SYMBOLS.clear();
        int sameSymbolCounter = 0;
        int starCounter = 0;
        for(int i = 0; i < 3; i++)
        {
            RANDOMIZED_SYMBOLS.add(LIST_OF_SYMBOLS.get((int)(Math.random() * LIST_OF_SYMBOLS.size())));
            randomizeAnimation(LIST_OF_SYMBOLS);
            System.out.print(RANDOMIZED_SYMBOLS.get(i));
        }
        MENU.waitMilliSeconds(200);
        System.out.println();

        for(String slot : RANDOMIZED_SYMBOLS)
        {
            if(slot.equals(RANDOMIZED_SYMBOLS.get(0)))
            {
                sameSymbolCounter++;
            }
            if(slot.equals("⭐"))
            {
                starCounter++;
            }
        }
        if(sameSymbolCounter == 3)
        {
            playerWon(player, betAmount, 50);
        }
        else if(starCounter > 0)
        {
            playerWon(player,betAmount,starCounter);
        }
        else
        {
            playerLost(player,betAmount);
        }
    }
    public void changeBetAmount(Player player)
    {
        betAmount = playerBet(player);
    }
}
