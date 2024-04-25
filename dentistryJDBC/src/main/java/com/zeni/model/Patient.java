package com.zeni.model;

public class Patient {
    private final int patientID;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String email;
    
    private Patient(Builder builder) {
        this.patientID = builder.patientID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
    }
    
    public int getPatientID() {
        return patientID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    public static class Builder {
        private int patientID;
        private String firstName;
        private String lastName;
        private String address;
        private String phone;
        private String email;
        
        public Builder patientID(int patientID) {
            this.patientID = patientID;
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
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Patient build() {
            return new Patient(this);
        }
    }
}
