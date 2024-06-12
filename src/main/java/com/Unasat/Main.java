package com.Unasat;

import javax.swing.SwingUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/tictactoe";
    private static final String USER = "root"; //
    private static final String PASSWORD = "root123"; //weet niet echt waar ik dit kan plaatsen... ben vergeten. oh well

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            int userId = -1;

            while (userId == -1) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    userId = User_info.registerUser(conn, scanner);
                    if (userId == -1) {
                        System.out.println("Registration failed. Please try registering again.");
                    }
                } else if (choice == 2) {
                    userId = User_info.loginUser(conn, scanner);
                    if (userId == -1) {
                        System.out.println("Login failed. Please try logging in again.");
                    }
                } else {
                    System.out.println("Invalid choice. Please try again. Maybe then it will work.");
                }
            }

            System.out.println("Login successful!! Starting the game now...");
            TicTacToeGUI game = new TicTacToeGUI(userId);
            game.createAndShowGUI();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}