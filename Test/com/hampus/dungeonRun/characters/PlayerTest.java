package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.dbLogic.*;
import com.hampus.dungeonRun.logic.CombatFlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    DBConnection db = new DBConnection();
    MonsterDAO monsterDAO = new MonsterDAO(db.getConnection());
    PlayerDAO playerDAO = new PlayerDAO(db.getConnection());
    BattleHistoryDAO battleHistoryDAO = new BattleHistoryDAO(db.getConnection());
    CombatFlow combatFlow = new CombatFlow(playerDAO, monsterDAO, battleHistoryDAO);
    Player player = new Player(100, 10, 0, 0, 5, 0, 0);
    Monster monster = new Monster(100, 100, 0, 0, 5, 0, 0);

    /**
     * Adds names before each test
     */
    @BeforeEach
    public void setNames()
    {
        player.setName("PLAYER");
        monster.setName("MONSTER");
    }

    /**
     * Test if player can level up
     */
    @Test
    public void levelUp()
    {
        // Case 1: player should not level up with insufficient xp
        player.setExperience(49);
        player.levelUp(monster);
        assertEquals(5, player.getLevel(), "Player should not have leveled up");

        // Case 2: player should level up with exactly enough xp
        player.setExperience(50);
        player.levelUp(monster);
        assertEquals(6, player.getLevel(), "Player did not level up correctly");

        // Case 3: player should level up with more than enough xp
        player.setExperience(player.getExperience() + 100);
        player.levelUp(monster);
        assertEquals(7, player.getLevel(), "Player did not level up correctly");

    }

    /**
     * Tests what happens if the players gets too much experience
     */
    @Test
    public void multiLevelUp()
    {
        player.setExperience(700);
        player.levelUp(monster);
        assertTrue(player.getLevel() > 6, "Player only leveled up once");
    }

    /**
     * Tests if player takes damage
     */
    @Test
    public void characterCanTakeDamage()
    {
        monster.setStrength(10);
        player.takeDamage(monster.getStrength(),
                player.getName(),
                monster.getName(),
                monster.getCriticalRate());
        assertTrue(player.getHealth() < player.getMaxHealth(), "Player didn't take damage");
    }

    /**
     * Tests if player damage is within calculations.
     * Damage is randomized between 0.8 to 1.2 of the attackers strength
     * That means the first test should always succeed since the conditions are fulfilled
     * The second test can fail because the damage CAN be outside the allowed conditions (But since it random
     * it could also succeed)
     */
    @Test
    public void damageIsWithinCalculations()
    {
        //This should always succeed
        for(int i = 9; i < 1000; i++) //We're using 9 since it's the base damage for the game
        {
            for(int j = 0; j < 50; j++) //Another loop to test the same number multiple times
            {
                int damage = monster.randomizeStats(i);
                int upperLimit = (int) (i * 1.2); //This is the limit that is inside the game
                int lowerLimit = (int) (i * 0.8); //This is the limit that is inside the game
                assertFalse(damage < lowerLimit || damage > upperLimit, "Damage done : " + i + " upper and lower: " + upperLimit + " " + lowerLimit);
            }
        }
    }

    /**
     * Tests if player is dead
     */
    @Test
    public void isPlayerDead()
    {
        //This should always succeed if player health is less than 1
        player.setHealth(0);
        //Returns true if player health is 0 or below
        assertTrue(combatFlow.isCharacterDead(player), "player has 0 hp");

        //This should fail with any number above 0
        player.setHealth(1);
        //Returns true if player health is 0
        assertFalse(combatFlow.isCharacterDead(player), "player counts as dead");
    }
}