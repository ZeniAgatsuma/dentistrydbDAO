package com.zeni.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Параметри підключення до бази даних SQLite
    private static final String URL = "jdbc:sqlite:C:\\Users\\yuraz\\Desktop\\SqliteExpert\\dentistry.db"; // замініть на шлях до вашої бази даних SQLite
    private static final String USERNAME = "com/zeni"; // ваше ім'я користувача
    private static final String PASSWORD = "198237#Yura"; // ваш пароль
    
    // Метод для отримання з'єднання з базою даних
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

