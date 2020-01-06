package controller;

import data.Deserialise;
import view.GUI;

public class Controller {
    public static void main() {
        // load dataset //
        Deserialise.deserialise();
    }

    public static void tabChangeState(GUI GUI) {
        int index = GUI.getTabbedPane().getSelectedIndex();

        // do not trigger when removing tabs (bc index is -1, and invalid)
        if (index >= 0) {
            switch (GUI.getTabbedPane().getTitleAt(index)) {
                case "Login":
                case "Register":
                    break;
                case "Settings":
                    Settings.main(GUI);
                    break;
                case "Appointment List":
                    AppointmentList.main(GUI);
                    break;
                case "Feedback List":
                    FeedbackList.main(GUI);
                    break;
                case "Medicine List":
                    MedicineList.main(GUI);
                    break;
                case "Patient View":
                    PatientView.main(GUI);
                    break;
                case "Prescription List":
                    PrescriptionList.main(GUI);
                    break;
                case "User List":
                    UserList.main(GUI);
                    break;
            }
        }
    }

    public static void updateTabs(GUI GUI) {
        // remove tabs for loginWrongPasswordMessage / register
        // allows for displayTabs to have no argument for when no authority is known
        GUI.getTabbedPane().removeAll();

        // set tab names
        GUI.getLoginPanel().setName("Login");
        GUI.getRegisterPanel().setName("Register");

        GUI.getTabbedPane().add(GUI.getLoginPanel());
        GUI.getTabbedPane().add(GUI.getRegisterPanel());
    }

    public static void updateTabs(GUI GUI, char authority) {
        // clear panels //
        GUI.getTabbedPane().removeAll();

        // add panels by authority //
        switch (authority) {
            case 'S':
                GUI.getPatientViewPanel().setName("Patient View");
                GUI.getTabbedPane().add(GUI.getPatientViewPanel());
                GUI.getAppointmentsPanel().setName("Appointment List");
                GUI.getTabbedPane().add(GUI.getAppointmentsPanel());
                GUI.getPrescriptionsPanel().setName("Prescription List");
                GUI.getTabbedPane().add(GUI.getPrescriptionsPanel());
                GUI.getFeedbackPanel().setName("Feedback List");
                GUI.getTabbedPane().add(GUI.getFeedbackPanel());
                GUI.getMedicinesPanel().setName("Medicine List");
                GUI.getTabbedPane().add(GUI.getMedicinesPanel());
                GUI.getUsersPanel().setName("User List");
                GUI.getTabbedPane().add(GUI.getUsersPanel());
                GUI.getLogoutPanel().setName("Settings");
                GUI.getTabbedPane().add(GUI.getLogoutPanel());
            case 'A':
                GUI.getUsersPanel().setName("User List");
                GUI.getTabbedPane().add(GUI.getUsersPanel());
                GUI.getLogoutPanel().setName("Settings");
                GUI.getTabbedPane().add(GUI.getLogoutPanel());
                break;
            case 'D':
                GUI.getPatientViewPanel().setName("Patient View");
                GUI.getTabbedPane().add(GUI.getPatientViewPanel());
                GUI.getAppointmentsPanel().setName("Appointment List");
                GUI.getTabbedPane().add(GUI.getAppointmentsPanel());
                GUI.getPrescriptionsPanel().setName("Prescription List");
                GUI.getTabbedPane().add(GUI.getPrescriptionsPanel());
                GUI.getFeedbackPanel().setName("Feedback List");
                GUI.getTabbedPane().add(GUI.getFeedbackPanel());
                GUI.getMedicinesPanel().setName("Medicine List");
                GUI.getTabbedPane().add(GUI.getMedicinesPanel());
                GUI.getLogoutPanel().setName("Settings");
                GUI.getTabbedPane().add(GUI.getLogoutPanel());
                break;
            case 'P':
                GUI.getPatientViewPanel().setName("Patient View");
                GUI.getTabbedPane().add(GUI.getPatientViewPanel());
                GUI.getAppointmentsPanel().setName("Appointment List");
                GUI.getTabbedPane().add(GUI.getAppointmentsPanel());
                GUI.getPrescriptionsPanel().setName("Prescription List");
                GUI.getTabbedPane().add(GUI.getPrescriptionsPanel());
                GUI.getFeedbackPanel().setName("Feedback List");
                GUI.getTabbedPane().add(GUI.getFeedbackPanel());
                GUI.getLogoutPanel().setName("Settings");
                GUI.getTabbedPane().add(GUI.getLogoutPanel());
                break;
        }
    }

}