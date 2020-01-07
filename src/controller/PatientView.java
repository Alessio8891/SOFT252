package controller;

import data.Data;
import data.Serialise;
import data.User;
import system.PatientNote;
import user.Doctor;
import user.Patient;
import view.ComboItem;
import view.GUI;
import view.ListItem;

import javax.swing.*;

public class PatientView {
    public static void main(GUI GUI) {
        char authority = User.getUser().getAuthority();

        switch (authority) {
            case 'D':
                GUI.getNewNoteButton().setVisible(true);
                GUI.getPatientViewSelectPanel().setVisible(true);
                GUI.getPatientViewNewEditLabel().setVisible(true);
                GUI.getSaveEditedNoteButton().setVisible(true);
                GUI.getAddPatientNoteButton().setVisible(true);
                GUI.getDeleteSelectedNoteButton().setVisible(true);
                populatePatientSelectCombo(GUI.getPatientViewSelectCombo());
                break;
            case 'P':
                GUI.getNewNoteButton().setVisible(false);
                GUI.getPatientViewNewEditLabel().setVisible(false);
                GUI.getSaveEditedNoteButton().setVisible(false);
                GUI.getAddPatientNoteButton().setVisible(false);
                GUI.getDeleteSelectedNoteButton().setVisible(false);
                GUI.getPatientViewSelectPanel().setVisible(false);
                Patient patient = Data.getData().findPatient(User.getUser().getId());
                populatePatientHistory(GUI, patient);
            case 'S':
                GUI.getPatientViewNewEditLabel().setVisible(false);
                GUI.getSaveEditedNoteButton().setVisible(false);
                GUI.getAddPatientNoteButton().setVisible(false);
                GUI.getDeleteSelectedNoteButton().setVisible(false);
                populatePatientSelectCombo(GUI.getPatientViewSelectCombo());
        }
        clearPatientNote(GUI);
    }

    public static void populatePatientHistory(GUI GUI, Patient patient) {
        // must cast to Patient, as stored as AbstractUser
        DefaultListModel patientHistoryListModel = new DefaultListModel();
        GUI.getPatientHistoryList().setModel(patientHistoryListModel);

        // populate list of history //
        for (int i = 0; i < patient.getPatientNotes().size(); i++) {
            PatientNote note = patient.getPatientNotes().get(i);

            // only display max first 50 characters of note
            String text;
            if (note.getNote().length() > 50) {
                text = note.getNote().substring(0, 50);
            } else {
                text = note.getNote();
            }

            String element = String.format("Doctor: %s, Note: %s", note.getDoctor().getName(), text);

            patientHistoryListModel.addElement(new ListItem(element, note));
        }
    }

    public static void populatePatientNote(JList patientHistoryList, JTextArea patientNoteTextArea) {
        // populate patient note panel //
        ListItem selectedItem = (ListItem) patientHistoryList.getSelectedValue();

        if (selectedItem == null) {
            return;
        }

        PatientNote patientNote = (PatientNote) selectedItem.getValue();
        patientNoteTextArea.setText(patientNote.getNote());
    }

    public static void clearPatientNote(GUI GUI) {
        GUI.getPatientNoteTextArea().setText("");
    }

    public static void updatePatientNote(GUI GUI) {
        ComboItem selectedItem = (ComboItem) GUI.getPatientViewSelectCombo().getSelectedItem();
        Patient patient = (Patient) selectedItem.getValue();

        int patientIndex = Data.getData().getPatient().indexOf(patient);

        int historyIndex = GUI.getPatientHistoryList().getSelectedIndex();

        Data.getData().getPatient().get(patientIndex).getPatientNotes().get(historyIndex).setNote(GUI.getPatientNoteTextArea().getText());
        Serialise.serialise();

        clearPatientNote(GUI);
        populatePatientHistory(GUI, patient);
    }

    public static void newPatientNote(GUI GUI) {
        ComboItem selectedItem = (ComboItem) GUI.getPatientViewSelectCombo().getSelectedItem();
        Patient patient = (Patient) selectedItem.getValue();

        int patientIndex = Data.getData().getPatient().indexOf(patient);

        Doctor doctor = (Doctor) Data.getData().findUser(User.getUser().getAuthority(), User.getUser().getId());

        PatientNote patientNote = new PatientNote(doctor, GUI.getPatientNoteTextArea().getText());

        Data.getData().getPatient().get(patientIndex).getPatientNotes().add(patientNote);
        Serialise.serialise();

        clearPatientNote(GUI);
        populatePatientHistory(GUI, patient);
    }

    public static void deletePatientNote(GUI GUI) {
        ComboItem selectedItem = (ComboItem) GUI.getPatientViewSelectCombo().getSelectedItem();
        Patient patient = (Patient) selectedItem.getValue();

        int patientIndex = Data.getData().getPatient().indexOf(patient);

        ListItem selectedNote = (ListItem) GUI.getPatientHistoryList().getSelectedValue();
        PatientNote patientNote = (PatientNote) selectedNote.getValue();

        Data.getData().getPatient().get(patientIndex).getPatientNotes().remove(patientNote);
        Serialise.serialise();

        clearPatientNote(GUI);
        populatePatientHistory(GUI, patient);
    }

    public static void populatePatientSelectCombo(JComboBox patientSelect) {
        patientSelect.removeAllItems();
        for (int i = 0; i < Data.getData().getPatient().size(); i++) {

            Patient patient = Data.getData().getPatient().get(i);

            patientSelect.addItem(new ComboItem(patient.getName(), patient));
        }
    }
}
