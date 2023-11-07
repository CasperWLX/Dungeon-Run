package com.hampus.dungeonRun.casino;

import java.util.ArrayList;
import java.util.List;

public class Gambler
{
    private final String NAME;
    private List<Card> playerHand = new ArrayList<>(0);
    public Gambler(String name)
    {
        this.NAME = name;
    }
    public String getNAME()
    {
        return NAME;
    }

    public void addCardToHand(Card card)
    {
        playerHand.add(card);
    }
    public String getPlayerHand()
    {
        StringBuilder cardsInHand = new StringBuilder();
        for(Card card : playerHand){
            cardsInHand.append(card).append(", ");
        }
        return cardsInHand.toString();
    }
    public void getHouseHand()
    {
        System.out.println(playerHand.get(0).toString() + " + hidden card");
    }
    public int sumOfCards()
    {
        int sum = 0;
        for(Card card : playerHand)
        {
            if(card.getValue() == 11)
            {
                if(sum + card.getValue() > 21)
                {
                    card.setValue(1);
                }
            }

            sum += card.getValue();
        }
        return sum;
    }
    public boolean didPlayerBust()
    {
        return sumOfCards() > 21;
    }
    public void clearCards(){
        playerHand.clear();
    }
}
