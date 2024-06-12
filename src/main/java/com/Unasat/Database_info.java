package com.Unasat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database_info {
    public static boolean userExists(Connection conn, String name) throws SQLException {
        String query = "SELECT * FROM Users WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public static int getUserId(Connection conn, String name) throws SQLException {
        String query = "SELECT user_id FROM Users WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        }
        return -1;
    }
}//ur also useless atm
