package user;

/**
 * @author zacharysmith
 */
public abstract class AbstractUser {

    protected char authority;
    protected int id;
    protected String name;
    protected String address;

    abstract char getAuthority();

    abstract int getId();

    abstract String getName();

    abstract String getAddress();


}
