package com.hampus.dungeonRun.dbLogic;

import com.hampus.dungeonRun.characters.CharacterManager;

import java.sql.*;

public class MonsterDAO
{
    private final Connection CONNECTION;
    public MonsterDAO(Connection connection)
    {
        CONNECTION = connection;
    }

    public String insertMonsterToDatabase(CharacterManager characterManager)
    {
        String query = "INSERT INTO monster(name, monsterLevel, health, maxHealth, experienceGained, strength, agility, goldGained, criticalHitrate) VALUES(?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, characterManager.getMONSTER().getName());
            preparedStatement.setInt(2, characterManager.getMONSTER().getLevel());
            preparedStatement.setInt(3, characterManager.getMONSTER().getHealth());
            preparedStatement.setInt(4, characterManager.getMONSTER().getMaxHealth());
            preparedStatement.setInt(5, characterManager.getMONSTER().getExperience());
            preparedStatement.setInt(6, characterManager.getMONSTER().getStrength());
            preparedStatement.setInt(7, characterManager.getMONSTER().getAgility());
            preparedStatement.setInt(8, characterManager.getMONSTER().getGold());
            preparedStatement.setInt(9, characterManager.getMONSTER().getCriticalRate());

            preparedStatement.executeUpdate();

            ResultSet returnedGeneratedKeys = preparedStatement.getGeneratedKeys();
            if(returnedGeneratedKeys.next())
            {
                characterManager.getMONSTER().setId(returnedGeneratedKeys.getInt(1));
            }
            return "Monster added to database";
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return "could not add monster to DataBase";
        }
    }

}
