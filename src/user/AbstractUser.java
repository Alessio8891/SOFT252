package user;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public abstract class AbstractUser implements Serializable {

    protected char authority;
    protected int id;
    protected String name;
    protected String address;

    abstract char getAuthority();

    abstract int getId();

    abstract String getName();

    abstract String getAddress();


}
