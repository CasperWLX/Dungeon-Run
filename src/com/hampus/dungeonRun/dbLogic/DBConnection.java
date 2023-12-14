package com.hampus.dungeonRun.dbLogic;

import java.sql.*;


public class DBConnection
{
    private final String URL = "jdbc:mariadb://localhost:3306/dungeonrun";
    private final String USER = "root";
    private final String PASSWORD = "Passw0rd";
    private Connection connection;

    public DBConnection()
    {
        openConnection();
    }

    public void openConnection()
    {
        try
        {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected");
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
        }
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.getMessage());
        }
    }
    public Connection getConnection()
    {
        return connection;
    }
}
