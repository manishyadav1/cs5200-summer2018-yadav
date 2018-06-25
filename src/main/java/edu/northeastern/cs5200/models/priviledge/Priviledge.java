package edu.northeastern.cs5200.models.priviledge;

public class Priviledge {

    private int id;
    private String priviledgeName;

    public Priviledge() {
        super();
    }

    public Priviledge(int id, String priviledgeName) {
        super();
        this.id = id;
        this.priviledgeName = priviledgeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriviledgeName() {
        return priviledgeName;
    }

    public void setPriviledgeName(String priviledgeName) {
        this.priviledgeName = priviledgeName;
    }
}
