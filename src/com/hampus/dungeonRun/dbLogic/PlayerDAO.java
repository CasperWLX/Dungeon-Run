package com.hampus.dungeonRun.dbLogic;

import com.hampus.dungeonRun.characters.CharacterManager;
import com.hampus.dungeonRun.characters.Player;
import com.hampus.dungeonRun.shop_logic.Item;

import java.sql.*;

public class PlayerDAO
{
    private final Connection CONNECTION;
    public PlayerDAO(Connection connection)
    {
        CONNECTION = connection;
    }

    public String addPlayerToDatabase(CharacterManager characterManager)
    {
        String query = "INSERT INTO player(name, playerLevel, health, maxHealth, experience, requiredExperience, strength, agility, gold, criticalHitrate, incomingBossbattle, numberOfKills, equippedItemID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, characterManager.getPLAYER().getName());
            preparedStatement.setInt(2, characterManager.getPLAYER().getLevel());
            preparedStatement.setInt(3, characterManager.getPLAYER().getHealth());
            preparedStatement.setInt(4, characterManager.getPLAYER().getMaxHealth());
            preparedStatement.setInt(5, characterManager.getPLAYER().getExperience());
            preparedStatement.setInt(6, characterManager.getPLAYER().getRequiredExperience());
            preparedStatement.setInt(7, characterManager.getPLAYER().getStrength());
            preparedStatement.setInt(8, characterManager.getPLAYER().getAgility());
            preparedStatement.setInt(9, characterManager.getPLAYER().getGold());
            preparedStatement.setInt(10, characterManager.getPLAYER().getCriticalRate());
            preparedStatement.setBoolean(11, characterManager.getPLAYER().itsBossTime());
            preparedStatement.setInt(12, characterManager.getPLAYER().getNoOfKills());
            preparedStatement.setInt(13, characterManager.getPLAYER().getEquippedItemID());
            preparedStatement.executeUpdate();

            ResultSet returnedGeneratedKeys = preparedStatement.getGeneratedKeys();
            if(returnedGeneratedKeys.next())
            {
                characterManager.getPLAYER().setId(returnedGeneratedKeys.getInt(1));
            }
            loadItemsToShop(characterManager);
            return "player added to DataBase";
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return "could not add player to DataBase";
        }
    }
    public boolean loadPlayerFromDatabase(CharacterManager characterManager, int id)
    {
        String query = "select * from player where playerID = ?";
        try
        {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                characterManager.getPLAYER().setId(resultSet.getInt("playerID"));
                characterManager.getPLAYER().setName(resultSet.getString("name"));
                characterManager.getPLAYER().setLevel(resultSet.getInt("playerLevel"));
                characterManager.getPLAYER().setHealth(resultSet.getInt("health"));
                characterManager.getPLAYER().setMaxHealth(resultSet.getInt("maxHealth"));
                characterManager.getPLAYER().setExperience(resultSet.getInt("experience"));
                characterManager.getPLAYER().setRequiredExperience(resultSet.getInt("requiredExperience"));
                characterManager.getPLAYER().setStrength(resultSet.getInt("strength"));
                characterManager.getPLAYER().setAgility(resultSet.getInt("agility"));
                characterManager.getPLAYER().setGold(resultSet.getInt("gold"));
                characterManager.getPLAYER().setCriticalRate(resultSet.getInt("criticalHitrate"));
                characterManager.getPLAYER().setBossTime(resultSet.getBoolean("incomingBossbattle"));
                characterManager.getPLAYER().setNoOfKills(resultSet.getInt("numberOfKills"));
                characterManager.getPLAYER().setEquippedItemID(resultSet.getInt("equippedItemID"));
            }
            preparedStatement.close();
            resultSet.close();
            if(characterManager.getPLAYER().getName() == null)
            {
                System.out.println("Could not find character");
                return false;
            }
            else if(characterManager.getPLAYER().getHealth() == 0)
            {
                System.out.println("Can't load in a dead character");
                return false;
            }
            else
            {
                System.out.println("Welcome back : " + characterManager.getPLAYER().getName());
                return loadItemsToShop(characterManager);
            }
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    public boolean loadItemsToShop(CharacterManager characterManager)
    {
        String query = "select * from item";
        try
        {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            for(Item item : characterManager.getSHOP().getListOfItems())
            {
                if(resultSet.next())
                {
                    item.setItemID(resultSet.getInt("itemID"));
                    item.setName(resultSet.getString("name"));
                    item.setValue(resultSet.getInt("itemValue"));
                    item.setDescription(resultSet.getString("description"));
                    item.setCost(resultSet.getInt("price"));
                    item.setStockAmount(resultSet.getInt("quantity"));
                }
            }
            resultSet.close();
            statement.close();
            System.out.println("Shop loaded");
            return loadStockToShop(characterManager);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    public boolean loadStockToShop(CharacterManager characterManager)
    {
        String query = "select amountBought,itemID from inventory where playerID = " + characterManager.getPLAYER().getId();
        try
        {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                for(Item item : characterManager.getSHOP().getListOfItems())
                {
                    if(item.getItemID() == resultSet.getInt("itemID"))
                    {
                        item.setStockAmount(item.getStockAmount() - resultSet.getInt("amountBought"));
                        System.out.println("Item stock updated");
                    }
                }
            }
            return loadPlayerInventory(characterManager);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    public boolean loadPlayerInventory(CharacterManager characterManager)
    {
        String query = "select * from inventory where playerID = " + characterManager.getPLAYER().getId();

        try
        {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                if(resultSet.getInt("itemID") > 3)
                {
                    characterManager.getPLAYER().addWeaponToInventory(characterManager.getSHOP().getListOfItems().get(resultSet.getInt("itemID") - 1));
                }
            }
            int counter = 1;
            for(Item item : characterManager.getPLAYER().getLIST_OF_WEAPONS())
            {
                if(item.getItemID() == characterManager.getPLAYER().getEquippedItemID())
                {
                    characterManager.getPLAYER().setEquippedItem(counter);
                }
            }

            System.out.println("Items loaded to inventory");
            return true;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
    public String updatePlayer(Player player)
    {
        String query = "UPDATE player " +
                "SET name = ?, playerLevel = ?, health = ?, maxHealth = ?, " +
                "experience = ?, requiredExperience = ?, strength = ?, " +
                "agility = ?, gold = ?, criticalHitrate = ?, " +
                "incomingBossbattle = ?, numberOfKills = ?, equippedItemID = ? ," +
                "numberOfKills = ? " +
                "WHERE playerID = ?";
        try
        {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);

            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, player.getLevel());
            preparedStatement.setInt(3, player.getHealth());
            preparedStatement.setInt(4, player.getMaxHealth());
            preparedStatement.setInt(5, player.getExperience());
            preparedStatement.setInt(6, player.getRequiredExperience());
            preparedStatement.setInt(7, player.getStrength());
            preparedStatement.setInt(8, player.getAgility());
            preparedStatement.setInt(9, player.getGold());
            preparedStatement.setInt(10, player.getCriticalRate());
            preparedStatement.setBoolean(11, player.itsBossTime());
            preparedStatement.setInt(12, player.getNoOfKills());
            preparedStatement.setInt(13, player.getEquippedItemID());
            preparedStatement.setInt(14, player.getNoOfKills());

            preparedStatement.setInt(15, player.getId());

            preparedStatement.executeUpdate();

            return "Player updated";
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return "Something went wrong when updating player";
        }
    }
}
