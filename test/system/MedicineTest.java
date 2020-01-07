package system;

import data.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicineTest {

    public Medicine instance;

    @BeforeEach
    void setUp() {
        Data.setData(new Data());
        instance = new Medicine("name");
    }

    @AfterEach
    void tearDown() {
        instance = null;
        Data.setData(null);
    }

    @Test
    void getName() {
        System.out.println("getName");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}