package system;

import user.Doctor;
import user.Patient;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public class Appointment implements Serializable {

    private Doctor doctor;
    private Patient patient;
    private String[] date;

    /**
     * @param doctor_id
     * @param patient_id
     * @param appointment_dates
     */
    public Appointment(Doctor doctor, Patient patient, String[] appointment_dates) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = appointment_dates;
    }

    /**
     * @return
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor
     */
    public void setDoctor(Doctor doctor) {
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
    public Patient getPatient() {
        return patient;
    }

}
