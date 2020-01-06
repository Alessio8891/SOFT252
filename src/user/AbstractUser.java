package user;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public abstract class AbstractUser implements Serializable {

    protected char authority;
    protected int id;
    protected char[] password;
    protected String name;
    protected String address;
    protected boolean approved;
    protected boolean requestDelete;

    public abstract boolean getRequestDelete();

    public abstract void setRequestDelete(boolean requestDelete);

    public abstract char getAuthority();

    public abstract char[] getPassword();

    public abstract int getId();

    public abstract void setId(int id);

    public abstract boolean getApproved();

    public abstract void setApproved(boolean approved);

    public abstract String getName();

    public abstract String getAddress();
}
