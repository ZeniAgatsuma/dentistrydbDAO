package com.zeni.DAO;

import java.sql.Connection;

public class MySQLDAOFactory implements DAOFactory {
    private final Connection connection;
    
    public MySQLDAOFactory(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public DoctorDAO createDoctorDAO() {
        return DoctorDAO.getInstance(connection);
    }
    
    @Override
    public ProcedureDAO createProcedureDAO() {
        return ProcedureDAO.getInstance(connection);
    }
    
    @Override
    public PatientDAO createPatientDAO() {
        return PatientDAO.getInstance(connection);
    }
    
    @Override
    public AppointmentDAO createAppointmentDAO() {
        return AppointmentDAO.getInstance(connection);
    }
    
    @Override
    public PatientProcedureDAO createPatientProcedureDAO() {
        return PatientProcedureDAO.getInstance(connection);
    }
}

