package system;

import user.Doctor;
import user.Patient;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public class Prescription implements Serializable {

    private Doctor doctor;
    private Patient patient;
    private String notes;
    private Medicine medicine;
    private int quantity;
    private String dosage;

    public Prescription(Doctor doctor, Patient patient, String notes, Medicine medicine, int quantity, String dosage) {
        this.doctor = doctor;
        this.patient = patient;
        this.notes = notes;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getNotes() {
        return notes;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * @return
     */
    public String getDosage() {
        return dosage;
    }


}
