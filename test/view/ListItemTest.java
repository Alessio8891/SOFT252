package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListItemTest {
    private ListItem instance;

    @BeforeEach
    void setUp() {
        instance = new ListItem("test", "test");
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void testToString() {
        System.out.println("Testing ComboItem.toString()");
        assertEquals(instance.toString(), "test", "Keys do not match");
    }

    @Test
    void getKey() {
        System.out.println("Testing ComboItem.getKey()");
        assertEquals(instance.getKey(), "test", "Keys do not match");
    }

    @Test
    void getValue() {
        System.out.println("Testing ComboItem.getValue()");
        assertEquals(instance.getValue(), "test", "Values do not match");
    }

    @Test
    void setKey() {
        System.out.println("Testing ComboItem.setKey()");
        instance.setKey("new");
        assertEquals(instance.getKey(), "new", "Keys do not match");
    }
}