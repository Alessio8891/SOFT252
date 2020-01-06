package system;

import user.Doctor;

import java.io.Serializable;

public class PatientNote implements Serializable {

    private Doctor doctor;
    private String note;

    public PatientNote(Doctor doctor, String note) {
        this.doctor = doctor;
        this.note = note;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
