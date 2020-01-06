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
    private Medicine medicine;
    private int quantity;
    private String dosage;
    private boolean processed;

    public Prescription(Doctor doctor, Patient patient, Medicine medicine, int quantity, String dosage) {
        this.doctor = doctor;
        this.patient = patient;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
        this.processed = false;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
