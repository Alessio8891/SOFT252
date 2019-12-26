package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialise {
    public static void deserialise() {
        Data.data = null;

        try {
            FileInputStream fileIn = new FileInputStream("serial.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Data.data = (Data) in.readObject();

            in.close();
            fileIn.close();
        }
        catch (IOException i) {
            i.printStackTrace();

            // create initial ./serial.ser file //
            Data.data = new Data();
            Serialise.serialise();

            System.out.println("Created './serial.ser', please reload.");
            return;
        }
        catch (ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }
    }
}
