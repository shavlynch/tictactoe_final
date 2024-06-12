package com.Unasat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User_info {

    public static int registerUser(Connection conn, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        String birthdate = scanner.nextLine();

        String query = "INSERT INTO Users (name, password, birthdate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setDate(3, Date.valueOf(birthdate));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("User registered successfully.");
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: User already exists. Make a new user!");
        }
        return -1;
    }

    public static int loginUser(Connection conn, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String query = "SELECT user_id FROM Users WHERE name = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful.");
                return rs.getInt("user_id");  //
            } else {
                System.out.println("Invalid name or password. Try again >.<");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}