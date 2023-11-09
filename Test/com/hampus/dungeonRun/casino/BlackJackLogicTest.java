package com.hampus.dungeonRun.casino;

import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackLogicTest
{



    /**
     * Test that makes sure the game can handle situations where someone gets multiple aces and correctly converts
     * the number 11 to 1 if the sum of the players cards is higher than 21.
     */
    @Test
    public void doubleAceTest()
    {
        Gambler house = new Gambler("house");
        Card card1 = new Card("Diamonds",1,"Ace");
        Card card2 = new Card("Hearts",1,"Ace");
        Card card3 = new Card("Clovers",10,"King");
        Card card4 = new Card("Spades",1,"Ace");
        house.addCardToHand(card1);
        house.addCardToHand(card2);
        assertEquals(12,house.sumOfCards());
        house.addCardToHand(card3);
        assertEquals(12,house.sumOfCards());
        house.addCardToHand(card4);
        assertEquals(13,house.sumOfCards());
    }



}