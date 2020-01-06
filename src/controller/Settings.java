package controller;

import data.Data;
import data.Serialise;
import data.User;
import user.AbstractUser;
import user.Patient;
import view.GUI;

public class Settings {
    public static void main(GUI GUI) {
        // clear old authority
        GUI.getSettingsUserAuthority().removeAllItems();

        // load user details
        switch (User.getUser().getAuthority()) {
            case 'A':
                GUI.getSettingsUserAuthority().addItem("Admin");
                break;
            case 'S':
                GUI.getSettingsUserAuthority().addItem("Secretary");
                break;
            case 'D':
                GUI.getSettingsUserAuthority().addItem("Doctor");
                break;
            case 'P':
                GUI.getSettingsUserAuthority().addItem("Patient");
                break;
        }

        GUI.getSettingsUserID().setText(Integer.toString(User.getUser().getId()));

        boolean flag;
        if (User.getUser().getAuthority() == 'P') {
            Patient user = Data.getData().findPatient(User.getUser().getId());
            flag = user.getRequestDelete();
        } else {
            AbstractUser user = Data.getData().findUser(User.getUser().getAuthority(), User.getUser().getId());
            flag = user.getRequestDelete();
        }

        GUI.getRequestAccountDeletionCheckBox().setSelected(flag);
    }

    public static void logout(GUI GUI) {
        // clear user
        User.setUser(null);

        // reset to login page
        Controller.updateTabs(GUI);
    }

    public static void requestAccountDeletion(GUI GUI) {
        boolean updated = GUI.getRequestAccountDeletionCheckBox().isSelected();

        if (User.getUser().getAuthority() == 'P') {
            Patient patient = Data.getData().findPatient(User.getUser().getId());
            int index = Data.getData().getPatient().indexOf(patient);
            Data.getData().getPatient().get(index).setRequestDelete(updated);
        } else {
            AbstractUser user = Data.getData().findUser(User.getUser().getAuthority(), User.getUser().getId());
            int index = Data.getData().getUser().indexOf(user);
            Data.getData().getUser().get(index).setRequestDelete(updated);
        }

        Serialise.serialise();
    }
}
