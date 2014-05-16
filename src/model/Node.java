package model;

import java.util.ArrayList;

public class Node {

    String value;
    String name;
    ArrayList<Node> nodes;
    ArrayList<Attribute> attributes;

    public Node(String name, String value) {
        this.attributes = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.name = name;
        this.value = value;
    }

    public Node(String name) {
        this.name = name;
    }

    public Node() {
        this.attributes = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public void removeNode(Node node) {
        if (nodes.contains(node)) {
            nodes.remove(node);
        }
    }

    public void removeNode(int index) {
        if (nodes.size() > index && index >= 0) {
            nodes.remove(index);
        }
    }

    public Node getNode(int index) {
        if (nodes.size() > index && index >= 0) {
            return nodes.get(index);
        }
        return null;
    }

    public void addAttribute(Attribute attr) {
        if (!attributes.contains(attr)) {
            attributes.add(attr);
        }
    }

    public void removeAttribute(Attribute attr) {
        if (attributes.contains(attr)) {
            attributes.remove(attr);
        }
    }

    public void removeAttribute(int index) {
        if (attributes.size() > index && index >= 0) {
            attributes.remove(index);
        }
    }

    public Attribute getAttribute(int index) {
        if (attributes.size() > index && index >= 0) {
            return attributes.get(index);
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
