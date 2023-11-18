package com.hampus.dungeonRun;

import com.hampus.dungeonRun.logic.Game;

/**
 * A text based dungeon game with different enemies based on player-level
 * The game also has 3 different bosses at level 10, 20 and 30
 * The combat is turn-based where the player always go first
 * The player can enter a shop to buy items to become stronger
 * During combat, the player can decide to flee which has a small chance, however you won't have to fight the same enemy again
 * Another way to earn money is by going to the casino and test your luck
 *
 * @author Hampus Källberg
 */
public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        Game game = new Game();
        game.run();
    }
}
