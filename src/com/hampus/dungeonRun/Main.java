package com.hampus.dungeonRun;

import com.hampus.dungeonRun.logic.Game;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        Game game = new Game();
        game.run();
    }
}
