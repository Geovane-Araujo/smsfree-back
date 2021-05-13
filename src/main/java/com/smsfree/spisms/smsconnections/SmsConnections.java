package com.smsfree.spisms.smsconnections;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SmsConnections {
    private String url = "jdbc:postgresql://localhost:5432/";

    public String getUrl() {
        return url;
    }

    public boolean openSessionConnections(String db) {

        boolean ret;

        try {
            String url = getUrl()+db;
            Class.forName("org.postgresql.Driver");

            String senha = "1816";
            DriverManager.getConnection(url, "postgres", senha);
            ret = true;
        } catch (SQLException | ClassNotFoundException e) {
            ret = false;
            System.out.println(e);
        }

        return ret;
    }

    public Connection getNewConnections(String db) throws SQLException {

        Connection con = null;
        String url = getUrl()+db;
        try {
            String senha = "1816" +
                    "";
            con = DriverManager.getConnection(url,"postgres",senha);
        }
        catch(SQLException e) {
            System.out.println(e);

        }
        return con;
    }
}
