package com.zeni.DAO;

import com.zeni.model.Procedure;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDAO {
    private static ProcedureDAO instance;
    private final Connection connection;
    
    private ProcedureDAO(Connection connection) {
        this.connection = connection;
    }
    
    // Сінглтон для отримання єдиного екземпляру класу ProcedureDAO
    public static synchronized ProcedureDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new ProcedureDAO(connection);
        }
        return instance;
    }
    
    // Фабричний метод для створення ProcedureDAO
    public static ProcedureDAO createInstance(Connection connection) {
        return new ProcedureDAO(connection);
    }
    
    // Метод для створення процедури
    public void createProcedure(Procedure procedure) throws SQLException {
        String sql = "INSERT INTO Procedures (PatientID, ProcedureDate, Description) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, procedure.getPatientID());
            preparedStatement.setString(2, procedure.getProcedureDate());
            preparedStatement.setString(3, procedure.getDescription());
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для оновлення процедури
    public void updateProcedure(Procedure procedure) throws SQLException {
        String sql = "UPDATE Procedures SET PatientID=?, ProcedureDate=?, Description=? WHERE ProcedureID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, procedure.getPatientID());
            preparedStatement.setString(2, procedure.getProcedureDate());
            preparedStatement.setString(3, procedure.getDescription());
            preparedStatement.setInt(4, procedure.getProcedureID());
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для видалення процедури за ідентифікатором
    public void deleteProcedure(int procedureId) throws SQLException {
        String sql = "DELETE FROM Procedures WHERE ProcedureID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, procedureId);
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для отримання процедури за ідентифікатором
    public Procedure getProcedureById(int procedureId) throws SQLException {
        String sql = "SELECT * FROM Procedures WHERE ProcedureID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, procedureId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Procedure.Builder()
                            .procedureID(resultSet.getInt("ProcedureID"))
                            .patientID(resultSet.getInt("PatientID"))
                            .procedureDate(resultSet.getString("ProcedureDate"))
                            .description(resultSet.getString("Description"))
                            .build();
                }
            }
        }
        return null;
    }
    
    // Метод для отримання всіх процедур
    public List<Procedure> getAllProcedures() throws SQLException {
        List<Procedure> procedures = new ArrayList<>();
        String sql = "SELECT * FROM Procedures";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Procedure procedure = new Procedure.Builder()
                        .procedureID(resultSet.getInt("ProcedureID"))
                        .patientID(resultSet.getInt("PatientID"))
                        .procedureDate(resultSet.getString("ProcedureDate"))
                        .description(resultSet.getString("Description"))
                        .build();
                procedures.add(procedure);
            }
        }
        return procedures;
    }
}
