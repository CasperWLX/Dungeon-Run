package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.logic.Colorize;

public class CasinoMenu
{
    private final Colorize COLORIZE = new Colorize();
    private final String SMALL_DIVIDER = "----------------------------";
    public void welcomeToTheCasino()
    {
        System.out.println("-------------Welcome to the casino-------------");
        System.out.println("What would you like to play?");
    }
    public void chooseGame()
    {
        System.out.println("1. BlackJack\n2. Chuck-A-Luck\n3. Slots\n4. Exit Casino");
    }
    public void betAmount()
    {
        System.out.println("How much would you like to bet?");
    }
    public void blackJackMenu()
    {
        System.out.println("1. Play a round of Black Jack\n2. Leave the table");
    }
    public void printStartingHands(Gambler user, Gambler house)
    {
        System.out.printf("These are your cards, %s\n",user.getNAME());
        System.out.println(user.getPLAYER_HAND());
        System.out.println("These are the houses cards");
        house.getHouseHand();
    }
    public void drawOrStay()
    {
        System.out.println("What would you like to do?\n1. Draw card\n2. Stay");
    }
    public void houseGotBlackJack(Gambler house)
    {
        System.out.println("AW SHUCKS THE HOUSE GOT A BLACKJACK");
        System.out.println(house.getPLAYER_HAND());
    }
    public void printHand(Gambler gambler)
    {
        System.out.println(gambler.getPLAYER_HAND());
    }
    public void playerWon(int bet){
        System.out.printf("You gained %s gold\n",COLORIZE.printYellow(String.valueOf(bet)));
    }
    public void playerLost(int bet)
    {
        System.out.printf("You lost %s gold\n",COLORIZE.printYellow(String.valueOf(bet)));
    }
    public void draw(){
        System.out.println("IT'S A DRAW");
    }
    public void kickedFromGame(int game){
        switch (game){
            case 1 -> System.out.println("Look's like you're out of gold kid, your gonna have to leave the table now");
            case 2 -> System.out.println("Looks like you're out of gold buddy, can't play here anymore");
            case 3 ->  System.out.println("You reach into your pocket but can't find any more gold coins...\n" +
                    "You stroll away from the slot machines, feeling defeated by the god of luck");
        }
    }
    public void outOfScopeChoice()
    {
        System.out.println("That is not a choice.");
    }
    public void notEnoughGold()
    {
        System.out.println("Sorry you don't have enough gold, try a smaller amount");
    }
    public void kickedOutOfCasino()
    {
        System.out.println("You feel a tap on your shoulder. Behind you stands a large bodyguard with a mean face.");
        System.out.println("You got kicked out of the Casino...");
    }
    public void goldInInventory(int gold)
    {
        System.out.printf("You have %s gold left\n", COLORIZE.printYellow(String.valueOf(gold)));
    }
    public void chuckALuckMenu()
    {
        System.out.println("What would you like to bet on?");
        System.out.println("""
                1. Numbers bet
                2. Field bet
                3. High bet (Over 11)
                4. Low bet (Under 10)
                5. Any triple
                6. Stop playing""");
    }
    public void chuckALuckBets(int bet)
    {
        switch (bet)
        {
            case 1 -> System.out.println("You bet that the sum of the dices will be between 5-8 or 13-16");
            case 2 -> System.out.println("You bet that the sum of the dices would be higher than 11");
            case 3 -> System.out.println("You bet that the sum of the dices would be lower than 10");
            case 4 -> System.out.println("You bet there will be a triple of any number");
        }
    }
    public void welcomeToSlots()
    {
        System.out.println(SMALL_DIVIDER);
        System.out.println("Welcome to slots");
        System.out.println("The starting bet amount is 1 gold");
        System.out.println(SMALL_DIVIDER);
    }
    public void welcomeToChuckALuck(){
        System.out.printf("%s\nWelcome to Chuck-A-Luck\n%s\n",SMALL_DIVIDER,SMALL_DIVIDER);
    }
    public void welcomeToBlackJack()
    {
        System.out.printf("%s\nWelcome to Black Jack\n%s\n",SMALL_DIVIDER,SMALL_DIVIDER);
    }
    public void slotsMenu()
    {
        System.out.println("""
                1. Pull lever
                2. Change bet amount
                3. Leave machine""");
    }
}
