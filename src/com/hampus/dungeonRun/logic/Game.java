package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Player;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final Menu MENU = new Menu();
    private final GameData GAME_DATA = new GameData();
    private final Input INPUT = new Input();

    public void run()
    {

        boolean userIsSelecting = true;
        List<CharacterManager> playerList = new ArrayList<>();
        String filename = "C:/Users/Hampus/IdeaProjects/JavaB/JavaB_Labb/src/com/hampus/dungeonRun/files/players.dat";

        MENU.welcomeMessage();
        MENU.firstMenu();

        do
        {
            switch(INPUT.getInt())
            {
                case 1 ->
                {
                    CharacterManager player = newGame();
                    GAME_DATA.saveCharacter(player, filename);
                    playerList.add(player);
                    userIsSelecting = false;

                }
                case 2 ->
                {
                    CharacterManager player = GAME_DATA.loadCharacter(filename);
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
        CharacterManager characterManager = playerList.get(0);
        characterManager.getPlayer().getStats();
        System.out.println("You killed this many monsters last round");
        System.out.println(characterManager.getPlayer().getNoOfKills());
        System.out.println("Wow this is really cool, give the character some gold!");
        characterManager.getPlayer().setGold(INPUT.getInt());

        System.out.println("How many mobs did you kill?");
        characterManager.getPlayer().setNoOfKills(INPUT.getInt());
        System.out.println("Thanks for saving!");

        GAME_DATA.saveCharacter(characterManager,filename);
    }

    public CharacterManager newGame()
    {
        return new CharacterManager(new Player(100, 5, 8, 0, 5, 10, 5));
    }

}
