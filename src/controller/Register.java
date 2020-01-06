package controller;

import data.Data;
import data.Serialise;
import data.User;
import user.*;
import view.GUI;

import java.util.Arrays;

public class Register {
    public static boolean register(GUI GUI) {
        char authority;
        switch (GUI.getComboAuthorityRegister().getSelectedIndex()) {
            case 0:
                authority = 'A';
                break;
            case 1:
                authority = 'D';
                break;
            case 2:
                authority = 'S';
                break;
            case 3:
                authority = 'P';
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + GUI.getComboAuthorityRegister().getSelectedIndex());
        }

        String name = GUI.getRegisterNameField().getText();
        String address = GUI.getRegisterAddressField().getText();

        char[] password = GUI.getRegisterPasswordField().getPassword();
        char[] passwordConfirm = GUI.getRegisterPasswordConfirmField().getPassword();
        if (Arrays.equals(password, passwordConfirm)) {
            GUI.getLoginWrongPasswordMessage().setVisible(false);
        } else {
            GUI.getLoginWrongPasswordMessage().setVisible(true);
            return false;
        }

        if (authority == 'P') {
            String gender = GUI.getRegisterGenderField().getText();

            try {
                // ensure typed values are committed
                GUI.getRegisterPatientAge().commitEdit();
                GUI.getRegisterAgeMessage().setVisible(false);
            } catch (java.text.ParseException e) {
                // catch invalid typed input
                GUI.getRegisterAgeMessage().setVisible(true);
                return false;
            }

            int age = (Integer) GUI.getRegisterPatientAge().getValue();

            Patient newPatient = new Patient(name, address, age, gender, password);

            Data.getData().getPatient().add(newPatient);
            User.setUser(new User(authority, newPatient.getId()));
        } else {
            AbstractUser newUser;
            switch (authority) {
                case 'A':
                    newUser = new Administrator(name, address);
                    newUser.setApproved(true);
                    break;
                case 'D':
                    newUser = new Doctor(name, address);
                    break;
                case 'S':
                    newUser = new Secretary(name, address);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + GUI.getComboAuthorityRegister().getSelectedIndex());
            }

            Data.getData().getUser().add(newUser);
            User.setUser(new User(authority, newUser.getId()));
        }

        clearRegister(GUI);

        Serialise.serialise();
        return true;
    }

    public static void clearRegister(GUI GUI) {
        GUI.getRegisterPasswordField().setText("");
        GUI.getRegisterPasswordConfirmField().setText("");
        GUI.getRegisterNameField().setText("");
        GUI.getRegisterAddressField().setText("");
        GUI.getRegisterGenderField().setText("");
        GUI.getRegisterAddressField().setText("");
        GUI.getRegisterSuccessPanel().setVisible(false);
        GUI.getRegisterPanel().setVisible(true);
    }

    public static void registerSuccess(GUI GUI) {
        GUI.getRegisterMainPanel().setVisible(false);
        GUI.getRegisterSuccessPanel().setVisible(true);

        GUI.getRegisterSucessID().setValue(User.getUser().getId());

        switch (User.getUser().getAuthority()) {
            case 'A':
                GUI.getRegisterSuccessAuthority().setText("Admin");
                break;
            case 'S':
                GUI.getRegisterSuccessAuthority().setText("Secretary");
                break;
            case 'D':
                GUI.getRegisterSuccessAuthority().setText("Doctor");
                break;
            case 'P':
                GUI.getRegisterSuccessAuthority().setText("Patient");
                break;
        }
    }

    public static void comboActionPerformed(GUI GUI) {
        switch (GUI.getComboAuthorityRegister().getSelectedIndex()) {
            case 0:
            case 1:
            case 2:
                GUI.getRegisterPatientAge().setEnabled(false);
                GUI.getRegisterGenderField().setEnabled(false);
                break;
            case 3:
                GUI.getRegisterPatientAge().setEnabled(true);
                GUI.getRegisterGenderField().setEnabled(true);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + GUI.getComboAuthorityRegister().getSelectedIndex());
        }
    }
}
