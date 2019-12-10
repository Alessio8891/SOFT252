package system;

/**
 * @author zacharysmith
 */
public class Prescription {

    private int doctor;
    private int patient;
    private String notes;
    private Medicine medicine;
    private int quantity;
    private String dosage;

    public Prescription(int doctor, int patient, String notes, Medicine medicine, int quantity, String dosage) {
        this.doctor = doctor;
        this.patient = patient;
        this.notes = notes;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public int getDoctor() {
        return doctor;
    }

    public int getPatient() {
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
