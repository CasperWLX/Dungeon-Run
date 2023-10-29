package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.MonsterList;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.shop_logic.Shop;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final Menu MENU = new Menu();
    private final SaveClass GAME_DATA = new SaveClass();
    private final Input INPUT = new Input();
    private final MonsterList MONSTERS = new MonsterList();
    private final Shop shop = new Shop();

    public void run()
    {
        boolean userIsSelecting = true;
        List<CharacterManager> playerList = new ArrayList<>();
        String filename = System.getProperty("user.dir") + "/src/com/hampus/dungeonRun/files/players.dat";

        MENU.welcomeMessage();
        do
        {
            MENU.mainMenu();
            switch(INPUT.getInt())
            {
                case 1 ->
                {
                    CharacterManager player = newGame();
                    GAME_DATA.saveCharacter(player, filename, false);
                    playerList.add(player);
                    MENU.enterName();
                    player.getPLAYER().setName(INPUT.getStringInput());
                    userIsSelecting = false;
                }
                case 2 ->
                {
                    CharacterManager player = GAME_DATA.loadCharacter(filename);

                    if(player != null)
                    {
                        playerList.add(player);
                        MENU.loadedCharacter(player.getPLAYER());
                        userIsSelecting = false;
                    }
                }
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

        do
        {
            MENU.gameMenu();
            switch(INPUT.getInt())
            {
                case 1 -> enterCombat(characterManager, INPUT, filename);
                case 2 -> MENU.printPlayerStats(characterManager);
                case 3 -> shop.buyItems(INPUT, characterManager.getPLAYER());
                case 4 ->
                {
                    MENU.exitGame();
                    userIsSelecting = false;
                }
                default -> MENU.outOfScopeChoice();
            }
        } while(userIsSelecting);

        GAME_DATA.saveCharacter(characterManager, filename, false);
    }

    public CharacterManager newGame()
    {
        return new CharacterManager(new Player(80, 9, 5, 0, 5, 50, 5),
                new Monster(0, 0, 0, 0, 0, 0, 0));
    }

    public void enterCombat(CharacterManager characterManager, Input INPUT, String filename)
    {
        MONSTERS.generateMonster(characterManager);
        MENU.printMonsterName(characterManager);
        MENU.printMonsterStats(characterManager);
        boolean characterIsDead = false;
        boolean combatIsActive = true;
        do
        {
            MENU.combatMenu();
            switch(INPUT.getInt())
            {
                case 1 ->
                {
                    characterManager.getMONSTER().takeDamage(characterManager);
                    if(characterManager.getMONSTER().getHealth() <= 0)
                    {
                        characterManager.getPLAYER().killedMonster();
                        characterManager.getMONSTER().setHealth(0);
                        battleStats(characterManager);
                        MENU.combatSuccess(characterManager);
                        characterManager.getPLAYER().levelUp(MENU, characterManager);
                        combatIsActive = false;
                    }
                    else
                    {
                        characterManager.getPLAYER().takeDamage(characterManager);
                        characterIsDead = isCharacterDead(characterManager, GAME_DATA, filename);
                        battleStats(characterManager);
                    }
                }
                case 2 ->
                {
                    if(characterManager.getPLAYER().didDodge())
                    {
                        MENU.fleeSuccess();
                        combatIsActive = false;
                    }
                    else
                    {
                        MENU.fleeFailed();
                        characterManager.getPLAYER().takeDamage(characterManager);
                        characterIsDead = isCharacterDead(characterManager, GAME_DATA, filename);
                        battleStats(characterManager);
                    }
                }
                case 3 -> MENU.printPlayerStats(characterManager);
                default -> MENU.outOfScopeChoice();
            }
            if(characterIsDead)
            {
                MENU.gameOver(characterManager);
                System.exit(0);
            }
        } while(combatIsActive);
    }

    public boolean isCharacterDead(CharacterManager characterManager, SaveClass gameData, String filename)
    {

        if(characterManager.getPLAYER().getHealth() <= 0)
        {
            characterManager.getPLAYER().setHealth(0);
            gameData.deleteCharacter(filename);
            return true;
        }
        return false;
    }

    public void battleStats(CharacterManager characterManager)
    {
        System.out.printf("%s HP \t\t: %d\n", characterManager.getPLAYER().getName(), characterManager.getPLAYER().getHealth());
        System.out.printf("%s HP \t\t: %d\n", characterManager.getMONSTER().getName(), characterManager.getMONSTER().getHealth());
    }
}
