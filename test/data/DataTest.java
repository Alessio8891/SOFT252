package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.Appointment;
import system.Feedback;
import system.Medicine;
import system.Prescription;
import user.AbstractUser;
import user.Patient;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataTest {

    @BeforeEach
    void setUp() {
        Data.setData(new Data());
    }

    @AfterEach
    void tearDown() {
        Data instance = null;
    }

    @Test
    void getData() {
        System.out.println("Testing Data.getData()");
        assertDataInstancesAreSimilar(new Data(), Data.getData());
    }

    @Test
    void setData() {
        System.out.println("Testing Data.setData()");
        Data newInstance = new Data();
        Data.setData(newInstance);
        assertEquals(Data.getData(), newInstance, "Instances do not match.");
    }

    @Test
    void getUniqueAdminID() {
        System.out.println("Testing Data.getUniqueAdminID()");
        int expectedID = 1;
        assertEquals(Data.getUniqueAdminID(), expectedID, "Unexpected ID generated.");
    }

    @Test
    void getUniqueDoctorID() {
        System.out.println("Testing Data.getUniqueDoctorID()");
        int expectedID = 1;
        assertEquals(Data.getUniqueDoctorID(), expectedID, "Unexpected ID generated.");
    }

    @Test
    void getUniqueSecretaryID() {
        System.out.println("Testing Data.getUniqueSecretaryID()");
        int expectedID = 1;
        assertEquals(Data.getUniqueSecretaryID(), expectedID, "Unexpected ID generated.");
    }

    @Test
    void getUniquePatientID() {
        System.out.println("Testing Data.getUniquePatientID()");
        int expectedID = 1;
        assertEquals(Data.getUniquePatientID(), expectedID, "Unexpected ID generated.");
    }

    @Test
    void getUser() {
        System.out.println("Testing Data.getUser()");
        ArrayList<AbstractUser> user = new ArrayList<>();
        assertEquals(Data.getData().getUser(), user, "Arraylists do not match");
    }

    @Test
    void findUser() {
        System.out.println("Testing Data.findUser()");
        assertNull(Data.getData().findUser('A', 1), "Invalid output.");
    }

    @Test
    void findPatient() {
        System.out.println("Testing Data.findPatient()");
        assertNull(Data.getData().findPatient(1), "Invalid output.");
    }

    @Test
    void getPatient() {
        System.out.println("Testing Data.getPatient()");
        ArrayList<Patient> patient = new ArrayList<>();
        assertEquals(Data.getData().getUser(), patient, "Arraylists do not match");
    }

    @Test
    void getAppointment() {
        System.out.println("Testing Data.getAppointment()");
        ArrayList<Appointment> appointment = new ArrayList<>();
        assertEquals(Data.getData().getUser(), appointment, "Arraylists do not match");
    }

    @Test
    void getFeedback() {
        System.out.println("Testing Data.getFeedback()");
        ArrayList<Feedback> feedback = new ArrayList<>();
        assertEquals(Data.getData().getUser(), feedback, "Arraylists do not match");
    }

    @Test
    void getMedicine() {
        System.out.println("Testing Data.getMedicine()");
        ArrayList<Medicine> medicine = new ArrayList<>();
        assertEquals(Data.getData().getUser(), medicine, "Arraylists do not match");
    }

    @Test
    void getPrescription() {
        System.out.println("Testing Data.getPrescription()");
        ArrayList<Prescription> prescription = new ArrayList<>();
        assertEquals(Data.getData().getUser(), prescription, "Arraylists do not match");
    }

    void assertDataInstancesAreSimilar(Data real, Data expected) {
        assertEquals(real.getUser(), expected.getUser(), "user arrays are not equal.");
        assertEquals(real.getPatient(), expected.getPatient(), "patient arrays are not equal.");
        assertEquals(real.getMedicine(), expected.getMedicine(), "medicine arrays are not equal.");
        assertEquals(real.getPrescription(), expected.getPrescription(), "prescription arrays are not equal.");
        assertEquals(real.getFeedback(), expected.getFeedback(), "feedback arrays are not equal.");
        assertEquals(real.getAppointment(), expected.getAppointment(), "appointment arrays are not equal.");
    }
}