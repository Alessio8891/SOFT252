package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialise {
    public static void deserialise() {
        Data dataset = main("serial.ser");
        Data.setData(dataset);
    }

    public static Data main(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Data dataset = (Data) in.readObject();

            in.close();
            fileIn.close();

            return dataset;
        } catch (IOException i) {
            i.printStackTrace();

            Data dataset = new Data();
            Serialise.main(dataset, filepath);

            System.out.printf("Created './%s'.\n", filepath);
            return dataset;
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
            return null;
        }
    }
}
