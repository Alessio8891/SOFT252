package user;

import data.Data;

/**
 * @author zacharysmith
 */
public class Doctor extends AbstractUser {

    public Doctor(String name, String address, char[] password) {
        this.authority = 'D';
        this.id = Data.getData().getUniqueDoctorID();
        this.password = password;
        this.name = name;
        this.address = address;
        this.approved = false;
        this.requestDelete = false;
    }

    public Doctor(String name, String address) {
        this.authority = 'D';
        this.id = Data.getData().getUniqueDoctorID();
        this.name = name;
        this.address = address;
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

    @Override
    public char getAuthority() {
        return authority;
    }

    @Override
    public char[] getPassword() {
        return password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }


}
