package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.MonsterList;
import com.hampus.dungeonRun.characters.Player;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final Menu MENU = new Menu();
    private final SaveClass GAME_DATA = new SaveClass();
    private final Input INPUT = new Input();
    private final MonsterList MONSTERS = new MonsterList();

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
                    GAME_DATA.saveCharacter(player, filename,false);
                    playerList.add(player);
                    MENU.enterName();
                    player.getPlayer().setName(INPUT.getStringInput());
                    userIsSelecting = false;

                }
                case 2 ->
                {
                    CharacterManager player = GAME_DATA.loadCharacter(filename);
                    try
                    {
                        if(player.getPlayer().getName() != null || !player.getPlayer().getName().isEmpty())
                        {
                            playerList.add(player);
                            System.out.printf("-Welcome back %s-\n", player.getPlayer().getName());
                            userIsSelecting = false;
                        }
                    }
                    catch(NullPointerException npe)
                    {
                        MENU.noSaveFile();
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
                case 1 -> enterCombat(characterManager, INPUT,filename);
                case 2 -> MENU.printPlayerStats(characterManager);
                case 3 -> System.out.println("shop");
                case 4 ->
                {
                    MENU.exitGame();
                    userIsSelecting = false;
                }
                default -> MENU.outOfScopeChoice();
            }

        } while(userIsSelecting);

        GAME_DATA.saveCharacter(characterManager, filename,false);
    }

    public CharacterManager newGame()
    {
        return new CharacterManager(new Player(80, 9, 5, 0, 5, 50, 5),
                new Monster(40, 3, 5, 0, 4, 2, 4));
    }

    public void enterCombat(CharacterManager characterManager, Input INPUT, String filename)
    {
        MONSTERS.generateMonster(characterManager);
        MENU.printMonsterName(characterManager);
        MENU.printMonsterStats(characterManager);
        boolean combatIsActive = true;
        do
        {
            MENU.combatMenu();
            switch(INPUT.getInt())
            {
                case 1 ->
                {
                    characterManager.getMonster().takeDamage(characterManager);
                    if(characterManager.getMonster().getHealth() <= 0)
                    {
                        characterManager.getPlayer().killedMonster();
                        characterManager.getMonster().setHealth(0);
                        MENU.combatSuccess(characterManager);
                        characterManager.getPlayer().levelUp(MENU, characterManager);
                        combatIsActive = false;
                    }
                    else
                    {
                        characterManager.getPlayer().takeDamage(characterManager);
                        System.out.printf("%s HP \t:\t %d\n", characterManager.getPlayer().getName(),characterManager.getPlayer().getHealth());
                        System.out.printf("%s HP \t:\t %d\n", characterManager.getMonster().getName(),characterManager.getMonster().getHealth());
                        isCharacterDead(characterManager, GAME_DATA,filename);
                    }

                }
                case 2 ->
                {
                    if(characterManager.getPlayer().didDodge(characterManager))
                    {
                        MENU.fleeSuccess();
                        combatIsActive = false;
                    }
                    else
                    {
                        MENU.fleeFailed();
                        characterManager.getPlayer().takeDamage(characterManager);
                    }
                }
                case 3 -> MENU.printPlayerStats(characterManager);
                default -> MENU.outOfScopeChoice();
            }
        } while(combatIsActive);
    }
    public void isCharacterDead(CharacterManager characterManager, SaveClass gameData, String filename)
    {
        if(characterManager.getPlayer().getHealth() <= 0)
        {
            characterManager.getPlayer().setHealth(0);
            MENU.gameOver(characterManager);
            gameData.deleteCharacter(filename);

            System.exit(0);
        }

    }
}
