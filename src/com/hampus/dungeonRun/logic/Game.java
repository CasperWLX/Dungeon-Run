package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Player;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    Menu menu = new Menu();

    public void run()
    {
        Input input = new Input();
        boolean userIsSelecting = true;
        List<CharacterManager> playerList = new ArrayList<>();

        menu.welcomeMessage();
        menu.firstMenu();

        do
        {
            switch(input.getInt())
            {
                case 1 ->
                {
                    CharacterManager player = newGame();
                    playerList.add(player);
                    userIsSelecting = false;
                }
                case 2 ->
                {
                    CharacterManager player = loadGame();
                    playerList.add(player);
                    userIsSelecting = false;
                }
                case 3 ->
                {
                    System.out.println("Ok thanks for playing!");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid number");
            }
        } while(userIsSelecting);


        playerList.get(0).getPlayer().getStats();

    }

    public CharacterManager newGame()
    {
        return new CharacterManager(new Player(100, 5, 8, 0, 5, 10, 5));
    }

    public CharacterManager loadGame()
    {
        return new CharacterManager(new Player(100, 5, 8, 0, 5, 10, 5));
    }

}
