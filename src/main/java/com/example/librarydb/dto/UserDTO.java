package com.example.librarydb.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    private String iduser;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullname;

    private boolean sanctioned;

    public UserDTO() {
    }

    public UserDTO(String iduser, String fullname, boolean sanctioned) {
        this.iduser = iduser;
        this.fullname = fullname;
        this.sanctioned = sanctioned;
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
}
