package view;

import controller.*;
import data.User;
import user.Patient;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JTabbedPane tabbedPane;
    private JPanel contentPane;
    private JButton loginButton;
    private JTextField UserID;
    private JComboBox comboAuthorityLogin;
    private JComboBox comboAuthorityRegister;
    private JTextField registerNameField;
    private JTextField registerGenderField;
    private JTextField registerAgeField;
    private JButton registerButton;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JPanel patientsPanel;
    private JPanel patientViewPanel;
    private JPanel appointmentsPanel;
    private JPanel medicinesPanel;
    private JPanel usersPanel;
    private JPanel prescriptionsPanel;
    private JButton addPatientNoteButton;
    private JList appointmentList;
    private JList patientHistoryList;
    private JList medicineList;
    private JList userList;
    private JButton removeUserButton;
    private JList prescriptionList;
    private JButton deleteMedicineButton;
    private JButton newAppointmentButton;
    private JButton editAppointmentButton;
    private JList patientList;
    private JPanel secretaryAppointmentPanel;
    private JButton markInStockButton1;
    private JButton markOutOfStockButton1;
    private JPanel PatientFeedbackControlPanel;
    private JPanel SecretaryFeedbackControlPanel;
    private JButton editFeedbackSecretaryButton;
    private JButton editFeedbackButton;
    private JLabel loginNoIDMessage;
    private JTextField registerAddressField;
    private JLabel registerAgeMessage;
    private JLabel loginNotApprovedMessage;
    private JPanel feedbackPanel;
    private JList feedbackList;
    private JTextArea patientNoteTextArea;
    private JComboBox medicineSelectCombo;
    private JButton addPatientPrescriptionButton;
    private JTextField patientPrescriptionDosage;
    private JTextField userName;
    private JButton submitFeedbackButton;
    private JTextArea feedbackTextArea;
    private JComboBox feedbackDoctorSelectCombo;
    private JSlider feedbackRatingSlider;
    private JPasswordField loginPasswordField;
    private JLabel loginWrongPasswordMessage;
    private JPasswordField registerPasswordField;
    private JPasswordField registerPasswordConfirmField;
    private JButton submitPrescriptionButton;
    private JTextField prescriptionDosage;
    private JLabel prescription;
    private JCheckBox prescriptionProcessedCheckBox;
    private JComboBox prescriptionMedicineSelectCombo;
    private JButton submitAppointmentButton;
    private JCheckBox appointmentConfirmedCheckbox;
    private JTextField appointmentLatestDate;
    private JTextField appointmentEarliestDate;
    private JComboBox appointmentDoctorSelectCombo;
    private JComboBox appointmentPatientSelectCombo;
    private JButton submitMedicineButton;
    private JCheckBox medicineStockedCheckbox;
    private JTextField medicineName;
    private JButton updateUserButton;
    private JCheckBox userApprovedCheckBox;
    private JCheckBox feedbackApprovedCheckBox;
    private JButton deleteUserButton;
    private JComboBox patientViewSelectCombo;
    private JPanel patientViewSelectPanel;
    private JPanel patientNotePanel;
    private JSpinner prescriptionQuantitySelector;
    private JSpinner registerPatientAge;
    private JLabel patientViewNewEditLabel;
    private JButton saveEditedNoteButton;
    private JPanel appointmentEditPanel;
    private JLabel appointmentEditTitle;
    private JButton deleteSelectedAppointmentButton;
    private JButton deleteSelectedNoteButton;
    private JButton deleteSelectedFeedbackButton;
    private JButton saveEditedPrescriptionButton;
    private JButton saveEditedMedicineButton;
    private JButton deleteSelectedMedicineButton;
    private JSpinner medicineStockSpinner;
    private JLabel medicineStockWarningMessage;
    private JComboBox prescriptionPatientSelectCombo;
    private JLabel prescriptionDosageWarningMessage;
    private JButton deleteSelectedPrescriptionButton;
    private JTextField userAddress;
    private JTextField userGender;
    private JSpinner userAge;
    private JLabel appointmentEarliestOrConfirmedLabel;
    private JButton newFeedbackButton;
    private JButton newNoteButton;
    private JButton newMedicineButton;
    private JButton newPrescriptionButton;
    private JTextField userAuthority;
    private JSpinner userListID;
    private JPanel registerSuccessPanel;
    private JTextField registerSuccessAuthority;
    private JSpinner registerSucessID;
    private JSpinner loginUserID;
    private JLabel loginUserIDWrongMessage;
    private JPanel registerMainPanel;
    private JPanel logoutPanel;
    private JButton logoutButton;
    private JTextField settingsUserID;
    private JComboBox settingsUserAuthority;
    private JButton requestDeleteAccountButton;
    private JCheckBox requestedDeletionCheckBox;
    private JCheckBox requestAccountDeletionCheckBox;
    private JLabel prescriptionInsufficientStockWarningMessage;
    private JComboBox prescriptionDoctorComboSelect;
    private JComboBox feedbackPatientSelectCombo;
    private JButton buttoneditButton;

    private JFrame frame;
    private static GUI GUI;

    public GUI() {
        // add tab change event listener //
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Controller.tabChangeState(GUI);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean success = Login.login(GUI);
                if (success) {
                    Controller.updateTabs(GUI, User.user.getAuthority());
                }
            }
        });
        comboAuthorityRegister.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Register.comboActionPerformed(GUI);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = Register.register(GUI);
                if (success) {
                    Register.registerSuccess(GUI);
                }
            }
        });
        comboAuthorityLogin.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // hide error messages for previous authority selection //
                GUI.getLoginNotApprovedMessage().setVisible(false);
                GUI.getLoginNoIDMessage().setVisible(false);
            }
        });
        patientViewSelectCombo.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem selectedItem = (ComboItem) GUI.getPatientViewSelectCombo().getSelectedItem();

                if (selectedItem == null) {
                    return;
                }

                PatientView.populatePatientHistory(GUI, (Patient) selectedItem.getValue());
            }
        });
        addPatientNoteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientView.newPatientNote(GUI);
            }
        });
        saveEditedNoteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientView.updatePatientNote(GUI);
            }
        });
        patientHistoryList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                PatientView.populatePatientNote(GUI.getPatientHistoryList(), GUI.getPatientNoteTextArea());
            }
        });
        deleteSelectedNoteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientView.deletePatientNote(GUI);
            }
        });
        deleteSelectedAppointmentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentList.deleteAppointment(GUI);
            }
        });
        editFeedbackButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedbackList.editFeedback(GUI);
            }
        });
        submitFeedbackButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedbackList.newFeedback(GUI);
            }
        });
        deleteSelectedFeedbackButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedbackList.deleteFeedback(GUI);
            }
        });
        saveEditedMedicineButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineList.editMedicine(GUI);
            }
        });
        submitMedicineButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineList.newMedicine(GUI);
            }
        });
        deleteSelectedMedicineButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineList.deleteMedicine(GUI);
            }
        });
        saveEditedPrescriptionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionList.editPrescription(GUI);
            }
        });
        submitPrescriptionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionList.newPrescription(GUI);
            }
        });
        deleteSelectedPrescriptionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionList.deletePrescription(GUI);
            }
        });
        updateUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                UserList.editUser(GUI);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                UserList.deleteUser(GUI);
            }
        });
        appointmentList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                AppointmentList.loadAppointment(GUI);
            }
        });
        editAppointmentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentList.editAppointment(GUI);
            }
        });
        submitAppointmentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentList.newAppointment(GUI);
            }
        });
        deleteSelectedAppointmentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentList.deleteAppointment(GUI);
            }
        });
        medicineList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MedicineList.loadMedicine(GUI);
            }
        });
        newNoteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientView.clearPatientNote(GUI);
            }
        });
        feedbackList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                FeedbackList.loadFeedback(GUI);
            }
        });
        newAppointmentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentList.clearAppointment(GUI);
            }
        });
        newMedicineButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineList.clearMedicine(GUI);
            }
        });
        newPrescriptionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionList.clearPrescription(GUI);
            }
        });
        newFeedbackButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedbackList.clearFeedback(GUI);
            }
        });
        prescriptionList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                PrescriptionList.loadPrescription(GUI);
            }
        });
        userList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                UserList.loadUser(GUI);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.logout(GUI);
            }
        });
        requestAccountDeletionCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.requestAccountDeletion(GUI);
            }
        });
    }

    public static view.GUI getGUI() {
        return GUI;
    }

    public JTextField getUserID() {
        return UserID;
    }

    public JTextField getRegisterSuccessAuthority() {
        return registerSuccessAuthority;
    }

    public JLabel getLoginUserIDWrongMessage() {
        return loginUserIDWrongMessage;
    }

    public JSpinner getRegisterSucessID() {
        return registerSucessID;
    }

    public JSpinner getLoginUserID() {
        return loginUserID;
    }

    public JComboBox getComboAuthorityLogin() {
        return comboAuthorityLogin;
    }

    public JComboBox getComboAuthorityRegister() {
        return comboAuthorityRegister;
    }

    public JTextField getRegisterAddressField() {
        return registerAddressField;
    }

    public JTextField getRegisterNameField() {
        return registerNameField;
    }

    public JTextField getRegisterGenderField() {
        return registerGenderField;
    }

    public JTextField getRegisterAgeField() {
        return registerAgeField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JLabel getRegisterAgeMessage() {
        return registerAgeMessage;
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }

    public JLabel getLoginNotApprovedMessage() {
        return loginNotApprovedMessage;
    }

    public JPanel getPatientsPanel() {
        return patientsPanel;
    }

    public JPanel getPatientViewPanel() {
        return patientViewPanel;
    }

    public JPanel getAppointmentsPanel() {
        return appointmentsPanel;
    }

    public JPanel getMedicinesPanel() {
        return medicinesPanel;
    }

    public JPanel getUsersPanel() {
        return usersPanel;
    }

    public JPanel getPrescriptionsPanel() {
        return prescriptionsPanel;
    }

    public JButton getAddPatientNoteButton() {
        return addPatientNoteButton;
    }

    public JList getAppointmentList() {
        return appointmentList;
    }

    public JList getPatientHistoryList() {
        return patientHistoryList;
    }

    public JList getMedicineList() {
        return medicineList;
    }

    public JList getUserList() {
        return userList;
    }

    public JButton getRemoveUserButton() {
        return removeUserButton;
    }

    public JList getPrescriptionList() {
        return prescriptionList;
    }

    public JButton getDeleteMedicineButton() {
        return deleteMedicineButton;
    }

    public JButton getNewAppointmentButton() {
        return newAppointmentButton;
    }

    public JButton getEditAppointmentButton() {
        return editAppointmentButton;
    }

    public JList getPatientList() {
        return patientList;
    }

    public JPanel getSecretaryAppointmentPanel() {
        return secretaryAppointmentPanel;
    }

    public JButton getMarkInStockButton1() {
        return markInStockButton1;
    }

    public JButton getMarkOutOfStockButton1() {
        return markOutOfStockButton1;
    }

    public JPanel getPatientFeedbackControlPanel() {
        return PatientFeedbackControlPanel;
    }

    public JPanel getSecretaryFeedbackControlPanel() {
        return SecretaryFeedbackControlPanel;
    }

    public JButton getEditFeedbackSecretaryButton() {
        return editFeedbackSecretaryButton;
    }

    public JButton getEditFeedbackButton() {
        return editFeedbackButton;
    }

    public JButton getButtoneditButton() {
        return buttoneditButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JCheckBox getRequestedDeletionCheckBox() {
        return requestedDeletionCheckBox;
    }

    public JCheckBox getRequestAccountDeletionCheckBox() {
        return requestAccountDeletionCheckBox;
    }

    public JButton getDeleteSelectedPrescriptionButton() {
        return deleteSelectedPrescriptionButton;
    }

    public JTextField getUserAddress() {
        return userAddress;
    }

    public JTextField getUserGender() {
        return userGender;
    }

    public JButton getNewFeedbackButton() {
        return newFeedbackButton;
    }

    public JTextField getUserAuthority() {
        return userAuthority;
    }

    public JButton getNewNoteButton() {
        return newNoteButton;
    }

    public JButton getNewMedicineButton() {
        return newMedicineButton;
    }

    public JButton getNewPrescriptionButton() {
        return newPrescriptionButton;
    }

    public JSpinner getUserAge() {
        return userAge;
    }

    public JLabel getAppointmentEarliestOrConfirmedLabel() {
        return appointmentEarliestOrConfirmedLabel;
    }

    public JButton getDeleteSelectedAppointmentButton() {
        return deleteSelectedAppointmentButton;
    }

    public JLabel getPrescriptionDosageWarningMessage() {
        return prescriptionDosageWarningMessage;
    }

    public JComboBox getPrescriptionPatientSelectCombo() {
        return prescriptionPatientSelectCombo;
    }

    public JButton getDeleteSelectedNoteButton() {
        return deleteSelectedNoteButton;
    }

    public JButton getDeleteSelectedFeedbackButton() {
        return deleteSelectedFeedbackButton;
    }

    public JButton getSaveEditedPrescriptionButton() {
        return saveEditedPrescriptionButton;
    }

    public JButton getSaveEditedMedicineButton() {
        return saveEditedMedicineButton;
    }

    public JButton getDeleteSelectedMedicineButton() {
        return deleteSelectedMedicineButton;
    }

    public JSpinner getPrescriptionQuantitySelector() {
        return prescriptionQuantitySelector;
    }

    public JSpinner getRegisterPatientAge() {
        return registerPatientAge;
    }

    public JButton getSaveEditedNoteButton() {
        return saveEditedNoteButton;
    }

    public JPanel getAppointmentEditPanel() {
        return appointmentEditPanel;
    }

    public JLabel getAppointmentEditTitle() {
        return appointmentEditTitle;
    }

    public JPanel getFeedbackPanel() {
        return feedbackPanel;
    }

    public JList getFeedbackList() {
        return feedbackList;
    }

    public JLabel getPatientViewNewEditLabel() {
        return patientViewNewEditLabel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getLoginNoIDMessage() {
        return loginNoIDMessage;
    }

    public JTextArea getPatientNoteTextArea() {
        return patientNoteTextArea;
    }

    public JComboBox getMedicineSelectCombo() {
        return medicineSelectCombo;
    }

    public JButton getAddPatientPrescriptionButton() {
        return addPatientPrescriptionButton;
    }

    public JTextField getPatientPrescriptionDosage() {
        return patientPrescriptionDosage;
    }

    public JTextField getUserName() {
        return userName;
    }

    public JButton getSubmitFeedbackButton() {
        return submitFeedbackButton;
    }

    public JButton getSubmitPrescriptionButton() {
        return submitPrescriptionButton;
    }

    public JPanel getRegisterMainPanel() {
        return registerMainPanel;
    }

    public JButton getSubmitAppointmentButton() {
        return submitAppointmentButton;
    }

    public JCheckBox getAppointmentConfirmedCheckbox() {
        return appointmentConfirmedCheckbox;
    }

    public JTextField getAppointmentLatestDate() {
        return appointmentLatestDate;
    }

    public JTextField getAppointmentEarliestDate() {
        return appointmentEarliestDate;
    }

    public JComboBox getAppointmentDoctorSelectCombo() {
        return appointmentDoctorSelectCombo;
    }

    public JComboBox getAppointmentPatientSelectCombo() {
        return appointmentPatientSelectCombo;
    }

    public JButton getSubmitMedicineButton() {
        return submitMedicineButton;
    }

    public JCheckBox getMedicineStockedCheckbox() {
        return medicineStockedCheckbox;
    }

    public JTextField getMedicineName() {
        return medicineName;
    }

    public JButton getUpdateUserButton() {
        return updateUserButton;
    }

    public JCheckBox getUserApprovedCheckBox() {
        return userApprovedCheckBox;
    }

    public JCheckBox getFeedbackApprovedCheckBox() {
        return feedbackApprovedCheckBox;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public JComboBox getPatientViewSelectCombo() {
        return patientViewSelectCombo;
    }

    public JPanel getPatientNotePanel() {
        return patientNotePanel;
    }

    public JPanel getPatientViewSelectPanel() {
        return patientViewSelectPanel;
    }

    public JPanel getLogoutPanel() {
        return logoutPanel;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JTextField getSettingsUserID() {
        return settingsUserID;
    }

    public JComboBox getSettingsUserAuthority() {
        return settingsUserAuthority;
    }

    public JButton getRequestDeleteAccountButton() {
        return requestDeleteAccountButton;
    }

    public JTextField getPrescriptionDosage() {
        return prescriptionDosage;
    }

    public JLabel getPrescription() {
        return prescription;
    }

    public JCheckBox getPrescriptionProcessedCheckBox() {
        return prescriptionProcessedCheckBox;
    }

    public JComboBox getPrescriptionMedicineSelectCombo() {
        return prescriptionMedicineSelectCombo;
    }

    public JTextArea getFeedbackTextArea() {
        return feedbackTextArea;
    }

    public JComboBox getFeedbackDoctorSelectCombo() {
        return feedbackDoctorSelectCombo;
    }

    public JSlider getFeedbackRatingSlider() {
        return feedbackRatingSlider;
    }

    public JPasswordField getLoginPasswordField() {
        return loginPasswordField;
    }

    public JLabel getMedicineStockWarningMessage() {
        return medicineStockWarningMessage;
    }

    public JLabel getPrescriptionInsufficientStockWarningMessage() {
        return prescriptionInsufficientStockWarningMessage;
    }

    public JComboBox getPrescriptionDoctorComboSelect() {
        return prescriptionDoctorComboSelect;
    }

    public JComboBox getFeedbackPatientSelectCombo() {
        return feedbackPatientSelectCombo;
    }

    public JSpinner getUserListID() {
        return userListID;
    }

    public JPanel getRegisterSuccessPanel() {
        return registerSuccessPanel;
    }

    public JSpinner getMedicineStockSpinner() {
        return medicineStockSpinner;
    }

    public JLabel getLoginWrongPasswordMessage() {
        return loginWrongPasswordMessage;
    }

    public JPasswordField getRegisterPasswordField() {
        return registerPasswordField;
    }

    public JPasswordField getRegisterPasswordConfirmField() {
        return registerPasswordConfirmField;
    }

    public static void main(String[] args) {
        GUI = new GUI();
        GUI.construct();

        // setup controller //
        Controller.main();
    }

    public void construct() {
        // create frame //
        frame = new JFrame("Patient Management System");
        frame.setContentPane(GUI.getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set initial form size to 2/3rd of screen //
        // get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;

        // set the frame height and width
        frame.setPreferredSize(new Dimension(width, height));

        // update tabs //
        Controller.updateTabs(GUI);

        // setup UI //
        createUIComponents();

        // display frame //
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // initialise combo-boxes //
        JComboBox[] comboBoxes = new JComboBox[2];
        comboBoxes[0] = GUI.getComboAuthorityRegister();
        comboBoxes[1] = GUI.getComboAuthorityLogin();

        for (int i = 0; i < comboBoxes.length; i++) {
            comboBoxes[i].addItem(new ComboItem("Admin", "A"));
            comboBoxes[i].addItem(new ComboItem("Doctor", "D"));
            comboBoxes[i].addItem(new ComboItem("Secretary", "S"));
            comboBoxes[i].addItem(new ComboItem("Patient", "P"));
        }

    }
}
