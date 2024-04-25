package com.zeni.DAO;

import com.zeni.model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private static AppointmentDAO instance;
    private final Connection connection;
    
    // Приватний конструктор для заборони створення екземплярів AppointmentDAO за межами класу
    private AppointmentDAO(Connection connection) {
        this.connection = connection;
    }
    
    // Статичний метод для отримання єдиного екземпляру AppointmentDAO
    public static synchronized AppointmentDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new AppointmentDAO(connection);
        }
        return instance;
    }
    
    // Фабричний метод для створення AppointmentDAO
    public static AppointmentDAO createInstance(Connection connection) {
        return new AppointmentDAO(connection);
    }
    
    // Метод для оновлення призначення
    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE Appointments SET DoctorID=?, PatientID=?, AppointmentDate=? WHERE AppointmentID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, appointment.getDoctorID());
            preparedStatement.setInt(2, appointment.getPatientID());
            preparedStatement.setString(3, appointment.getAppointmentDate());
            preparedStatement.setInt(4, appointment.getAppointmentID());
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для видалення призначення за ідентифікатором
    public void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM Appointments WHERE AppointmentID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, appointmentId);
            preparedStatement.executeUpdate();
        }
    }
    
    // Метод для отримання призначення за ідентифікатором
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM Appointments WHERE AppointmentID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, appointmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Appointment.Builder()
                            .appointmentID(resultSet.getInt("AppointmentID"))
                            .doctorID(resultSet.getInt("DoctorID"))
                            .patientID(resultSet.getInt("PatientID"))
                            .appointmentDate(resultSet.getString("AppointmentDate"))
                            .build();
                }
            }
        }
        return null;
    }
    
    // Метод для отримання всіх призначень
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Appointment appointment = new Appointment.Builder()
                        .appointmentID(resultSet.getInt("AppointmentID"))
                        .doctorID(resultSet.getInt("DoctorID"))
                        .patientID(resultSet.getInt("PatientID"))
                        .appointmentDate(resultSet.getString("AppointmentDate"))
                        .build();
                appointments.add(appointment);
            }
        }
        return appointments;
    }
    
    // Метод для отримання призначень за ідентифікатором пацієнта
    public List<Appointment> getAppointmentsByPatientId(int patientId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments WHERE PatientID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, patientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment.Builder()
                            .appointmentID(resultSet.getInt("AppointmentID"))
                            .doctorID(resultSet.getInt("DoctorID"))
                            .patientID(resultSet.getInt("PatientID"))
                            .appointmentDate(resultSet.getString("AppointmentDate"))
                            .build();
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
    }
    
    // Метод для отримання призначень за ідентифікатором лікаря
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments WHERE DoctorID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, doctorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment.Builder()
                            .appointmentID(resultSet.getInt("AppointmentID"))
                            .doctorID(resultSet.getInt("DoctorID"))
                            .patientID(resultSet.getInt("PatientID"))
                            .appointmentDate(resultSet.getString("AppointmentDate"))
                            .build();
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
    }
}
