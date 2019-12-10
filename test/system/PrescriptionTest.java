package system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

    public Prescription instance;
    public Medicine medicine;

    @BeforeEach
    void setUp() {
        medicine = new Medicine("name");
        instance = new Prescription(0, 0, "notes", medicine, 10, "dosage");
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void getDoctor() {
        System.out.println("getDoctor");
        int expResult = 0;
        int result = instance.getDoctor();
        assertEquals(expResult, result);
    }

    @Test
    void getPatient() {
        System.out.println("getPatient");
        int expResult = 0;
        int result = instance.getPatient();
        assertEquals(expResult, result);
    }

    @Test
    void getNotes() {
        System.out.println("getNotes");
        String expResult = "notes";
        String result = instance.getNotes();
        assertEquals(expResult, result);
    }

    @Test
    void getMedicine() {
        System.out.println("getMedicine");
        Medicine expResult = medicine;
        Medicine result = instance.getMedicine();
        assertEquals(expResult, result);
    }

    @Test
    void getQuantity() {
        System.out.println("getQuantity");
        int expResult = 10;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    @Test
    void getDosage() {
        System.out.println("getDosage");
        String expResult = "dosage";
        String result = instance.getDosage();
        assertEquals(expResult, result);
    }
}