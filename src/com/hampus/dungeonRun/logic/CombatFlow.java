package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;

public class CombatFlow
{
    private final Menu MENU = new Menu();
    public boolean dealDamage(Player player, Monster monster, SaveClass gameData, String filename)
    {
        monster.calculateDamage(player.getStrength(),player.getCriticalRate(),player.getWeapon(),player.getName());
        if(monster.getHealth() <= 0)
        {
            player.killedMonster();
            monster.setHealth(0);
            MENU.printBattleStats(player.getName(),monster.getName(),player.getHealth(),monster.getHealth());
            MENU.combatSuccess(monster);
            player.levelUp(MENU, player, monster);
            return false;
        }
        else
        {
            takeDamage(player, monster, gameData,filename);
        }
        MENU.printBattleStats(player.getName(),monster.getName(),player.getHealth(),monster.getHealth());
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
            MENU.printBattleStats(player.getName(),monster.getName(),player.getHealth(),monster.getHealth());
            if(isCharacterDead(player))
            {
                endGame(player,gameData,filename);
            }
        }
        return true;
    }

    public void takeDamage(Player player, Monster monster, SaveClass gameData, String filename)
    {
        player.calculateDamage(monster.getStrength(),monster.getName(), monster.getCriticalRate());
        if(isCharacterDead(player))
        {
            endGame(player,gameData,filename);
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
