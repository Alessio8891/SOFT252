package view;

import system.Medicine;
import user.AbstractUser;
import user.Patient;

public class ComboItem {

    private String key;
    private final Object value;

    public ComboItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem(String key, Patient value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem(String key, AbstractUser value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem(String key, Medicine value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
