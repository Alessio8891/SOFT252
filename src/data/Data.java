package data;

import system.Appointment;
import system.Feedback;
import system.Medicine;
import system.Prescription;
import user.AbstractUser;
import user.Patient;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    public ArrayList<AbstractUser> user;
    public ArrayList<Patient> patient;

    public ArrayList<Appointment> appointment;
    public ArrayList<Feedback> feedback;
    public ArrayList<Medicine> medicine;
    public ArrayList<Prescription> prescription;

    public static Data data;

    public Data() {
        this.user = new ArrayList<>();
        this.patient = new ArrayList<>();

        this.appointment = new ArrayList<>();
        this.feedback = new ArrayList<>();
        this.medicine = new ArrayList<>();
        this.prescription = new ArrayList<>();
    }

    public static Data getData() {
        return data;
    }

    public static void setData(Data dataset) {
        data = dataset;
    }

    public static int getUniqueAdminID() {
        int uniqueID = 1;
        for (int i = 0; i < Data.getData().getUser().size(); i++) {
            AbstractUser user = Data.getData().getUser().get(i);
            if (user.getAuthority() == 'A') {
                uniqueID++;
            }
        }

        return uniqueID;
    }

    public static int getUniqueDoctorID() {
        int uniqueID = 1;
        for (int i = 0; i < Data.getData().getUser().size(); i++) {
            AbstractUser user = Data.getData().getUser().get(i);
            if (user.getAuthority() == 'D') {
                uniqueID++;
            }
        }

        return uniqueID;
    }

    public static int getUniqueSecretaryID() {
        int uniqueID = 1;
        for (int i = 0; i < Data.getData().getUser().size(); i++) {
            AbstractUser user = Data.getData().getUser().get(i);
            if (user.getAuthority() == 'S') {
                uniqueID++;
            }
        }

        return uniqueID;
    }

    public static int getUniquePatientID() {
        // unique ID = number of existing patients + 1
        int uniqueID = Data.getData().getPatient().size() + 1;

        return uniqueID;
    }

    public ArrayList<AbstractUser> getUser() {
        return user;
    }

    public AbstractUser findUser(char authority, int id) {
        for (int i = 0; i < Data.getData().getUser().size(); i++) {
            AbstractUser x = Data.getData().getUser().get(i);
            if (x.getAuthority() == authority && x.getId() == id) {
                return x;
            }
        }
        return null;
    }

    public Patient findPatient(int id) {
        for (int i = 0; i < Data.getData().getPatient().size(); i++) {
            Patient x = Data.getData().getPatient().get(i);
            if (x.getId() == id) {
                return x;
            }
        }
        return null;
    }

    public ArrayList<Patient> getPatient() {
        return patient;
    }

    public ArrayList<Appointment> getAppointment() {
        return appointment;
    }

    public ArrayList<Feedback> getFeedback() {
        return feedback;
    }

    public ArrayList<Medicine> getMedicine() {
        return medicine;
    }

    public ArrayList<Prescription> getPrescription() {
        return prescription;
    }
}