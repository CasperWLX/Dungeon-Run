package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final Menu MENU = new Menu();
    private final SaveClass GAME_DATA = new SaveClass();
    private final Input INPUT = new Input();

    public void run()
    {

        boolean userIsSelecting = true;
        List<CharacterManager> playerList = new ArrayList<>();
        String filename = "C:/Users/Hampus/IdeaProjects/JavaB/JavaB_Labb/src/com/hampus/dungeonRun/files/players.dat";

        MENU.welcomeMessage();
        MENU.mainMenu();

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
                    if(player != null)
                    {
                        System.out.println("-Welcome back player-");
                        userIsSelecting = false;
                    }
                    else
                    {
                        System.out.println("Oops, looks like the character doesn't exist");
                    }
                }
                case 3 ->
                {
                    System.out.println("Ok thanks for playing!");
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
                case 1 -> enterCombat(characterManager, INPUT);
                case 2 ->
                {
                    System.out.println("check stats");
                    characterManager.getPlayer().getStats();
                }
                case 3 -> System.out.println("shop");
                case 4 ->
                {
                    System.out.println("exit game");
                    userIsSelecting = false;
                }
            }

        } while(userIsSelecting);

        GAME_DATA.saveCharacter(characterManager, filename);
    }

    public CharacterManager newGame()
    {
        return new CharacterManager(new Player(100, 10, 8, 0, 5, 10, 5), new Monster(40, 3, 5, 0, 4, 2, 4));
    }

    public void enterCombat(CharacterManager characterManager, Input INPUT)
    {
        randomizeMonsterStats(characterManager);
        characterManager.getMonster().getStats();
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
                        characterManager.getMonster().setHealth(0);
                        System.out.println("YOU DEFEATED THE MONSTER!");
                        gainEXP(); //FIXA DENNA
                        combatIsActive = false;
                    }
                    else
                    {
                        characterManager.getPlayer().takeDamage(characterManager);
                    }
                    System.out.printf("Player HP \t:\t %d\n", characterManager.getPlayer().getHealth());
                    System.out.printf("Monster HP \t:\t %d\n", characterManager.getMonster().getHealth());
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
                case 3 -> characterManager.getPlayer().getStats();
            }
        } while(combatIsActive);
    }

    public void randomizeMonsterStats(CharacterManager monsterStats)
    {
        int randomUp = (int) (Math.random() * 1 + 1);
        int randomDown = (int) (Math.random() * 2 + 2);
        int monsterHealth = (int) (((monsterStats.getPlayer().getHealth() * 0.6) - randomDown + randomUp) * 1.3);
        int monsterStrength = monsterStats.getPlayer().getStrength() - randomDown + randomUp;
        int monsterAgility = monsterStats.getPlayer().getAgility() - randomDown + randomUp;
        int monsterLevel = monsterStats.getPlayer().getLevel() - randomDown + randomUp;
        int monsterGold = monsterLevel / 2;
        int monsterCriticalRate = (int) (Math.random() * 15 + 1);
        monsterStats.getMonster().setStats(monsterHealth, monsterStrength, monsterAgility, 0, monsterLevel, monsterGold, monsterCriticalRate);
    }
    public void gainEXP(){

    }

    //TODO - eventuell ide för att generera random monster.
    /*
    public void generateMonster(){
        switch(randomNumber){
            case 1,2,3,4,5 -> createGoblin();
            case 6,7,8 -> createTroll();
            case 9,10 -> createGolem();
            case 11 -> createDragon();
        }
    }

    public int generateNumberInRange()
    {
        int baseValue = 100;
        double lowerPercent = 0.8;
        double upperPercent = 1.2;
        int lowerBound = (int)(baseValue * lowerPercent)
        int upperBound = (int)(baseValue * upperPercent)
        int randomNumber = (int) (generateRandomNumber(lowerBound, upperBound))
        return randomNumber;

    }
    public static double generateRandomNumber(int lowerBound, int upperBound) {
        Random rand = new Random();
        int randomOffset = (upperBound - lowerBound) * rand.nextDouble();
        return lowerBound + randomOffset;
    }
    */

}
