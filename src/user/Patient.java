package user;

import data.Data;
import system.PatientNote;

import java.util.ArrayList;

/**
 * @author zacharysmith
 */
public class Patient extends AbstractUser {
    protected final int age;
    protected final String gender;
    protected boolean approved;
    private ArrayList<PatientNote> patientNotes;

    public Patient(String name, String address, int age, String gender, char[] password) {
        this.authority = 'P';
        this.id = Data.getUniquePatientID();
        this.password = password;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.approved = false;
        this.patientNotes = new ArrayList<>();
        this.requestDelete = false;
    }

    public Patient(String name, String address, int age, String gender) {
        this.authority = 'P';
        this.id = Data.getUniquePatientID();
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.approved = false;
        this.requestDelete = false;
    }

    @Override
    public boolean getRequestDelete() {
        return requestDelete;
    }

    @Override
    public void setRequestDelete(boolean requestDelete) {
        this.requestDelete = requestDelete;
    }

    public boolean isApproved() {
        return approved;
    }

    public ArrayList<PatientNote> getPatientNotes() {
        return patientNotes;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public boolean getApproved() {
        return approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public char[] getPassword() {
        return password;
    }

    @Override
    public char getAuthority() {
        return authority;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

}
