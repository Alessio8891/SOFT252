package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeserialiseTest {
    private Deserialise instance;

    @BeforeEach
    void setUp() {
        instance = new Deserialise();
    }

    @AfterEach
    void tearDown() {
        instance = null;

        File file = new File("test.ser");
        file.delete();
    }

    @Test
    void main() throws IOException, ClassNotFoundException {
        System.out.println("Testing Deserialise.main()");
        Data test = Deserialise.main("test.ser");

        FileInputStream fileIn = new FileInputStream("test.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);

        Data real = (Data) in.readObject();

        in.close();
        fileIn.close();

        assertDataInstancesAreSimilar(real, test);
    }

    void assertDataInstancesAreSimilar(Data real, Data expected) {
        assertEquals(real.getUser(), expected.getUser(), "user arrays are not equal.");
        assertEquals(real.getPatient(), expected.getPatient(), "patient arrays are not equal.");
        assertEquals(real.getMedicine(), expected.getMedicine(), "medicine arrays are not equal.");
        assertEquals(real.getPrescription(), expected.getPrescription(), "prescription arrays are not equal.");
        assertEquals(real.getFeedback(), expected.getFeedback(), "feedback arrays are not equal.");
        assertEquals(real.getAppointment(), expected.getAppointment(), "appointment arrays are not equal.");
    }
}