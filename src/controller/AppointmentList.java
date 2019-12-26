package controller;

import data.Data;
import system.Appointment;
import user.Doctor;
import user.Patient;

import javax.swing.*;

public class AppointmentList {
    public static void populate(JList appointmentList) {
        DefaultListModel appointmentListModel = new DefaultListModel();
        appointmentList.setModel(appointmentListModel);

        for (int i = 0; i < Data.data.appointment.size(); i++)
        {
            Appointment appointment = Data.data.appointment.get(i);

            Doctor doctor = appointment.getDoctor();
            Patient patient = appointment.getPatient();
            String[] date = appointment.getDate();

            String element = String.format("Patient: %s, Doctor: %s, Date: %s", patient.getName(), doctor.getName(), date);
            appointmentListModel.addElement(element);
        }
    }
}
