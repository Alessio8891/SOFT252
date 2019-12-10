package system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    public Feedback instance;

    @BeforeEach
    void setUp() {
        instance = new Feedback(0, 5, "string");
    }

    @AfterEach
    void tearDown() {
        instance = null;
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
        int expResult = 0;
        int result = instance.getDoctor();
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