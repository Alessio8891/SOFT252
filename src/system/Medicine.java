package system;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public class Medicine implements Serializable {

    private final String name;
    private int stock;

    public Medicine(String name) {
        this.name = name;
        this.stock = 0;
    }

    public Medicine(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void addStock(int stock) {
        this.stock += stock;
    }

    public void useStock(int stock) {
        this.stock -= stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
