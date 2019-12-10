package user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    public Patient instance;

    @BeforeEach
    void setUp() {
        instance = new Patient('P', "name", "address", 34, "gender");
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void setId() {
        System.out.println("setId");
        int id = 0;
        instance.setId(id);
    }

    @Test
    void getAge() {
        System.out.println("getAge");
        int expResult = 34;
        int result = instance.getAge();
        assertEquals(expResult, result);
    }

    @Test
    void getGender() {
        System.out.println("getGender");
        String expResult = "gender";
        String result = instance.getGender();
        assertEquals(expResult, result);
    }

    @Test
    void setApproved() {
        System.out.println("setApproved");
        boolean approved = true;
        instance.setApproved(approved);
    }

    @Test
    void getApproved() {
        System.out.println("getApproved");
        boolean expResult = false;
        boolean result = instance.getApproved();
        assertEquals(expResult, result);
    }

    @Test
    void getAuthority() {
        System.out.println("getAuthority");
        char expResult = 'P';
        char result = instance.getAuthority();
        assertEquals(expResult, result);
    }

    @Test
    void getId() {
        System.out.println("getId");
        instance.setId(0);
        int expResult = 0;
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