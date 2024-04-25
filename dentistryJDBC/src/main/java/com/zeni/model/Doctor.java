package com.zeni.model;

public class Doctor {
    private final int doctorID;
    private final String firstName;
    private final String lastName;
    private final String specialization;
    
    private Doctor(Builder builder) {
        this.doctorID = builder.doctorID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.specialization = builder.specialization;
    }
    
    public int getDoctorID() {
        return doctorID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
    
    public static class Builder {
        private int doctorID;
        private String firstName;
        private String lastName;
        private String specialization;
        
        public Builder doctorID(int doctorID) {
            this.doctorID = doctorID;
            return this;
        }
        
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public Builder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }
        
        public Doctor build() {
            return new Doctor(this);
        }
    }
}
