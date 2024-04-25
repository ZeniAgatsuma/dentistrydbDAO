package com.zeni.servicelayer;

import com.zeni.DAO.PatientDAO;
import com.zeni.model.Patient;

import java.sql.SQLException;

public class PatientService {
    private final PatientDAO patientDAO;
    
    public PatientService(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }
    
    public void createPatient(int patientID, String firstName, String lastName, String address, String phone, String email) throws SQLException {
        Patient newPatient = new Patient.Builder()
                .patientID(patientID)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
        patientDAO.createPatient(newPatient);
    }
}
