package controller;

import data.Data;
import data.User;
import user.AbstractUser;
import view.GUI;

import java.util.ArrayList;
import java.util.Arrays;

public class Login {
    public static Boolean login(GUI GUI) {
        char authority;
        switch (GUI.getComboAuthorityLogin().getSelectedIndex()) {
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
                throw new IllegalStateException("Unexpected value: " + GUI.getComboAuthorityLogin().getSelectedIndex());
        }

        try {
            // ensure typed values are committed
            GUI.getLoginUserID().commitEdit();
            GUI.getPrescriptionDosageWarningMessage().setVisible(false);
        } catch (java.text.ParseException e) {
            // catch invalid typed input
            GUI.getPrescriptionDosageWarningMessage().setVisible(true);
            return false;
        }

        int id = (Integer) GUI.getLoginUserID().getValue();

        char[] password = GUI.getLoginPasswordField().getPassword();

        ArrayList userList;
        // load patient list / user list depending on login request //
        if (authority == 'P') {
            userList = Data.getData().getPatient();
        } else {
            userList = Data.getData().getUser();
        }

        for (Object o : userList) {
            AbstractUser user = (AbstractUser) o;

            if (user.getId() == id && user.getAuthority() == authority) {
                GUI.getLoginNoIDMessage().setVisible(false);

                if (Arrays.equals(password, user.getPassword())) {
                    GUI.getLoginWrongPasswordMessage().setVisible(false);

                    if (user.getApproved()) {
                        GUI.getLoginNotApprovedMessage().setVisible(false);
                        User.setUser(new User(authority, id));

                        clearLogin(GUI);

                        return true;
                    } else {
                        GUI.getLoginNotApprovedMessage().setVisible(true);
                        return false;
                    }
                } else {
                    GUI.getLoginWrongPasswordMessage().setVisible(true);
                    return false;
                }
            }
        }

        GUI.getLoginNoIDMessage().setVisible(true);
        return false;
    }

    public static void clearLogin(GUI GUI) {
        // clear fields
        GUI.getLoginUserID().setValue(0);
        GUI.getLoginPasswordField().setText("");
    }
}
