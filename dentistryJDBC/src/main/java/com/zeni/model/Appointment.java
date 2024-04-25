package com.zeni.model;

public class Appointment {
    private final int appointmentID;
    private final int doctorID;
    private final int patientID;
    private final String appointmentDate;
    
    private Appointment(Builder builder) {
        this.appointmentID = builder.appointmentID;
        this.doctorID = builder.doctorID;
        this.patientID = builder.patientID;
        this.appointmentDate = builder.appointmentDate;
    }
    
    public int getAppointmentID() {
        return appointmentID;
    }
    
    public int getDoctorID() {
        return doctorID;
    }
    
    public int getPatientID() {
        return patientID;
    }
    
    public String getAppointmentDate() {
        return appointmentDate;
    }
    
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }
    
    public static class Builder {
        private int appointmentID;
        private int doctorID;
        private int patientID;
        private String appointmentDate;
        
        public Builder appointmentID(int appointmentID) {
            this.appointmentID = appointmentID;
            return this;
        }
        
        public Builder doctorID(int doctorID) {
            this.doctorID = doctorID;
            return this;
        }
        
        public Builder patientID(int patientID) {
            this.patientID = patientID;
            return this;
        }
        
        public Builder appointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }
        
        public Appointment build() {
            return new Appointment(this);
        }
    }
}
