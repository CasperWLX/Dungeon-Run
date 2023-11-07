package com.hampus.dungeonRun.casino;

public class CasinoMenu
{
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
    public void printStartingHands(Gambler user, Gambler house)
    {
        System.out.printf("These are your cards, %s\n",user.getNAME());
        System.out.println(user.getPLAYER_HAND());
        System.out.println("These are the houses cards");
        house.getHouseHand();
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
        System.out.println("You gained " + bet + " gold");
    }
    public void playerLost(int bet)
    {
        System.out.println("You lost " + bet + " gold");
    }
    public void draw(){
        System.out.println("IT'S A DRAW");
    }
    public void outOfScopeChoice()
    {
        System.out.println("That is not a choice.");
    }
}
