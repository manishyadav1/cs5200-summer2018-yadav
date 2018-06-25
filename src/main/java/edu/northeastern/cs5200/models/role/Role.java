package edu.northeastern.cs5200.models.role;

public class Role {

    private int id;
    private String roleName;

    public Role() {
        super();
    }

    public Role(int id, String roleName) {
        super();
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
