package com.hampus.dungeonRun.characters;

/**
 * A class that dictates which monster can be run into during the game
 * The class also contains all the possible monsters and their respective multipliers
 * It will also send a boss trigger when the level requirement is met
 */
public class MonsterList
{
    private int goldMultiplier;
    private int criticalHitRate;
    private double statsMultiplier;
    private String name;

    public void generateMonster(CharacterManager characterManager)
    {
        Monster monster = characterManager.getMONSTER();
        Player player = characterManager.getPLAYER();
        if(player.itsBossTime())
        {
            monster.createBoss(player.getLevel());
            player.setBossTime(false);
        }
        else
        {
            int randomNumber = monsterBasedOnPlayerLevel(player);

            switch(randomNumber)
            {
                case 1, 2, 3, 4, 5, 6 -> createGoblin(monster, player);
                case 7, 8, 9 -> createWolf(monster, player);
                case 10, 11, 12, 13, 14, 15 -> createTroll(monster, player);
                case 16, 17, 18, 19 -> createCyclops(monster, player);
                case 20, 21, 22, 23, 24, 25 -> createGolem(monster, player);
                case 26, 27, 28, 29 -> createMinotaur(monster, player);
                default -> createDragon(monster, player);
            }
        }
    }

    public void createGoblin(Monster monster, Player player)
    {
        statsMultiplier = 0.4;
        goldMultiplier = 3;
        criticalHitRate = 2;
        name = "Goblin";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public void createWolf(Monster monster, Player player)
    {
        statsMultiplier = 0.5;
        goldMultiplier = 1;
        criticalHitRate = 3;
        name = "Wolf";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public void createTroll(Monster monster, Player player)
    {
        statsMultiplier = 0.6;
        goldMultiplier = 6;
        criticalHitRate = 5;
        name = "Troll";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public void createCyclops(Monster monster, Player player)
    {
        statsMultiplier = 0.8;
        goldMultiplier = 10;
        criticalHitRate = 7;
        name = "Cyclops";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public void createGolem(Monster monster, Player player)
    {
        statsMultiplier = 0.9;
        goldMultiplier = 12;
        criticalHitRate = 8;
        name = "Golem";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public void createMinotaur(Monster monster, Player player)
    {
        statsMultiplier = 1;
        goldMultiplier = 16;
        criticalHitRate = 9;
        name = "Minotaur";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public void createDragon(Monster monster, Player player)
    {
        statsMultiplier = 1.2;
        goldMultiplier = 24;
        criticalHitRate = 15;
        name = "Dragon";
        monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
    }

    public int monsterBasedOnPlayerLevel(Player player)
    {
        int playerLevel = player.getLevel();
        return (int) (Math.random() * playerLevel + 1);
    }
}
