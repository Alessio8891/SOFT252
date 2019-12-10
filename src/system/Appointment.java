package system;

/**
 * @author zacharysmith
 */
public class Appointment {

    private int doctor;
    private int patient;
    private String[] date;

    /**
     * @param doctor_id
     * @param patient_id
     * @param appointment_dates
     */
    public Appointment(int doctor_id, int patient_id, String[] appointment_dates) {
        this.doctor = doctor_id;
        this.patient = patient_id;
        this.date = appointment_dates;
    }

    /**
     * @return
     */
    public int getDoctor() {
        return doctor;
    }

    /**
     * @param doctor
     */
    public void setDoctor(int doctor) {
        // doctor may be changed if unavailable.
        this.doctor = doctor;
    }

    /**
     * @return
     */
    public String[] getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String[] date) {
        this.date = date;
    }

    /**
     * @return
     */
    public int getPatient() {
        return patient;
    }

}
