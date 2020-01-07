package user;

import data.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdministratorTest {

    public Administrator instance;

    @BeforeEach
    void setUp() {
        Data.setData(new Data());
        instance = new Administrator("name", "address");
    }

    @AfterEach
    void tearDown() {
        instance = null;
        Data.setData(null);
    }

    @Test
    void getAuthority() {
        System.out.println("getAuthority");
        char expResult = 'A';
        char result = instance.getAuthority();
        assertEquals(expResult, result);
    }

    @Test
    void getId() {
        System.out.println("getId");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getName() {
        System.out.println("getName");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    void getAddress() {
        System.out.println("getAddress");
        String expResult = "address";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }
}