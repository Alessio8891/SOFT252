package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialise {
    public static void serialise() {
        main(Data.getData(), "serial.ser");
    }

    public static void main(Data dataset, String filepath) {
        try {
            // ensure file exists //
            File serial = new File(filepath);
            serial.createNewFile();

            FileOutputStream fileOut = new FileOutputStream(filepath);

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dataset);
            out.close();

            fileOut.close();

            System.out.printf("Serialized data is saved in '/%s'.\n", filepath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}