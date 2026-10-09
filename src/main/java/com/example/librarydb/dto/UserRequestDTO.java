package com.example.librarydb.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {

    private String iduser;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullname;

    private boolean sanctioned;

    @NotBlank(message = "El password es obligatorio")
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(
            String iduser,
            String fullname,
            boolean sanctioned,
            String password) {

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
