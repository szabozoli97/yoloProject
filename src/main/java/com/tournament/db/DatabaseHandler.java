package com.tournament.db;

import java.sql.*;

public final class DatabaseHandler {
    private static DatabaseHandler databaseHandler;


    private static final String DB_URL = "jdbc:h2:./database/myDB";
    private static Connection conn = null;
    private static Statement stmt = null;

    public  DatabaseHandler(){
        createConnection();
        setupTeamTable();
        setupPlayerTable();
    }

    void createConnection(){
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Exception at setup: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Exception at setup: " + e.getMessage());
        }
    }

    void setupTeamTable() {
        String tableName = "TEAM";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbM = conn.getMetaData();
            ResultSet tables = dbM.getTables(null, null, tableName.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + tableName + " already exists. Everything is ready!");
            } else {
                stmt.execute("CREATE TABLE " + tableName + "("
                        + "             TeamID  INT IDENTITY(1,1) PRIMARY KEY, \n"
                        + "             TeamName VARCHAR(30) NOT NULL"
                        + "             )");
            }
        } catch (SQLException e) {
            System.err.println("Exception at setup: " + e.getMessage());
        }
    }

    void setupPlayerTable(){
        String tableName = "PLAYER";
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbM= conn.getMetaData();
            ResultSet tables = dbM.getTables(null, null, tableName.toUpperCase(), null);

            if (tables.next())
            {
                System.out.println("Table " + tableName + " already exists. TABLES ARE READY!");
            }
            else
            {
                stmt.execute("CREATE TABLE " + tableName + "("
                        + "             PlayerID  INT IDENTITY(1,1) PRIMARY KEY, \n"
                        + "             PlayerName VARCHAR(30) NOT NULL, \n"
                        + "             Birth DATE , \n"
                        + "             TeamID INT REFERENCES TEAM"
                        + "             )" );
            }
        }
        catch (SQLException e)
        {
            System.err.println("Exception at setup: " + e.getMessage());
        }

    }

    public ResultSet executeQuery(String query){
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception at executeQuery: " + e.getMessage());
            return null;
        }
        return rs;
    }

    public boolean executeAction(String query){
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            System.err.println("Exception at executeQuery: " + e.getMessage());
            return false;
        }

    }
}


