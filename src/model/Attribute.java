package model;

import java.util.HashMap;

public class Attribute {

    HashMap<String, String> attributes;

    public Attribute() {
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String key, String value) {
        if (!attributes.containsKey(key)) {
            attributes.put(key, value);
        }
    }

    public void removeAttribute(String key) {
        if (attributes.containsKey(key)) {
            attributes.remove(key);
        }
    }

    public String getValue(String key) {
        return attributes.get(key);
    }
}
