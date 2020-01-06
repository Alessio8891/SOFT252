package user;

import data.Data;

/**
 * @author zacharysmith
 */
public class Secretary extends AbstractUser {

    public Secretary(String name, String address, char[] password) {
        this.authority = 'S';
        this.id = Data.getData().getUniqueSecretaryID();
        this.password = password;
        this.name = name;
        this.address = address;
        this.approved = false;
        this.requestDelete = false;
    }

    public Secretary(String name, String address) {
        this.authority = 'S';
        this.id = Data.getData().getUniqueSecretaryID();
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
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean getApproved() {
        return approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
