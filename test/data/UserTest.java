package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @BeforeEach
    void setUp() {
        User.setUser(new User('A', 1));
    }

    @AfterEach
    void tearDown() {
        User instance = null;
    }

    @Test
    void getUser() {
        System.out.println("Testing User.getUser()");
        assertUserInstancesAreSimilar(new User('A', 1), User.getUser());
    }

    @Test
    void setUser() {
        System.out.println("Testing User.getUser()");
        User newUser = new User('P', 2);
        User.setUser(newUser);
        assertUserInstancesAreSimilar(newUser, User.getUser());
    }

    @Test
    void getAuthority() {
        System.out.println("Testing User.getUser()");
        char authority = 'A';
        assertEquals(authority, User.getUser().getAuthority(), "authorities do not match.");
    }

    @Test
    void getId() {
        System.out.println("Testing User.getUser()");
        int id = 1;
        assertEquals(id, User.getUser().getId(), "IDs do not match.");
    }

    void assertUserInstancesAreSimilar(User expected, User actual) {
        assertEquals(expected.getAuthority(), actual.getAuthority(), "Authorities do not match");
        assertEquals(expected.getId(), actual.getId(), "IDs do not match");
    }
}