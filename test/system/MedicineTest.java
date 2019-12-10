package system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {

    public Medicine instance;

    @BeforeEach
    void setUp() {
        instance = new Medicine("name");
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void getName() {
        System.out.println("getName");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}