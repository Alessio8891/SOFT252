package system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    public Appointment instance;
    public String[] date_range;

    @BeforeEach
    void setUp() {
        date_range = new String[]{"min", "max"};
        instance = new Appointment(0, 0, date_range);
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
    void setDoctor() {
        System.out.println("setDoctor");
        int doctor = 1;
        instance.setDoctor(doctor);
    }

    @Test
    void getDate() {
        System.out.println("getDate");
        String[] expResult = date_range;
        String[] result = instance.getDate();
        assertArrayEquals(expResult, result);
    }

    @Test
    void setDate() {
        System.out.println("setDate");
        String[] final_date = new String[]{"final"};
        instance.setDate(final_date);
    }

    @Test
    void getPatient() {
        System.out.println("getPatient");
        int expResult = 0;
        int result = instance.getPatient();
        assertEquals(expResult, result);
    }
}