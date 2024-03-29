package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.casino.Casino;
import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.shop_logic.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the main game options.
 */
public class Game
{
    private final Menu MENU = new Menu();
    private final SaveClass GAME_DATA = new SaveClass();
    private final Input INPUT = new Input();
    private final CombatFlow combat = new CombatFlow();
    private final Casino CASINO = new Casino();
    private final String FILENAME = System.getProperty("user.dir") + "/src/com/hampus/dungeonRun/files/players.dat";

    public void run()
    {
        boolean userIsSelecting = true;
        List<CharacterManager> playerList = new ArrayList<>();

        MENU.welcomeMessage();
        do
        {
            MENU.mainMenu();
            switch(INPUT.getInt())
            {
                case 1 -> userIsSelecting = newGame(playerList, FILENAME);
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
                case 1 -> combat.enterCombat(characterManager, INPUT, FILENAME, GAME_DATA);
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

        MENU.exitGame();
        GAME_DATA.saveCharacter(characterManager, FILENAME, false);
    }

    public boolean newGame(List<CharacterManager> list, String filename)
    {
        String name;
        CharacterManager characterManager = new CharacterManager(
                new Player(80, 9, 5, 0, 5, 50, 5),
                new Monster(0, 0, 0, 0, 0, 0, 0),
                new Shop());

        MENU.enterName();
        name = INPUT.getStringInput();
        GAME_DATA.saveCharacter(characterManager, filename, false);
        list.add(characterManager);
        characterManager.getPLAYER().setName(name);
        return false;
    }

    public boolean loadGame(List<CharacterManager> list)
    {
        boolean characterDoesNotExist;
        CharacterManager characterManager = GAME_DATA.loadCharacter(FILENAME);
        try
        {
            characterDoesNotExist = characterManager.getPLAYER().getName().isBlank();

        }
        catch(NullPointerException npe)
        {
            System.out.println("There is no saved character. Please try another option");
            return true;
        }
        if(!characterDoesNotExist)
        {
            MENU.loadedCharacter(characterManager.getPLAYER());
            list.add(characterManager);
            return false;
        }
        return true;
    }
}
