package com.hampus.dungeonRun.characters;

public class MonsterList
{
    public void generateMonster(CharacterManager characterManager)
    {
        Monster monster = characterManager.getMONSTER();
        Player player = characterManager.getPLAYER();
        if(player.itsBossTime())
        {
            createBoss(player, monster);
            player.setBossTime(false);
        }
        else
        {
            int randomNumber = monsterBasedOnPlayerLevel(player);

            int goldMultiplier;
            int criticalHitRate;
            double statsMultiplier;
            String name;
            switch(randomNumber)
            {
                case 1,2,3,4,5 -> {
                    statsMultiplier = 0.4;
                    goldMultiplier = 3;
                    criticalHitRate = 2;
                    name = "Goblin";
                    monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
                }
                case 6,7,8 -> {
                    statsMultiplier = 0.6;
                    goldMultiplier = 6;
                    criticalHitRate = 5;
                    name = "Troll";
                    monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
                }
                case 9,10 -> {
                    statsMultiplier = 0.9;
                    goldMultiplier = 12;
                    criticalHitRate = 10;
                    name = "Golem";
                    monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
                }
                case 11 -> {
                    statsMultiplier = 1.2;
                    goldMultiplier = 24;
                    criticalHitRate = 15;
                    name = "Dragon";
                    monster.generateMonster(player, statsMultiplier, goldMultiplier, criticalHitRate, name);
                }
            }
        }

    }
    public void createBoss(Player player, Monster monster)
    {
        switch(player.getLevel())
        {
            case 10 -> monster.setStats("Hobgoblin",120,8,10,100,12,100,7);
            case 20 -> monster.setStats("Ogre",250,15,0,400,25,300,5);
            case 30 -> monster.setStats("Giant",500,25,0,3000,40,1000,0);
        }
    }
    public int monsterBasedOnPlayerLevel(Player player)
    {
        int playerLevel = player.getLevel();
        if(playerLevel < 10){
            return (int)(Math.random() * 5 + 1);
        }
        if(playerLevel < 20)
        {
            return (int)(Math.random() * 8 + 1);
        }
        if(playerLevel < 30){
            return (int)(Math.random() * 10 + 1);
        }
        else
        {
            return (int)(Math.random() * 11 + 1);
        }
    }
}
