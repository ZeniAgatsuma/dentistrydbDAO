package com.zeni.model;

public class PatientProcedure {
    private final int examinationID;
    private final int patientID;
    private final String examinationDate;
    private final String results;
    
    private PatientProcedure(Builder builder) {
        this.examinationID = builder.examinationID;
        this.patientID = builder.patientID;
        this.examinationDate = builder.examinationDate;
        this.results = builder.results;
    }
    
    public int getExaminationID() {
        return examinationID;
    }
    
    public int getPatientID() {
        return patientID;
    }
    
    public String getExaminationDate() {
        return examinationDate;
    }
    
    public String getResults() {
        return results;
    }
    
    @Override
    public String toString() {
        return "PatientProcedure{" +
                "examinationID=" + examinationID +
                ", patientID=" + patientID +
                ", examinationDate='" + examinationDate + '\'' +
                ", results='" + results + '\'' +
                '}';
    }
    
    public static class Builder {
        private int examinationID;
        private int patientID;
        private String examinationDate;
        private String results;
        
        public Builder examinationID(int examinationID) {
            this.examinationID = examinationID;
            return this;
        }
        
        public Builder patientID(int patientID) {
            this.patientID = patientID;
            return this;
        }
        
        public Builder examinationDate(String examinationDate) {
            this.examinationDate = examinationDate;
            return this;
        }
        
        public Builder results(String results) {
            this.results = results;
            return this;
        }
        
        public PatientProcedure build() {
            return new PatientProcedure(this);
        }
    }
}
