package com.hampus.dungeonRun.casino;

import java.util.ArrayList;
import java.util.List;

public class Gambler
{
    private final String NAME;
    private final List<Card> PLAYER_HAND = new ArrayList<>(0);

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
        PLAYER_HAND.add(card);
    }

    public String getPLAYER_HAND()
    {
        StringBuilder cardsInHand = new StringBuilder();
        for(Card card : PLAYER_HAND)
        {
            cardsInHand.append(card).append(", ");
        }
        return cardsInHand.toString();
    }

    public void getHouseHand()
    {
        System.out.println(PLAYER_HAND.get(0).toString() + " + hidden card");
    }

    public int sumOfCards()
    {
        int sum = 0;
        for(Card card : PLAYER_HAND)
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
        for(Card card : PLAYER_HAND)
        {
            if(sum > 21 && card.getValue() == 11)
            {
                card.setValue(1);
                sum -= 10;
            }
        }
        return sum;
    }

    public boolean didPlayerBust()
    {
        return sumOfCards() > 21;
    }

    public void clearCards()
    {
        PLAYER_HAND.clear();
    }
}
