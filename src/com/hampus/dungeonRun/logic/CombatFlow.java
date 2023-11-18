package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.MonsterList;
import com.hampus.dungeonRun.characters.Player;

/**
 * The class that sets up the combat flow for each combat.
 * This class also checks if the player is dead
 */
public class CombatFlow
{
    private final Menu MENU = new Menu();
    private final MonsterList MONSTERS = new MonsterList();

    public void enterCombat(CharacterManager characterManager, Input INPUT, String filename, SaveClass gameData)
    {
        MONSTERS.generateMonster(characterManager);
        MENU.printMonsterStats(characterManager.getMONSTER());
        boolean combatIsActive = true;
        do
        {
            MENU.combatMenu();
            switch(INPUT.getInt())
            {
                case 1 ->
                        combatIsActive = dealDamage(characterManager.getPLAYER(), characterManager.getMONSTER(), gameData, filename);
                case 2 ->
                        combatIsActive = escape(characterManager.getPLAYER(), characterManager.getMONSTER(), gameData, filename);
                case 3 -> MENU.printPlayerStats(characterManager.getPLAYER().getStats());
                default -> MENU.outOfScopeChoice();
            }
        } while(combatIsActive);
    }

    public boolean dealDamage(Player player, Monster monster, SaveClass gameData, String filename)
    {
        monster.calculateDamage(player.getStrength(), player.getCriticalRate(), player.getWeapon(), player.getName());
        if(monster.getHealth() <= 0)
        {
            player.killedMonster();
            monster.setHealth(0);
            MENU.printBattleStats(player.getName(), monster.getName(), player.getHealth(), monster.getHealth());
            MENU.combatSuccess(monster);
            player.levelUp(monster);
            return false;
        }
        else
        {
            takeDamage(player, monster, gameData, filename);
        }
        MENU.printBattleStats(player.getName(), monster.getName(), player.getHealth(), monster.getHealth());
        return true;
    }

    public boolean escape(Player player, Monster monster, SaveClass gameData, String filename)
    {
        //Fleeing is based on agility and the possibility of a success is very low
        if(player.didDodge(2))
        {
            MENU.fleeSuccess();
            return false;
        }
        else
        {
            MENU.fleeFailed();
            takeDamage(player, monster, gameData, filename);
            MENU.printBattleStats(player.getName(), monster.getName(), player.getHealth(), monster.getHealth());
            if(isCharacterDead(player))
            {
                endGame(player, gameData, filename);
            }
        }
        return true;
    }

    public void takeDamage(Player player, Monster monster, SaveClass gameData, String filename)
    {
        player.calculateDamage(monster.getStrength(), monster.getName(), monster.getCriticalRate());
        if(isCharacterDead(player))
        {
            endGame(player, gameData, filename);
        }
    }

    public boolean isCharacterDead(Player player)
    {
        if(player.getHealth() <= 0)
        {
            player.setHealth(0);
            return true;
        }
        return false;
    }

    public void endGame(Player player, SaveClass gameData, String filename)
    {
        MENU.gameOver(player);
        gameData.deleteCharacter(filename);
        System.exit(0);
    }
}
