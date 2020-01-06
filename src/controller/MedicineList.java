package controller;

import data.Data;
import data.Serialise;
import data.User;
import system.Medicine;
import view.GUI;
import view.ListItem;

import javax.swing.*;

public class MedicineList {
    public static void main(GUI GUI) {
        char authority = User.getUser().getAuthority();

        switch (authority) {
            case 'D':
                GUI.getNewMedicineButton().setVisible(true);
                GUI.getMedicineStockSpinner().setEnabled(false);
                GUI.getSubmitMedicineButton().setVisible(true);
            case 'S':
                GUI.getNewMedicineButton().setVisible(false);
                GUI.getMedicineStockSpinner().setEnabled(true);
                GUI.getSubmitMedicineButton().setVisible(false);
        }

        populate(GUI.getMedicineList());
        clearMedicine(GUI);
    }

    public static void populate(JList medicineList) {
        DefaultListModel medicineListModel = new DefaultListModel();
        medicineList.setModel(medicineListModel);

        for (int i = 0; i < Data.getData().getMedicine().size(); i++) {
            Medicine medicine = Data.getData().getMedicine().get(i);

            String element = String.format("Name: %s, Stock: %s", medicine.getName(), medicine.getStock());

            medicineListModel.addElement(new ListItem(element, medicine));
        }
    }

    public static void clearMedicine(GUI GUI) {
        GUI.getMedicineStockSpinner().setValue(0);
        GUI.getMedicineName().setText("");
    }

    public static void loadMedicine(GUI GUI) {
        ListItem selectedMedicine = (ListItem) GUI.getMedicineList().getSelectedValue();

        if (selectedMedicine == null) {
            return;
        }

        Medicine medicine = (Medicine) selectedMedicine.getValue();

        GUI.getMedicineName().setText(medicine.getName());
        GUI.getMedicineStockSpinner().setValue(medicine.getStock());
    }

    public static void newMedicine(GUI GUI) {
        String name = GUI.getMedicineName().getText();

        try {
            // ensure typed values are committed
            GUI.getMedicineStockSpinner().commitEdit();
            GUI.getMedicineStockWarningMessage().setVisible(false);
        } catch (java.text.ParseException e) {
            // catch invalid typed input
            GUI.getMedicineStockWarningMessage().setVisible(true);
            return;
        }

        int stock = (Integer) GUI.getMedicineStockSpinner().getValue();

        Medicine medicine = new Medicine(name, stock);

        Data.getData().getMedicine().add(medicine);
        Serialise.serialise();

        clearMedicine(GUI);
        populate(GUI.getMedicineList());
    }

    public static void editMedicine(GUI GUI) {
        // get selected feedback //
        ListItem selectedMedicine = (ListItem) GUI.getMedicineList().getSelectedValue();
        Medicine medicine = (Medicine) selectedMedicine.getValue();

        int medicineIndex = Data.getData().getMedicine().indexOf(medicine);

        try {
            // ensure typed values are committed
            GUI.getMedicineStockSpinner().commitEdit();
            GUI.getMedicineStockWarningMessage().setVisible(false);
        } catch (java.text.ParseException e) {
            // catch invalid typed input
            GUI.getMedicineStockWarningMessage().setVisible(true);
            return;
        }

        int stock = (Integer) GUI.getMedicineStockSpinner().getValue();

        Data.getData().getMedicine().get(medicineIndex).setStock(stock);
        Serialise.serialise();

        clearMedicine(GUI);
        populate(GUI.getMedicineList());
    }

    public static void deleteMedicine(GUI GUI) {
        ListItem selectedMedicine = (ListItem) GUI.getMedicineList().getSelectedValue();
        Medicine medicine = (Medicine) selectedMedicine.getValue();

        int medicineIndex = Data.getData().getMedicine().indexOf(medicine);

        Data.getData().getMedicine().remove(medicineIndex);
        Serialise.serialise();

        clearMedicine(GUI);
        populate(GUI.getMedicineList());
    }
}
