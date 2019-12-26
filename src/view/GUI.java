package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class GUI {
    private JTabbedPane tabbedPane;
    private JPanel contentPane;
    private JButton loginButton;
    private JTextField UserID;
    private JComboBox comboAuthority2;
    private JComboBox comboAuthority1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton registerButton;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JPanel patientsPanel;
    private JPanel patientViewPanel;
    private JPanel appointmentsPanel;
    private JPanel medicinesPanel;
    private JPanel usersPanel;
    private JPanel prescriptionsPanel;
    private JButton addNoteButton;
    private JList appointmentList;
    private JList patientHistory;
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
    private JButton editFeedbackButton;
    private JButton buttoneditButton;

    private JFrame frame;
    private static GUI GUI;

    public GUI() {
        // add tab change event listener //
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();

                System.out.println("Tab: " + tabbedPane.getSelectedIndex());

                // do not trigger when removing tabs
                if (index >= 0) {
                    switch (tabbedPane.getTitleAt(index)) {
                        case "Login":
                        case "Register":
                            break;
                        case "GUI List":
                            break;
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI = new GUI();
        GUI.construct();
    }

    public void construct() {
        // create frame //
        frame = new JFrame("GUI");
        frame.setContentPane(GUI.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set initial form size to 2/3rd of screen //
        // get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;

        // set the frame height and width
        frame.setPreferredSize(new Dimension(width, height));

        // update tabs //
        displayTabs();

        // setup UI //
        createUIComponents();

        // display frame //
        frame.pack();
        frame.setVisible(true);
    }

    public void displayTabs() {
        // remove tabs for login / register
        // allows for displayTabs to have no argument for when no authority is known
        tabbedPane.removeAll();

        // set tab names
        loginPanel.setName("Login");
        registerPanel.setName("Register");

        tabbedPane.add(loginPanel);
        tabbedPane.add(registerPanel);
    }
    public void displayTabs(String authority) {
        // clear panels //
        tabbedPane.removeAll();

        // add panels applicable to all authorities //
        patientViewPanel.setName("GUI View");
        appointmentsPanel.setName("Appointment List");
        prescriptionsPanel.setName("Prescription List");

        tabbedPane.add(patientViewPanel);
        tabbedPane.add(appointmentsPanel);
        tabbedPane.add(prescriptionsPanel);

        // add authority specific panels //
        switch (authority) {
            case "A":
                usersPanel.setName("User List");
                medicinesPanel.setName("Medicine List");
                patientsPanel.setName("GUI List");

                tabbedPane.add(usersPanel);
                tabbedPane.add(medicinesPanel);
                tabbedPane.add(patientsPanel);
                break;
            case "D":
            case "S":
                medicinesPanel.setName("Medicine List");
                patientsPanel.setName("GUI List");

                tabbedPane.add(medicinesPanel);
                tabbedPane.add(patientsPanel);
                break;
            case "P":
                break;
        }
    }

    private void createUIComponents() {
        // initialise combo-boxes //
        JComboBox[] comboBoxes = new JComboBox[2];
        comboBoxes[0] = comboAuthority1;
        comboBoxes[1] = comboAuthority2;

        for (int i = 0; i < comboBoxes.length; i++) {
            comboBoxes[i].addItem(new ComboItem("Admin", "A"));
            comboBoxes[i].addItem(new ComboItem("Doctor", "D"));
            comboBoxes[i].addItem(new ComboItem("Secretary", "S"));
            comboBoxes[i].addItem(new ComboItem("GUI", "P"));
        }

    }
}
