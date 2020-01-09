package controller;

import data.Data;
import data.Serialise;
import data.User;
import system.Appointment;
import system.Medicine;
import user.AbstractUser;
import user.Patient;
import view.GUI;
import view.ListItem;

import javax.swing.*;

public class UserList {
    public static void main(GUI GUI) {
        // cannot edit authority or ID
        GUI.getUserAuthority().setEnabled(false);
        GUI.getUserListID().setEnabled(false);

        populate(GUI.getUserList());
        clearUser(GUI);
    }

    public static void populate(JList userList) {
        DefaultListModel userListModel = new DefaultListModel();
        userList.setModel(userListModel);

        char authority = User.getUser().getAuthority();

        if (authority == 'S') {
            for (int i = 0; i < Data.getData().getPatient().size(); i++) {
                Patient patient = Data.getData().getPatient().get(i);

                String element = String.format("Name: %s, Approved: %s", patient.getName(), patient.getApproved());

                userListModel.addElement(new ListItem(element, patient));
            }
        } else if (authority == 'A') {
            for (int i = 0; i < Data.getData().getUser().size(); i++) {
                AbstractUser user = Data.getData().getUser().get(i);

                String element = String.format("Authority: %s, Name: %s, Approved: %s", user.getAuthority(), user.getName(), user.getApproved());

                userListModel.addElement(new ListItem(element, user));
            }
        }
    }

    public static void loadUser(GUI GUI) {
        ListItem selectedUser = (ListItem) GUI.getUserList().getSelectedValue();

        if (selectedUser == null) {
            return;
        }

        AbstractUser user = (AbstractUser) selectedUser.getValue();

        GUI.getUserAddress().setText(user.getAddress());
        GUI.getUserName().setText(user.getName());

        switch (user.getAuthority()) {
            case 'A':
                GUI.getUserAuthority().setText("Admin");
                break;
            case 'S':
                GUI.getUserAuthority().setText("Secretary");
                break;
            case 'D':
                GUI.getUserAuthority().setText("Doctor");
                break;
            case 'P':
                GUI.getUserAuthority().setText("Patient");
                break;
        }

        GUI.getUserApprovedCheckBox().setSelected(user.getApproved());

        GUI.getUserListID().setValue(user.getId());

        System.out.println(user.getId());

        GUI.getRequestedDeletionCheckBox().setSelected(user.getRequestDelete());

        if (user.getAuthority() == 'P') {
            Patient patient = (Patient) selectedUser.getValue();

            GUI.getUserAge().setVisible(true);
            GUI.getUserGender().setVisible(true);

            GUI.getUserAge().setValue(patient.getAge());
            GUI.getUserGender().setText(patient.getGender());
        } else {
            GUI.getUserAge().setVisible(false);
            GUI.getUserGender().setVisible(false);
        }
    }

    public static void clearUser(GUI GUI) {
        GUI.getUserName().setText("");
        GUI.getUserGender().setText("");
        GUI.getUserAge().setValue(0);
        GUI.getUserApprovedCheckBox().setSelected(false);
        GUI.getUserAddress().setText("");
        GUI.getRequestedDeletionCheckBox().setSelected(false);
    }

    public static void editUser(GUI GUI) {
        ListItem selectedUser = (ListItem) GUI.getUserList().getSelectedValue();
        AbstractUser user = (AbstractUser) selectedUser.getValue();

        char authority = user.getAuthority();

        int userIndex;
        if (authority == 'P') {
            Patient patient = (Patient) selectedUser.getValue();

            userIndex = Data.getData().getPatient().indexOf(patient);

            Data.getData().getPatient().get(userIndex).setApproved(GUI.getUserApprovedCheckBox().isSelected());
        } else {
            userIndex = Data.getData().getUser().indexOf(user);

            Data.getData().getUser().get(userIndex).setApproved(GUI.getUserApprovedCheckBox().isSelected());
        }

        Serialise.serialise();

        clearUser(GUI);
        populate(GUI.getUserList());
    }

    public static void deleteUser(GUI GUI) {
        ListItem selectedUser = (ListItem) GUI.getUserList().getSelectedValue();
        AbstractUser user = (AbstractUser) selectedUser.getValue();

        char authority = user.getAuthority();

        int userIndex;
        if (authority == 'P') {
            Patient patient = (Patient) selectedUser.getValue();

            userIndex = Data.getData().getPatient().indexOf(patient);

            Data.getData().getPatient().remove(userIndex);

            AppointmentList.deleteUser(user);
            FeedbackList.deleteUser(user);
            PrescriptionList.deleteUser(user);
        } else {
            userIndex = Data.getData().getUser().indexOf(user);

            Data.getData().getUser().remove(userIndex);
        }

        Serialise.serialise();

        clearUser(GUI);
        populate(GUI.getUserList());
    }
}
