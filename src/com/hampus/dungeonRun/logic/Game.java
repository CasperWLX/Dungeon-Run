package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.casino.Casino;
import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.dbLogic.DBConnection;
import com.hampus.dungeonRun.shop_logic.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the main game options.
 */
public class Game
{
    private final Menu MENU = new Menu();
    private final Input INPUT = new Input();
    private final CombatFlow combat = new CombatFlow();
    private final Casino CASINO = new Casino();
    private final DBConnection database = new DBConnection();

    public void run()
    {
        database.openConnection();
        boolean userIsSelecting = true;
        List<CharacterManager> playerList = new ArrayList<>();

        MENU.welcomeMessage();
        do
        {
            MENU.mainMenu();
            switch(INPUT.getInt())
            {
                case 1 -> userIsSelecting = newGame(playerList);
                case 2 -> userIsSelecting = loadGame(playerList);
                case 3 ->
                {
                    MENU.exitGame();
                    System.exit(0);
                }
                default -> MENU.outOfScopeChoice();
            }
        } while(userIsSelecting);

        userIsSelecting = true;
        CharacterManager characterManager = playerList.get(0);
        Shop shop = characterManager.getSHOP();

        do
        {
            MENU.gameMenu();
            switch(INPUT.getInt())
            {
                case 1 -> combat.enterCombat(characterManager, INPUT);
                case 2 -> MENU.printPlayerStats(characterManager.getPLAYER().getStats());
                case 3 -> shop.buyItems(characterManager.getPLAYER(), INPUT);
                case 4 ->
                {
                    if(characterManager.getPLAYER().getGold() > 0)
                    {
                        CASINO.casinoMenu(characterManager.getPLAYER());
                    }
                    else
                    {
                        MENU.notEnoughGold();
                    }
                }
                case 5 ->
                {
                    if(characterManager.getPLAYER().getLIST_OF_WEAPONS().isEmpty())
                    {
                        MENU.noWeapons();
                    }
                    else
                    {
                        characterManager.getPLAYER().chooseItemToEquip(INPUT);
                    }
                }
                case 6 -> userIsSelecting = false;
                default -> MENU.outOfScopeChoice();
            }
        } while(userIsSelecting);

        System.out.println(database.updatePlayer(characterManager.getPLAYER()));
        database.closeConnection();
        MENU.exitGame();
    }

    public boolean newGame(List<CharacterManager> list)
    {
        String name;
        CharacterManager characterManager = new CharacterManager(
                new Player(80, 9, 5, 0, 5, 50, 5),
                new Monster(0, 0, 0, 0, 0, 0, 0),
                new Shop());

        MENU.enterName();
        name = INPUT.getStringInput();
        list.add(characterManager);
        characterManager.getPLAYER().setName(name);
        System.out.println(database.addPlayerToDatabase(characterManager));
        return false;
    }

    public boolean loadGame(List<CharacterManager> list)
    {
        System.out.println("Enter the ID of the character you want to load in");
        int id = INPUT.getInt();
        CharacterManager characterManager = new CharacterManager(new Player(), new Monster(), new Shop());
        if(database.loadPlayerFromDatabase(characterManager, id))
        {
            list.add(characterManager);
            return false;
        }
        else
        {
            return true;
        }
    }
}
