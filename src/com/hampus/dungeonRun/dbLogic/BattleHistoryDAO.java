package com.hampus.dungeonRun.dbLogic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Monster;
import com.hampus.dungeonRun.characters.Player;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public void getBattleHistory(CharacterManager characterManager)
    {
        List<String> battleHistory = new ArrayList<>();
        String query = "SELECT p.name as player_name, m.name as monster_name, resultOfBattle, timeOfBattle from battleHistory bh join player p on p.playerID = bh.playerID join monster m on m.monsterID = bh.monsterID where p.playerID = " + characterManager.getPLAYER().getId();

        try
        {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                battleHistory.add(resultSet.getString("player_name"));
                battleHistory.add(resultSet.getString("monster_name"));
                battleHistory.add(resultSet.getString("resultOfBattle"));
                battleHistory.add(resultSet.getString("timeOfBattle"));
            }

            for(int i = 0; i < battleHistory.size(); i += 4)
            {
                System.out.printf("%s vs %s. Result : %s | Time of battle : %s\n", battleHistory.get(i), battleHistory.get(i+1), battleHistory.get(i+2), battleHistory.get(i+3));
            }
        }catch(SQLException sqle)
        {
            System.out.println("Could not print battle history");
        }
    }
}
