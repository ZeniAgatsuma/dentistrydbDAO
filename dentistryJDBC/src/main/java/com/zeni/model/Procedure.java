package com.zeni.model;

public class Procedure {
    private final int procedureID;
    private final int patientID;
    private final String procedureDate;
    private final String description;
    
    private Procedure(Builder builder) {
        this.procedureID = builder.procedureID;
        this.patientID = builder.patientID;
        this.procedureDate = builder.procedureDate;
        this.description = builder.description;
    }
    
    public int getProcedureID() {
        return procedureID;
    }
    
    public int getPatientID() {
        return patientID;
    }
    
    public String getProcedureDate() {
        return procedureDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "Procedure{" +
                "procedureID=" + procedureID +
                ", patientID=" + patientID +
                ", procedureDate='" + procedureDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    
    public static class Builder {
        private int procedureID;
        private int patientID;
        private String procedureDate;
        private String description;
        
        public Builder procedureID(int procedureID) {
            this.procedureID = procedureID;
            return this;
        }
        
        public Builder patientID(int patientID) {
            this.patientID = patientID;
            return this;
        }
        
        public Builder procedureDate(String procedureDate) {
            this.procedureDate = procedureDate;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Procedure build() {
            return new Procedure(this);
        }
    }
}
