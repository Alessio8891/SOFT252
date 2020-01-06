package controller;

import data.Data;
import data.Serialise;
import data.User;
import system.Appointment;
import user.AbstractUser;
import user.Doctor;
import user.Patient;
import view.ComboItem;
import view.GUI;
import view.ListItem;

import javax.swing.*;

public class AppointmentList {
    public static void main(GUI GUI) {
        char authority = User.getUser().getAuthority();

        switch (authority) {
            case 'S':
                GUI.getAppointmentConfirmedCheckbox().setEnabled(true);
                GUI.getAppointmentPatientSelectCombo().setEnabled(true);
                populatePatientSelectCombo(GUI);
                break;
            case 'D':
                GUI.getAppointmentConfirmedCheckbox().setEnabled(false);
                GUI.getAppointmentPatientSelectCombo().setEnabled(true);
                populatePatientSelectCombo(GUI);
                break;
            case 'P':
                GUI.getAppointmentConfirmedCheckbox().setEnabled(false);
                GUI.getAppointmentPatientSelectCombo().setEnabled(false);
                Patient patient = Data.getData().findPatient(User.getUser().getId());
                GUI.getAppointmentPatientSelectCombo().addItem(new ComboItem(patient.getName(), patient));
                break;
        }

        populateDoctorSelectCombo(GUI);
        populate(GUI.getAppointmentList());
        clearAppointment(GUI);
    }

    public static void populateDoctorSelectCombo(GUI GUI) {
        GUI.getAppointmentDoctorSelectCombo().removeAllItems();
        for (int i = 0; i < Data.getData().getUser().size(); i++) {
            AbstractUser user = Data.getData().getUser().get(i);

            if (user.getAuthority() == 'D') {
                GUI.getAppointmentDoctorSelectCombo().addItem(new ComboItem(user.getName(), user));
            }
        }
    }

    public static void populatePatientSelectCombo(GUI GUI) {
        GUI.getAppointmentPatientSelectCombo().removeAllItems();
        for (int i = 0; i < Data.getData().getPatient().size(); i++) {
            Patient patient = Data.getData().getPatient().get(i);

            GUI.getAppointmentPatientSelectCombo().addItem(new ComboItem(patient.getName(), patient));
        }
    }

    public static void populate(JList appointmentList) {
        char authority = User.getUser().getAuthority();
        int id = User.getUser().getId();

        DefaultListModel appointmentListModel = new DefaultListModel();
        appointmentList.setModel(appointmentListModel);

        for (int i = 0; i < Data.getData().getAppointment().size(); i++) {
            Appointment appointment = Data.getData().getAppointment().get(i);

            Doctor doctor = appointment.getDoctor();
            Patient patient = appointment.getPatient();
            String[] date = appointment.getDate();

            ListItem listItem = new ListItem("", appointment);

            String element;
            // only display patients personal data //
            if (authority == 'P' && patient.getId() == id) {
                element = String.format("Doctor: %s, Approved: %s, Date: %s", doctor.getName(), appointment.getApproved(), date[0]);
                listItem.setKey(element);
                appointmentListModel.addElement(listItem);
            }
            // only display doctors own appointments //
            else if (authority == 'D' && doctor.getId() == id) {
                element = String.format("Patient: %s, Approved: %s, Date: %s", patient.getName(), appointment.getApproved(), date[0]);
                listItem.setKey(element);
                appointmentListModel.addElement(listItem);
            }
            // display all appointments to secretaries //
            else if (authority == 'S') {
                element = String.format("Patient: %s, Doctor: %s, Approved: %s, Date: %s", patient.getName(), doctor.getName(), appointment.getApproved(), date[0]);
                listItem.setKey(element);
                appointmentListModel.addElement(listItem);
            }
        }
    }

    public static void newAppointment(GUI GUI) {
        ComboItem selectedDoctor = (ComboItem) GUI.getAppointmentDoctorSelectCombo().getSelectedItem();
        Doctor doctor = (Doctor) selectedDoctor.getValue();

        ComboItem selectedPatient = (ComboItem) GUI.getAppointmentPatientSelectCombo().getSelectedItem();
        Patient patient = (Patient) selectedPatient.getValue();

        String[] dates = new String[]{GUI.getAppointmentEarliestDate().getText(), GUI.getAppointmentLatestDate().getText()};

        Appointment newAppointment = new Appointment(doctor, patient, dates);

        Data.getData().getAppointment().add(newAppointment);
        Serialise.serialise();

        clearAppointment(GUI);
        populate(GUI.getAppointmentList());
    }

    public static void clearAppointment(GUI GUI) {
        GUI.getAppointmentEarliestOrConfirmedLabel().setText("Earliest Date");
        GUI.getAppointmentConfirmedCheckbox().setSelected(false);
        GUI.getAppointmentLatestDate().setText("");
        GUI.getAppointmentEarliestDate().setText("");
    }


    public static void loadAppointment(GUI GUI) {
        ListItem selectedAppointment = (ListItem) GUI.getAppointmentList().getSelectedValue();

        if (selectedAppointment == null) {
            return;
        }

        Appointment appointment = (Appointment) selectedAppointment.getValue();

        GUI.getAppointmentPatientSelectCombo().setEnabled(false);
        GUI.getAppointmentPatientSelectCombo().setSelectedItem(appointment.getPatient());

        GUI.getAppointmentDoctorSelectCombo().setEnabled(true);
        GUI.getAppointmentDoctorSelectCombo().setSelectedItem(appointment.getDoctor());

        if (appointment.getApproved() == true) {
            // load one date
            GUI.getAppointmentEarliestOrConfirmedLabel().setText("Approved Date");

            GUI.getAppointmentEarliestDate().setText(appointment.getDate()[0]);

            GUI.getAppointmentLatestDate().setVisible(false);

            GUI.getAppointmentEarliestDate().setEnabled(false);

            GUI.getAppointmentConfirmedCheckbox().setSelected(true);
        } else {
            // load two dates
            GUI.getAppointmentEarliestOrConfirmedLabel().setText("Earliest Date");

            GUI.getAppointmentEarliestDate().setText(appointment.getDate()[0]);
            GUI.getAppointmentLatestDate().setText(appointment.getDate()[1]);

            GUI.getAppointmentLatestDate().setVisible(true);

            GUI.getAppointmentEarliestDate().setEnabled(true);

            GUI.getAppointmentConfirmedCheckbox().setSelected(false);
        }
    }


    public static void editAppointment(GUI GUI) {
        ComboItem selectedDoctor = (ComboItem) GUI.getAppointmentDoctorSelectCombo().getSelectedItem();
        Doctor doctor = (Doctor) selectedDoctor.getValue();

        String[] dates = new String[]{GUI.getAppointmentEarliestDate().getText(), GUI.getAppointmentLatestDate().getText()};

        boolean approved = GUI.getAppointmentConfirmedCheckbox().isSelected();

        ListItem selectedAppointment = (ListItem) GUI.getAppointmentList().getSelectedValue();
        Appointment appointment = (Appointment) selectedAppointment.getValue();

        appointment.setDate(dates);
        appointment.setApproved(approved);
        appointment.setDoctor(doctor);

        int appointmentIndex = Data.getData().getAppointment().indexOf(appointment);

        Data.getData().getAppointment().set(appointmentIndex, appointment);
        Serialise.serialise();

        clearAppointment(GUI);
        populate(GUI.getAppointmentList());
    }

    public static void deleteAppointment(GUI GUI) {
        ListItem selectedAppointment = (ListItem) GUI.getAppointmentList().getSelectedValue();
        Appointment appointment = (Appointment) selectedAppointment.getValue();

        int appointmentIndex = Data.getData().getAppointment().indexOf(appointment);

        Data.getData().getAppointment().remove(appointmentIndex);
        Serialise.serialise();

        clearAppointment(GUI);
        populate(GUI.getAppointmentList());
    }
}
