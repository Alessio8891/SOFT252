package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerialiseTest {
    private Serialise instance;
    private Data dataset;

    @BeforeEach
    void setUp() {
        instance = new Serialise();
        dataset = new Data();
    }

    @AfterEach
    void tearDown() {
        instance = null;
        dataset = null;

        File file = new File("test.ser");
        file.delete();
    }

    @Test
    void main() throws IOException, ClassNotFoundException {
        System.out.println("Testing Serialise.main()");
        Serialise.main(dataset, "test.ser");

        FileInputStream fileIn = new FileInputStream("test.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);

        Data real = (Data) in.readObject();

        in.close();
        fileIn.close();

        assertDataInstancesAreSimilar(real, dataset);
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