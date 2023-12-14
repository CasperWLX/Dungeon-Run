package com.hampus.dungeonRun.dbLogic;

import com.hampus.dungeonRun.characters.Player;

import java.sql.*;

public class ShopDAO
{
    private final Connection CONNECTION;
    public ShopDAO(Connection connection)
    {
        CONNECTION = connection;
    }

    public void boughtItem(Player player, int itemID)
    {
        if(itemIsAlreadyBought(player, itemID))
        {
            String query = "update inventory set amountBought = amountBought + 1";
            try
            {
                PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("This is not the first time you bought this");
            }
            catch(SQLException sqle)
            {
                System.out.println("Could not update items in database");
                System.out.println(sqle.getMessage());
            }

        }
        else
        {
            String query = "insert into inventory (itemID, playerID, amountBought) values (?,?,?)";
            System.out.println(player.getId());
            try
            {
                PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);

                preparedStatement.setInt(1, itemID);
                preparedStatement.setInt(2, player.getId());
                preparedStatement.setInt(3, 1);
                preparedStatement.executeUpdate();


                System.out.println("This is your first purchase of this item");
            }
            catch(SQLException sqle)
            {
                System.out.println(sqle.getMessage());
            }
        }
    }

    public boolean itemIsAlreadyBought(Player player, int itemID)
    {
        String query = "select itemID from inventory where itemID = " + itemID + " and playerID = '" + player.getId() + "'";
        int count = 0;
        try
        {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                count = resultSet.getInt(1);
            }
            return count == 1;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}
