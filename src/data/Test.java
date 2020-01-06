package data;

public class Test {
    public static void main(String[] args) {
        Deserialise.deserialise();

        System.out.println(Data.getData().getPatient().get(0).getPatientNotes().get(0).getNote());
    }
}
