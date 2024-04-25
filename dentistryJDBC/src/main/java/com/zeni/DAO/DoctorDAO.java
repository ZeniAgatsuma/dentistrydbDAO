package com.zeni.DAO;

import com.zeni.model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static DoctorDAO instance;
    private final Connection connection;
    
    private DoctorDAO(Connection connection) {
        this.connection = connection;
    }
    
    public static synchronized DoctorDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new DoctorDAO(connection);
        }
        return instance;
    }
    
    // Фабричний метод для створення DoctorDAO
    public static DoctorDAO createInstance(Connection connection) {
        return new DoctorDAO(connection);
    }
    
    // Метод для створення лікаря
    public void createDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO Doctors (FirstName, LastName, Specialization) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, doctor.getFirstName());
            preparedStatement.setString(2, doctor.getLastName());
            preparedStatement.setString(3, doctor.getSpecialization());
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для оновлення лікаря
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE Doctors SET FirstName=?, LastName=?, Specialization=? WHERE DoctorID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, doctor.getFirstName());
            preparedStatement.setString(2, doctor.getLastName());
            preparedStatement.setString(3, doctor.getSpecialization());
            preparedStatement.setInt(4, doctor.getDoctorID());
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для видалення лікаря за ідентифікатором
    public void deleteDoctor(int doctorId) throws SQLException {
        String sql = "DELETE FROM Doctors WHERE DoctorID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для отримання лікаря за ідентифікатором
    public Doctor getDoctorById(int doctorId) throws SQLException {
        String sql = "SELECT * FROM Doctors WHERE DoctorID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, doctorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Doctor.Builder()
                            .doctorID(resultSet.getInt("DoctorID"))
                            .firstName(resultSet.getString("FirstName"))
                            .lastName(resultSet.getString("LastName"))
                            .specialization(resultSet.getString("Specialization"))
                            .build();
                }
            }
        }
        return null;
    }
    
    // Метод для отримання всіх лікарів
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor.Builder()
                        .doctorID(resultSet.getInt("DoctorID"))
                        .firstName(resultSet.getString("FirstName"))
                        .lastName(resultSet.getString("LastName"))
                        .specialization(resultSet.getString("Specialization"))
                        .build();
                doctors.add(doctor);
            }
        }
        return doctors;
    }
}
