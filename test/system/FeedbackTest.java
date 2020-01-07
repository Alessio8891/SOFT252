package system;

import data.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.Doctor;
import user.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackTest {

    public Feedback instance;

    @BeforeEach
    void setUp() {
        Data.setData(new Data());

        Patient patient = new Patient("Name", "address", 12, "male");
        Doctor doctor = new Doctor("Name", "Address");
        instance = new Feedback(doctor, patient, 5, "string");
    }

    @AfterEach
    void tearDown() {
        instance = null;
        Data.setData(null);
    }

    @Test
    void getFeedback() {
        System.out.println("getFeedback");
        String expResult = "string";
        String result = instance.getFeedback();
        assertEquals(expResult, result);
    }

    @Test
    void setFeedback() {
        System.out.println("setFeedback");
        String feedback = "edited string";
        instance.setFeedback(feedback);
    }

    @Test
    void getApproved() {
        System.out.println("isApproved");
        boolean expResult = false;
        boolean result = instance.getApproved();
        assertEquals(expResult, result);
    }

    @Test
    void setApproved() {
        System.out.println("setApproved");
        boolean approved = true;
        instance.setApproved(approved);
    }

    @Test
    void getDoctor() {
        System.out.println("getDoctor");
        int expResult = 1;
        int result = instance.getDoctor().getId();
        assertEquals(expResult, result);
    }

    @Test
    void getRating() {
        System.out.println("getRating");
        int expResult = 5;
        int result = instance.getRating();
        assertEquals(expResult, result);
    }
}