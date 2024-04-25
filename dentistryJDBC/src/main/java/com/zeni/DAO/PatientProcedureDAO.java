package com.zeni.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientProcedureDAO {
    private static PatientProcedureDAO instance;
    private final Connection connection;
    
    private PatientProcedureDAO(Connection connection) {
        this.connection = connection;
    }
    
    public static synchronized PatientProcedureDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new PatientProcedureDAO(connection);
        }
        return instance;
    }
    
    // Фабричний метод для створення PatientProcedureDAO
    public static PatientProcedureDAO createInstance(Connection connection) {
        return new PatientProcedureDAO(connection);
    }
    
    // Метод для додавання процедури пацієнту
    public void addProcedureToPatient(int patientId, int procedureId, Date date) throws SQLException {
        String sql = "INSERT INTO PatientProcedures (PatientID, ProcedureID, Date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, procedureId);
            preparedStatement.setDate(3, date);
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для видалення процедури пацієнту за ідентифікатором
    public void removeProcedureFromPatient(int patientProcedureId) throws SQLException {
        String sql = "DELETE FROM PatientProcedures WHERE PatientProcedureID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, patientProcedureId);
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для отримання процедур для пацієнта за його ідентифікатором
    public List<Integer> getProceduresForPatient(int patientId) throws SQLException {
        List<Integer> procedureIds = new ArrayList<>();
        String sql = "SELECT ProcedureID FROM PatientProcedures WHERE PatientID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, patientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    procedureIds.add(resultSet.getInt("ProcedureID"));
                }
            }
        }
        return procedureIds;
    }
    
    // Метод для отримання пацієнтів для процедури за її ідентифікатором
    public List<Integer> getPatientsForProcedure(int procedureId) throws SQLException {
        List<Integer> patientIds = new ArrayList<>();
        String sql = "SELECT PatientID FROM PatientProcedures WHERE ProcedureID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, procedureId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    patientIds.add(resultSet.getInt("PatientID"));
                }
            }
        }
        return patientIds;
    }
}
