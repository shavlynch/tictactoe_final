package com.Unasat;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Game_info {

    public static void saveScore(Connection conn, char winner, int userId) {
        String sql = "INSERT INTO Scores (user_id, score) VALUES (?, ?)";
        int score = (winner == 'X') ? 1 : -1;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            incrementGamesPlayed(conn, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void incrementGamesPlayed(Connection conn, int userId) {
        String sql = "UPDATE Users SET games_played = games_played + 1 WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewTopScores(Connection conn) {
        String sql = "SELECT u.name, s.score FROM Scores s JOIN Users u ON s.user_id = u.user_id ORDER BY s.score DESC LIMIT 10";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Top 10 Scores:");//waarom wil je niet werkennnn
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println("Name: " + name + " - Score: " + score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}