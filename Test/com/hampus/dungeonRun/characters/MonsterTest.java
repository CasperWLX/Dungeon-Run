package com.hampus.dungeonRun.characters;

import com.hampus.dungeonRun.shop_logic.Item;
import com.hampus.dungeonRun.shop_logic.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest
{
    Player player = new Player(100, 10, 0, 0, 5, 0, 0);
    Monster monster = new Monster(100, 100, 0, 0, 5, 0, 0);

    /**
     * The test assigns the player a random level and checks that the bossTime condition is only met at 10, 20 or 30
     *
     * !!!THESE TEST AREN'T ADAPTED TO THE DATABASE!!!
     */
    @BeforeEach
    public void setNames()
    {
        player.setName("Player");
        monster.setName("Monster");
    }

    @Test
    public void bossTest()
    {
        int randomLevel = (int) (Math.random() * 28 + 1);
        player.setLevel(randomLevel);
        System.out.println("Current player level: " + player.getLevel());

        while(!player.itsBossTime())
        {
            player.setLevel(player.getLevel() + 1);
            player.levelUp(monster);
        }
        System.out.println("Current player level: " + player.getLevel());
        assertTrue(player.itsBossTime(), "Player is level 10 and ready to fight the boss");
    }

    /**
     * Damage calculations are done by taking randomizing a number based on player strength and then adding the weapon
     * This means that with 0 strength and no weapon, 0 damage should be done
     * And this also means that with 0 strength and a weapon with 10 damage, 10 damage should ALWAYS be done.
     */
    @Test
    public void damageFromWeapon()
    {
        System.out.println("----------TEST WITHOUT WEAPON----------");
        for(int i = 0; i < 100; i++)
        {
            Item weapon = new Weapon("Sword", 10, "Big sword", 0, 1);
            player.setStrength(0);
            player.buyWeapon(weapon, 0);
            monster.calculateDamage(0, player.getCriticalRate(), player.getWeapon(), player.getName());
            assertEquals(monster.getMaxHealth(), monster.getHealth());
        }

        System.out.println("----------TEST WITH WEAPON----------");
        for(int i = 0; i < 100; i++)
        {
            player.setEquippedItem(1);
            monster.calculateDamage(0, player.getCriticalRate(), player.getWeapon(), player.getName());
            assertTrue(monster.getHealth() < monster.getMaxHealth(), "Monster didnt take damage from weapon");
        }
    }
}