package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;

public class CombatFlow
{
    private final Menu MENU = new Menu();
    public boolean dealDamage(Player player, Monster monster, SaveClass gameData, String filename)
    {
        monster.takeDamage(player);
        if(monster.getHealth() <= 0)
        {
            player.killedMonster();
            monster.setHealth(0);
            battleStats(player, monster);
            MENU.combatSuccess(monster);
            player.levelUp(MENU, player, monster);
            return false;
        }
        else
        {
            takeDamage(player, monster, gameData,filename);
        }
        battleStats(player,monster);
        return true;
    }
    public boolean escape(Player player, Monster monster, SaveClass gameData, String filename)
    {
        if(player.didDodge(2))
        {
            MENU.fleeSuccess();
            return false;
        }
        else
        {
            MENU.fleeFailed();
            takeDamage(player,monster,gameData,filename);
            battleStats(player,monster);
            if(isCharacterDead(player))
            {
                endGame(player,gameData,filename);
            }
        }
        return true;
    }

    public void takeDamage(Player player, Monster monster, SaveClass gameData, String filename)
    {
        player.takeDamage(monster);
        if(isCharacterDead(player))
        {
            endGame(player,gameData,filename);
        }
    }
    public void battleStats(Player player, Monster monster)
    {
        System.out.printf("%s HP \t\t: %d\n", player.getName(),player.getHealth());
        System.out.printf("%s HP \t\t: %d\n", monster.getName(), monster.getHealth());
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
