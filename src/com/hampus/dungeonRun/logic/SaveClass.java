package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


//TODO Eventuellt kika på entity SQL spara filer.
public class SaveClass
{
    public void saveCharacter(CharacterManager character, String filename)
    {
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut))
        {
            objectOut.writeObject(character);
            System.out.println("Character saved to " + filename);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public CharacterManager loadCharacter(String filename)
    {
        CharacterManager character = null;
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn))
        {
            character = (CharacterManager) objectIn.readObject();
            System.out.println("Character loaded from " + filename);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return character;
    }

    /**
     * Method that sleeps the program for 2 seconds
     */
    public void waitTwoSeconds()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
