package com.hampus.dungeonRun.casino;

import java.util.function.DoubleToIntFunction;

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
        System.out.println(user.getPlayerHand());
        System.out.println("These are the houses cards");
        house.getHouseHand();
    }
    public void houseGotBlackJack(Gambler house)
    {
        System.out.println("AW SHUCKS THE HOUSE GOT A BLACKJACK");
        System.out.println(house.getPlayerHand());
    }
    public void printHand(Gambler gambler)
    {
        System.out.println(gambler.getPlayerHand());
    }
    public void playerWon(int bet){
        System.out.println("YOU WON\nYou gained " + bet + " gold");
    }
    public void playerLost(int bet)
    {
        System.out.println("YOU LOST\nYou lost " + bet + " gold");
    }
    public void draw(){
        System.out.println("IT'S A DRAW");
    }
}
