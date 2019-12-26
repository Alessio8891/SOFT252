package data;

import java.io.*;
public class Serialise {

    public static void main(String [] args) {
        try {
            FileOutputStream fileOut = new FileOutputStream("/serial.ser");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Data.data);
            out.close();

            fileOut.close();

            System.out.printf("Serialized data is saved in /serial.ser");
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
}