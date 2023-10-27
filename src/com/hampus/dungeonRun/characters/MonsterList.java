package com.hampus.dungeonRun.characters;

import java.util.Random;

public class MonsterList
{
    public void generateMonster(CharacterManager characterManager)
    {
        int randomNumber = numberBasedOnPlayerLevel(characterManager);
        switch(randomNumber)
        {
            case 1,2,3,4,5 -> {
                double goblinMultiplier = 0.3;
                int goldMultiplier = 3;
                int critRate = 2;
                characterManager.getMonster().setName("Goblin");
                createMonster(characterManager, goblinMultiplier, goldMultiplier, critRate);
            }
            case 6,7,8 -> {
                double trollMultiplier = 0.6;
                int goldMultiplier = 6;
                int critRate = 5;
                characterManager.getMonster().setName("Troll");
                createMonster(characterManager, trollMultiplier, goldMultiplier, critRate);
            }
            case 9,10 -> {
                double golemMultiplier = 0.9;
                int goldMultiplier = 12;
                int critRate = 10;
                characterManager.getMonster().setName("Golem");
                createMonster(characterManager, golemMultiplier, goldMultiplier, critRate);
            }
            case 11 -> {
                double dragonMultiplier = 1.2;
                int goldMultiplier = 24;
                int critRate = 15;
                characterManager.getMonster().setName("Dragon");
                createMonster(characterManager, dragonMultiplier, goldMultiplier, critRate);
            }
        }
    }
    public void createMonster(CharacterManager monsterStats, double statsMultiplier, int goldMultiplier, int critRate)
    {
        monsterStats.getMonster().setHealth(generateNumberInRange((int) (monsterStats.getPlayer().getMaxHealth() * statsMultiplier)));
        monsterStats.getMonster().setStrength(generateNumberInRange((int) (monsterStats.getPlayer().getStrength() * statsMultiplier)));
        monsterStats.getMonster().setAgility(generateNumberInRange((int) (monsterStats.getPlayer().getAgility() * statsMultiplier)));
        monsterStats.getMonster().setLevel(generateNumberInRange((int) (monsterStats.getPlayer().getLevel() * statsMultiplier)));
        monsterStats.getMonster().setExperience(generateNumberInRange((int) (monsterStats.getMonster().getLevel() * 10.5)));
        monsterStats.getMonster().setGold(generateNumberInRange(goldMultiplier));
        monsterStats.getMonster().setCriticalRate(generateNumberInRange(critRate));
    }
    public void createBoss()
    {

    }
    public int numberBasedOnPlayerLevel(CharacterManager characterManager)
    {
        int playerLevel = characterManager.getPlayer().getLevel();
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
    public int generateNumberInRange(int baseValue)
    {
        double lowerPercent = 0.8;
        double upperPercent = 1.2;
        int lowerBound = (int)(baseValue * lowerPercent);
        int upperBound = (int)(baseValue * upperPercent);
        int randomNumber = (int) (generateRandomNumber(lowerBound, upperBound));
        if(randomNumber == 0)
        {
            randomNumber = 1;
        }
        return randomNumber;

    }
    public static double generateRandomNumber(int lowerBound, int upperBound) {
        Random rand = new Random();
        int randomOffset = (int) ((upperBound - lowerBound) * rand.nextDouble());
        return lowerBound + randomOffset;
    }
}
