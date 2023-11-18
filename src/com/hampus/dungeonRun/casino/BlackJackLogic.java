package com.hampus.dungeonRun.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class that executes methods related to the Black Jack game such as drawCard.
 * The class contains deck logic and player logic
 */
public class BlackJackLogic
{
    List<Card> listOfCards = new ArrayList<>();
    List<Card> discardedCards = new ArrayList<>();
    List<String> faceNames = new ArrayList<>(Arrays.asList("Ace", "Jack", "Queen", "King"));
    private boolean houseStayed = false;

    public BlackJackLogic()
    {
        int x = 0;
        for(int i = 1; i < 14; i++)
        {
            if(i == 1 || i > 10)
            {
                listOfCards.add(new Card("Clubs", i, faceNames.get(x)));
                listOfCards.add(new Card("Diamonds", i, faceNames.get(x)));
                listOfCards.add(new Card("Hearts", i, faceNames.get(x)));
                listOfCards.add(new Card("Spades", i, faceNames.get(x)));
                x++;
            }
            else
            {
                listOfCards.add(new Card("Clubs", i, ""));
                listOfCards.add(new Card("Diamonds", i, ""));
                listOfCards.add(new Card("Hearts", i, ""));
                listOfCards.add(new Card("Spades", i, ""));
            }
        }
        shuffleDeck();
    }

    public void dealCards(Gambler house, Gambler player)
    {
        drawCard(player);
        drawCard(house);
        drawCard(player);
        drawCard(house);
    }

    public void drawCard(Gambler currentPlayer)
    {
        currentPlayer.addCardToHand(listOfCards.get(0));
        discardedCards.add(listOfCards.get(0));
        listOfCards.remove(0);
        ifDeckIsEmpty();
    }

    public void shuffleDeck()
    {
        Collections.shuffle(listOfCards);
    }

    public void ifDeckIsEmpty()
    {
        if(listOfCards.isEmpty())
        {
            listOfCards.addAll(discardedCards);
            discardedCards.clear();
            shuffleDeck();
        }
    }

    public boolean playerStays(Gambler house)
    {
        while(house.sumOfCards() < 17)
        {
            drawCard(house);
        }
        houseStayed = true;
        return house.sumOfCards() > 21;
    }

    public boolean didHouseStay()
    {
        return houseStayed;
    }
}
