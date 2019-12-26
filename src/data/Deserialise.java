package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialise {
    public static void main(String [] args) {
        Data.data = null;

        try {
            FileInputStream fileIn = new FileInputStream("/serial.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Data.data = (Data) in.readObject();

            in.close();
            fileIn.close();
        }
        catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }
    }
}
