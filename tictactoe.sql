CREATE DATABASE tictactoe;

USE tictactoe;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    birthdate DATE,
    games_played INT DEFAULT 0
);

CREATE TABLE Scores (
    score_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    score INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

ALTER TABLE Scores ADD COLUMN timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
select * from users;
select * from scores;
