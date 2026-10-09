package com.example.librarydb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String iduser;

    private String fullname;

    private boolean sanctioned;

    private String password;

    public User() {
    }

    public User(String iduser, String fullname, boolean sanctioned, String password) {
        this.iduser = iduser;
        this.fullname = fullname;
        this.sanctioned = sanctioned;
        this.password = password;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isSanctioned() {
        return sanctioned;
    }

    public void setSanctioned(boolean sanctioned) {
        this.sanctioned = sanctioned;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}