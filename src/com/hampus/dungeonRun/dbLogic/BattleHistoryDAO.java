package com.hampus.dungeonRun.dbLogic;

import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BattleHistoryDAO
{
    private final Connection CONNECTION;
    public BattleHistoryDAO(Connection connection)
    {
        CONNECTION = connection;
    }

    public String insertBattle(Player player, Monster monster, String result)
    {
        String query = "INSERT INTO battleHistory(playerID, monsterID, resultOfBattle,timeOfBattle) values (?,?,?,?)";
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentDateTime.format(formatter);
        try
        {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);

            preparedStatement.setInt(1,player.getId());
            preparedStatement.setInt(2,monster.getId());
            preparedStatement.setString(3,result);
            preparedStatement.setString(4,formattedTime);
            preparedStatement.executeUpdate();
            return "Battle history added";
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return "Could not add battle history";
        }
    }
}
