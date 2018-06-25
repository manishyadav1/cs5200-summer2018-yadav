package edu.northeastern.cs5200.models.person;

import java.util.Date;

public class User extends Person {

    private boolean userAgreement;

    public User() {
        super();
    }

    public User(int id, String firstName, String lastName, String username, String password,
                String email, Date dob, boolean userAgreement) {
        super(id, firstName, lastName, username, password, email, dob);
        this.userAgreement = userAgreement;
    }

    public User(String firstName, String lastName, String username, String password,
                String email, Date dob, boolean userAgreement) {
        super(firstName, lastName, username, password, email, dob);
        this.userAgreement = userAgreement;
    }

    public boolean isUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(boolean userAgreement) {
        this.userAgreement = userAgreement;
    }
}
