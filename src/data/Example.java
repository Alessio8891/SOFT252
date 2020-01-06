package data;

import system.*;
import user.Administrator;
import user.Doctor;
import user.Patient;
import user.Secretary;

public class Example {
    // populates dataset with example data //

    public static void main(String[] args) {
        // load dataset //
        Data.data = new Data();
        Deserialise.deserialise();

        // default password
        char[] password = new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};

        // populate clinic users //
        Administrator admin1 = new Administrator("Steve", "Edwards", password);
        admin1.setApproved(true);
        Data.getData().getUser().add(admin1);

        Doctor doctor1 = new Doctor("Bob", "Ross", password);
        doctor1.setApproved(true);
        Data.getData().getUser().add(doctor1);
        Doctor doctor2 = new Doctor("Bob", "Frost", password);
        Data.getData().getUser().add(doctor2);
        Doctor doctor3 = new Doctor("Ross", "Bob", password);
        Data.getData().getUser().add(doctor3);

        Patient patient1 = new Patient("Marley", "St Street", 12, "Male", password);
        patient1.setApproved(true);
        Data.getData().getPatient().add(patient1);
        Patient patient2 = new Patient("Mary", "St Street", 30, "Female", password);
        Data.getData().getPatient().add(patient2);
        Patient patient3 = new Patient("Jane", "St Street", 29, "Non-Binary", password);
        Data.getData().getPatient().add(patient3);

        Secretary secretary1 = new Secretary("Samuel", "Northgard", password);
        Data.getData().getUser().add(secretary1);
        secretary1.setApproved(true);

        // populate stocked medicines //
        Medicine medicine1 = new Medicine("Paracetamol", 10);
        Data.getData().getMedicine().add(medicine1);
        Medicine medicine2 = new Medicine("Ibuprofen", 10);
        Data.getData().getMedicine().add(medicine2);
        Medicine medicine3 = new Medicine("Acetaminophen", 10);
        Data.getData().getMedicine().add(medicine3);
        Medicine medicine4 = new Medicine("Aspirin", 10);
        Data.getData().getMedicine().add(medicine4);
        Medicine medicine5 = new Medicine("Buscopan", 10);
        Data.getData().getMedicine().add(medicine5);
        Medicine medicine6 = new Medicine("Benadryl", 10);
        Data.getData().getMedicine().add(medicine6);
        Medicine medicine7 = new Medicine("Xanax", 10);
        Data.getData().getMedicine().add(medicine7);
        Medicine medicine8 = new Medicine("Codiene", 10);
        Data.getData().getMedicine().add(medicine8);
        Medicine medicine9 = new Medicine("Nembutal", 10);
        Data.getData().getMedicine().add(medicine9);
        Medicine medicine10 = new Medicine("Ambien", 10);
        Data.getData().getMedicine().add(medicine10);

        // populate appointments //
        String[] dates = new String[2];
        dates[0] = "11/03/19";
        dates[1] = "14/03/19";
        Appointment appointment1 = new Appointment(doctor1, patient1, dates);
        Data.getData().getAppointment().add(appointment1);

        // populate prescriptions //
        Prescription prescription1 = new Prescription(doctor1, patient1, medicine1, 10, "Twice daily.");
        Data.getData().getPrescription().add(prescription1);

        // populate feedback //
        Feedback feedback1 = new Feedback(doctor1, patient1, 4, "Overall good");
        Data.getData().getFeedback().add(feedback1);

        // populate patient1 history //
        PatientNote patientnote1 = new PatientNote(doctor1, "A random note");
        Data.getData().getPatient().get(0).getPatientNotes().add(patientnote1);

        // save //
        Serialise.serialise();
    }
}
