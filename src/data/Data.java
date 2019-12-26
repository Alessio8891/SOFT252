package data;

import system.Appointment;
import system.Feedback;
import system.Medicine;
import system.Prescription;
import user.Administrator;
import user.Doctor;
import user.Patient;
import user.Secretary;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    public ArrayList<Patient> patient;
    public ArrayList<Doctor> doctor;
    public ArrayList<Secretary> secretary;
    public ArrayList<Administrator> admin;

    public ArrayList<Appointment> appointment;
    public ArrayList<Feedback> feedback;
    public ArrayList<Medicine> medicine;
    public ArrayList<Prescription> prescription;

    public static Data data;

    public Data() {
        this.patient = new ArrayList<>();
        this.doctor = new ArrayList<>();
        this.secretary = new ArrayList<>();
        this.admin = new ArrayList<>();

        this.appointment = new ArrayList<>();
        this.feedback = new ArrayList<>();
        this.medicine = new ArrayList<>();
        this.prescription = new ArrayList<>();
    }
}