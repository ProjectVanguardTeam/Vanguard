package me.jack.projectvanguardcore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

    private String host = "na02-db.cus.mc-panel.net";
    private String port = "3306";
    private String database = "db_115528";
    private String user = "db_115528";
    private String password = "da2ade92e5";

    private Connection conn;

    public boolean isConnected(){
        return (conn == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            conn = DriverManager.getConnection("jdbc:mysql://" +
                    host + ":" + port + "/" + database + "?useSSL=false", user, password);
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return conn;
    }
}