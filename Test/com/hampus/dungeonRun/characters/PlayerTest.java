package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.logic.CombatFlow;
import com.hampus.dungeonRun.logic.SaveClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    CombatFlow combatFlow = new CombatFlow();
    Player player = new Player(100,10,0,0,5,0,0);
    Monster monster = new Monster(100,100,0,10,5,0,0);

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
        player.setExperience(80);
        player.levelUp(monster);
        assertEquals(6,player.getLevel());
    }

    /**
     * Tests if player takes damage
     */
    @Test
    public void characterCanTakeDamage()
    {
        player.takeDamage(monster.getStrength(),
                player.getName(),
                monster.getName(),
                monster.getCriticalRate());

        assertTrue(player.getHealth() < player.getMaxHealth());
    }

    /**
     * Tests if player damage is within calculations
     */
    @Test
    public void damageIsWithinCalculations()
    {
        for (int i = 0; i < 1000; i++)
        {
            assertTrue(monster.randomDamage(player.getStrength()) <= player.getStrength() + 3);
        }
    }

    /**
     * Test if player can die
     */
    @Test
    public void canPlayerDie()
    {
        SaveClass gameData = new SaveClass();
        String filename = System.getProperty("user.dir") + "/src/com/hampus/dungeonRun/files/players.dat";
        combatFlow.takeDamage(player,monster,gameData,filename);
        assertEquals(0,player.getHealth());
    }
}