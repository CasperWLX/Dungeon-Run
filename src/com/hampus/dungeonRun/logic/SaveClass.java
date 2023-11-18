package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.*;

import java.io.*;

/**
 * The class that saves the game to a dat file
 */
public class SaveClass
{
    public void saveCharacter(CharacterManager character, String filename, boolean isDead)
    {
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut))
        {
            objectOut.writeObject(character);
            if(!isDead)
            {
                System.out.println("Character saved to " + filename);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Could not save the character, " +
                    "please check that the files are in the correct spot\n" +
                    "Aborting program");
            System.exit(0);
        }
    }

    public CharacterManager loadCharacter(String filename)
    {
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn))
        {
            CharacterManager character = (CharacterManager) objectIn.readObject();
            System.out.println("Character loaded from " + filename);
            return character;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void deleteCharacter(String filename)
    {
        try
        {
            new FileOutputStream(filename).close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
