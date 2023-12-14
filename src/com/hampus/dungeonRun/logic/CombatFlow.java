package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.MonsterList;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.dbLogic.BattleHistoryDAO;
import com.hampus.dungeonRun.dbLogic.MonsterDAO;
import com.hampus.dungeonRun.dbLogic.PlayerDAO;

/**
 * The class that sets up the combat flow for each combat.
 * This class also checks if the player is dead
 */
public class CombatFlow
{
    private final Menu MENU = new Menu();
    private final MonsterList MONSTERS = new MonsterList();
    private final PlayerDAO PLAYER_DAO;
    private final MonsterDAO MONSTER_DAO;
    private final BattleHistoryDAO BATTLE_HISTORY_DAO;

    public CombatFlow(PlayerDAO playerDAO, MonsterDAO monsterDAO, BattleHistoryDAO battleHistoryDAO)
    {
        this.PLAYER_DAO = playerDAO;
        this.MONSTER_DAO = monsterDAO;
        this.BATTLE_HISTORY_DAO = battleHistoryDAO;
    }

    public void enterCombat(CharacterManager characterManager, Input INPUT)
    {
        MONSTERS.generateMonster(characterManager);
        MENU.printMonsterStats(characterManager.getMONSTER());
        System.out.println(MONSTER_DAO.insertMonsterToDatabase(characterManager));
        boolean combatIsActive = true;
        do
        {
            MENU.combatMenu();
            switch(INPUT.getInt())
            {
                case 1 ->
                        combatIsActive = dealDamage(characterManager.getPLAYER(), characterManager.getMONSTER());
                case 2 ->
                        combatIsActive = escape(characterManager.getPLAYER(), characterManager.getMONSTER());
                case 3 -> MENU.printPlayerStats(characterManager.getPLAYER().getStats());
                default -> MENU.outOfScopeChoice();
            }
        } while(combatIsActive);
    }

    public boolean dealDamage(Player player, Monster monster)
    {
        monster.calculateDamage(player.getStrength(), player.getCriticalRate(), player.getWeapon(), player.getName());
        if(monster.getHealth() <= 0)
        {
            player.killedMonster();
            monster.setHealth(0);
            MENU.printBattleStats(player.getName(), monster.getName(), player.getHealth(), monster.getHealth());
            MENU.combatSuccess(monster);
            player.levelUp(monster);
            System.out.println(BATTLE_HISTORY_DAO.insertBattle(player, monster,"Player Won"));
            return false;
        }
        else
        {
            takeDamage(player, monster);
        }
        MENU.printBattleStats(player.getName(), monster.getName(), player.getHealth(), monster.getHealth());
        return true;
    }

    public boolean escape(Player player, Monster monster)
    {
        //Fleeing is based on agility and the possibility of a success is very low
        if(player.didDodge(2))
        {
            MENU.fleeSuccess();
            System.out.println(BATTLE_HISTORY_DAO.insertBattle(player,monster,"Player Fled"));
            return false;
        }
        else
        {
            MENU.fleeFailed();
            takeDamage(player, monster);
            MENU.printBattleStats(player.getName(), monster.getName(), player.getHealth(), monster.getHealth());
            if(isCharacterDead(player))
            {
                endGame(player);
            }
        }
        return true;
    }

    public void takeDamage(Player player, Monster monster)
    {
        player.calculateDamage(monster.getStrength(), monster.getName(), monster.getCriticalRate());
        if(isCharacterDead(player))
        {
            BATTLE_HISTORY_DAO.insertBattle(player,monster,"Player lost");
            endGame(player);
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

    public void endGame(Player player)
    {
        MENU.gameOver(player);
        PLAYER_DAO.updatePlayer(player);
        System.exit(0);
    }
}
