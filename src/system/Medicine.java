package system;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public class Medicine implements Serializable {

    private String name;

    public Medicine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
