package com.hampus.dungeonRun.casino;

public class Card
{
    private final String FACE_CARD;
    private final String SUIT;
    private int value;
    public Card(String suit, int value, String faceCard)
    {
        this.SUIT = suit;
        if(value > 10)
        {
            this.FACE_CARD = faceCard;
            this.value = 10;
        }
        else if (value == 1)
        {
            this.FACE_CARD = faceCard;
            this.value = 11;
        }
        else
        {
            this.FACE_CARD = faceCard;
            this.value = value;
        }
    }

    @Override
    public String toString()
    {
        if(!FACE_CARD.isEmpty())
        {
            return FACE_CARD + " of " + SUIT;
        }
        else
        {
            return value + " of " + SUIT;
        }
    }
    public int getValue(){
        return value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
}

