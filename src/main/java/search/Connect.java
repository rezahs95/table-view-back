package search;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    Connection crunchifyConn = null;
    Statement crunchifyStmt = null;

    public Statement getSqlStatement() {
        System.out.println("-------- Crunchify's tutorial on Oracle JDBC Connectivity  ------");

        try {
            // Returns the Class object associated with the class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException exception) {
            System.out.println("Oracle Driver Class Not found Exception: " + exception.toString());
            return null;
        }

        // Set connection timeout. Make sure you set this correctly as per your need
        DriverManager.setLoginTimeout(5);
        System.out.println("Oracle JDBC Driver Successfully Registered! Let's make connection now");

        try {
            // Attempts to establish a connection
            // here DB name: REZA, sid: XE
            this.crunchifyConn = DriverManager.getConnection("jdbc:oracle:thin:@REZA:1521:XE", "reza", "rezahastam");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        System.out.println("Connection Success!");

        // Creates a Statement object for sending SQL statements to the database
        try {
            this.crunchifyStmt = crunchifyConn.createStatement();
        } catch (SQLException e) {
            System.out.println("Failed !");
        }
        return this.crunchifyStmt;
    }
}