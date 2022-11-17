package com.unip.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luanz
 */
public class DAO {
    public static Connection connectToDB() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:sqlite:database.db");

        return connection;
    }
    
    public static List<String[]> doQueryOperation(Connection con, String query) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        List<String[]> result = new ArrayList<>();
        
        while(rs.next()) {
            String[] aux = {String.valueOf(rs.getInt("Nivel")), rs.getString("Info")};
            result.add(aux);
        }
        
        stmt.close();
        rs.close();
        
        return result;
    }
}
