package controller;

import data.Data;
import data.Serialise;
import data.User;
import system.Medicine;
import system.Prescription;
import user.Doctor;
import user.Patient;
import view.ComboItem;
import view.GUI;
import view.ListItem;

import javax.swing.*;

public class PrescriptionList {
    public static void main(GUI GUI) {
        char authority = User.getUser().getAuthority();

        switch (authority) {
            case 'P':
                GUI.getNewPrescriptionButton().setVisible(false);
                GUI.getSaveEditedPrescriptionButton().setVisible(false);
                GUI.getSubmitPrescriptionButton().setVisible(false);
                GUI.getDeleteSelectedPrescriptionButton().setVisible(false);

                GUI.getPrescriptionMedicineSelectCombo().setEnabled(false);
                GUI.getPrescriptionPatientSelectCombo().setEnabled(false);
                GUI.getPrescriptionDosage().setEnabled(false);
                GUI.getPrescriptionQuantitySelector().setEnabled(false);

                GUI.getPrescriptionProcessedCheckBox().setEnabled(false);
                break;
            case 'D':
                GUI.getSaveEditedPrescriptionButton().setVisible(true);
                GUI.getSubmitPrescriptionButton().setVisible(true);
                GUI.getDeleteSelectedPrescriptionButton().setVisible(true);

                GUI.getPrescriptionMedicineSelectCombo().setEnabled(true);
                GUI.getPrescriptionPatientSelectCombo().setEnabled(true);
                GUI.getPrescriptionDosage().setEnabled(true);
                GUI.getPrescriptionQuantitySelector().setEnabled(true);

                GUI.getPrescriptionProcessedCheckBox().setEnabled(false);
                break;
            case 'S':
                GUI.getNewPrescriptionButton().setVisible(false);
                GUI.getSaveEditedPrescriptionButton().setVisible(true);
                GUI.getSubmitPrescriptionButton().setVisible(false);
                GUI.getDeleteSelectedPrescriptionButton().setVisible(true);

                GUI.getPrescriptionMedicineSelectCombo().setEnabled(false);
                GUI.getPrescriptionPatientSelectCombo().setEnabled(false);
                GUI.getPrescriptionDosage().setEnabled(false);
                GUI.getPrescriptionQuantitySelector().setEnabled(false);

                GUI.getPrescriptionProcessedCheckBox().setEnabled(true);
                break;
        }

        populatePatientCombo(GUI);
        populateMedicineCombo(GUI);
        populate(GUI.getPrescriptionList());
        clearPrescription(GUI);
    }

    public static void loadPrescription(GUI GUI) {
        clearPrescription(GUI);
        ListItem selectedPrescription = (ListItem) GUI.getPrescriptionList().getSelectedValue();

        if (selectedPrescription == null) {
            return;
        }

        Prescription prescription = (Prescription) selectedPrescription.getValue();

        GUI.getPrescriptionMedicineSelectCombo().setSelectedItem(prescription.getMedicine());
        GUI.getPrescriptionPatientSelectCombo().setSelectedItem(prescription.getPatient());
        GUI.getPrescriptionDosage().setText(prescription.getDosage());
        GUI.getPrescriptionQuantitySelector().setValue(prescription.getQuantity());
        GUI.getPrescriptionProcessedCheckBox().setSelected(prescription.getProcessed());

        if (prescription.getQuantity() > prescription.getMedicine().getStock()) {
            // if insufficient quantity of medicine exists to fulfil prescription
            // disable the processed checkbox
            GUI.getPrescriptionInsufficientStockWarningMessage().setVisible(true);
            GUI.getPrescriptionProcessedCheckBox().setEnabled(false);
        }
    }

    public static void populatePatientCombo(GUI GUI) {
        GUI.getPrescriptionPatientSelectCombo().removeAllItems();
        for (int i = 0; i < Data.getData().getPatient().size(); i++) {
            Patient patient = Data.getData().getPatient().get(i);

            GUI.getPrescriptionPatientSelectCombo().addItem(new ComboItem(patient.getName(), patient));
        }
    }

    public static void populateMedicineCombo(GUI GUI) {
        GUI.getPrescriptionMedicineSelectCombo().removeAllItems();
        for (int i = 0; i < Data.getData().getMedicine().size(); i++) {
            Medicine medicine = Data.getData().getMedicine().get(i);

            GUI.getPrescriptionMedicineSelectCombo().addItem(new ComboItem(medicine.getName(), medicine));
        }
    }

    public static void clearPrescription(GUI GUI) {
        GUI.getPrescriptionProcessedCheckBox().setSelected(false);
        GUI.getPrescriptionDosage().setText("");
        GUI.getPrescriptionQuantitySelector().setValue(0);
        GUI.getPrescriptionDosageWarningMessage().setVisible(false);
        GUI.getPrescriptionInsufficientStockWarningMessage().setVisible(false);

        if (User.getUser().getAuthority() == 'S') {
            GUI.getPrescriptionProcessedCheckBox().setEnabled(true);
        }
    }

    public static void populate(JList prescriptionList) {
        char authority = User.getUser().getAuthority();
        int id = User.getUser().getId();

        DefaultListModel prescriptionListModel = new DefaultListModel();
        prescriptionList.setModel(prescriptionListModel);

        for (int i = 0; i < Data.getData().getPrescription().size(); i++) {
            Prescription prescription = Data.getData().getPrescription().get(i);

            Doctor doctor = prescription.getDoctor();
            Patient patient = prescription.getPatient();
            Medicine medicine = prescription.getMedicine();

            String element;
            // only display patients personal data //
            if (authority == 'P' && patient.getId() == id) {
                element = String.format("Doctor: %s, Medicine: %s", doctor.getName(), medicine.getName());
                prescriptionListModel.addElement(new ListItem(element, prescription));
            }
            // only display doctors own prescriptions //
            else if (authority == 'D' && doctor.getId() == id) {
                element = String.format("Patient: %s, Medicine: %s", patient.getName(), medicine.getName());
                prescriptionListModel.addElement(new ListItem(element, prescription));
            }
            // display all prescriptions to secretaries //
            else if (authority == 'S') {
                element = String.format("Doctor: %s, Patient: %s, Medicine: %s", doctor.getName(), patient.getName(), medicine.getName());
                prescriptionListModel.addElement(new ListItem(element, prescription));
            }
        }
    }

    public static void newPrescription(GUI GUI) {
        ComboItem selectedPatient = (ComboItem) GUI.getPrescriptionPatientSelectCombo().getSelectedItem();
        Patient patient = (Patient) selectedPatient.getValue();

        ComboItem selectedMedicine = (ComboItem) GUI.getPrescriptionMedicineSelectCombo().getSelectedItem();
        Medicine medicine = (Medicine) selectedMedicine.getValue();

        Doctor doctor = (Doctor) Data.getData().findUser('D', User.getUser().getId());

        try {
            // ensure typed values are committed
            GUI.getLoginUserID().commitEdit();
            GUI.getLoginUserIDWrongMessage().setVisible(false);
        } catch (java.text.ParseException e) {
            // catch invalid typed input
            GUI.getLoginUserIDWrongMessage().setVisible(true);
            return;
        }

        int quantity = (Integer) GUI.getLoginUserID().getValue();

        String dosage = GUI.getPrescriptionDosage().getText();

        Prescription prescription = new Prescription(doctor, patient, medicine, quantity, dosage);

        Data.getData().getPrescription().add(prescription);
        Serialise.serialise();

        clearPrescription(GUI);
        populate(GUI.getFeedbackList());
    }

    public static void editPrescription(GUI GUI) {
        ComboItem selectedMedicine = (ComboItem) GUI.getPrescriptionMedicineSelectCombo().getSelectedItem();
        Medicine medicine = (Medicine) selectedMedicine.getValue();

        ListItem selectedPrescription = (ListItem) GUI.getPrescriptionList().getSelectedValue();
        Prescription prescription = (Prescription) selectedPrescription.getValue();

        int prescriptionIndex = Data.getData().getPrescription().indexOf(prescription);

        try {
            // ensure typed values are committed
            GUI.getPrescriptionQuantitySelector().commitEdit();
            GUI.getPrescriptionDosageWarningMessage().setVisible(false);
        } catch (java.text.ParseException e) {
            // catch invalid typed input
            GUI.getPrescriptionDosageWarningMessage().setVisible(true);
            return;
        }

        int quantity = (Integer) GUI.getPrescriptionQuantitySelector().getValue();

        boolean processed = GUI.getPrescriptionProcessedCheckBox().isSelected();

        if (processed == true && Data.getData().getPrescription().get(prescriptionIndex).getProcessed() == false) {
            // if prescription set to processed state, subtract medicine from stock
            medicine.useStock(quantity);
        } else if (processed == false && Data.getData().getPrescription().get(prescriptionIndex).getProcessed() == true) {
            // if prescription set to unprocessed state, refund medicine into stock
            medicine.addStock(quantity);
        }

        Data.getData().getPrescription().get(prescriptionIndex).setDosage(GUI.getPrescriptionDosage().getText());
        Data.getData().getPrescription().get(prescriptionIndex).setMedicine(medicine);
        Data.getData().getPrescription().get(prescriptionIndex).setQuantity(quantity);
        Data.getData().getPrescription().get(prescriptionIndex).setProcessed(processed);

        Serialise.serialise();

        clearPrescription(GUI);
        populate(GUI.getFeedbackList());
    }

    public static void deletePrescription(GUI GUI) {
        ListItem selectedPrescription = (ListItem) GUI.getPrescriptionList().getSelectedValue();
        Prescription prescription = (Prescription) selectedPrescription.getValue();

        int prescriptionIndex = Data.getData().getPrescription().indexOf(prescription);

        Data.getData().getPrescription().remove(prescriptionIndex);
        Serialise.serialise();

        clearPrescription(GUI);
        populate(GUI.getPrescriptionList());
    }
}
