package user;

/**
 * @author zacharysmith
 */
public class Doctor extends AbstractUser {

    public Doctor(char authority, int id, String name, String address) {
        this.authority = authority;
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public char getAuthority() {
        return authority;
    }

    @Override
    public int getId() {
        return id;
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
