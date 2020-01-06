package view;

import system.*;
import user.AbstractUser;

public class ListItem {

    private String key;
    private Object value;

    public ListItem(String key, AbstractUser value) {
        this.key = key;
        this.value = value;
    }

    public ListItem(String key, PatientNote value) {
        this.key = key;
        this.value = value;
    }

    public ListItem(String key, Appointment value) {
        this.key = key;
        this.value = value;
    }

    public ListItem(String key, Feedback value) {
        this.key = key;
        this.value = value;
    }

    public ListItem(String key, Prescription value) {
        this.key = key;
        this.value = value;
    }

    public ListItem(String key, Medicine value) {
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

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }
}
