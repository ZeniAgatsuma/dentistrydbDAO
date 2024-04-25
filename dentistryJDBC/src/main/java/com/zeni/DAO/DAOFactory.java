package com.zeni.DAO;

import java.sql.Connection;

public interface DAOFactory {
    DoctorDAO createDoctorDAO();
    ProcedureDAO createProcedureDAO();
    PatientDAO createPatientDAO();
    AppointmentDAO createAppointmentDAO();
    PatientProcedureDAO createPatientProcedureDAO();
}

