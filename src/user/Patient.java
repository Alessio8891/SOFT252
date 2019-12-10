package user;

/**
 * @author zacharysmith
 */
public class Patient extends AbstractUser {
    protected int age;
    protected String gender;
    protected boolean approved;

    public Patient(char authority, String name, String address, int age, String gender) {
        this.authority = authority;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.approved = false;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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
