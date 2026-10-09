package com.example.librarydb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LoanDTO {

    private String id;

    @NotBlank(message = "El ID del libro es obligatorio")
    private String bookId;

    @NotBlank(message = "El ID del usuario es obligatorio")
    private String iduser;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    private LocalDate loanDate;

    private LocalDate returnDate;

    public LoanDTO() {
    }

    public LoanDTO(
            String id,
            String bookId,
            String iduser,
            LocalDate loanDate,
            LocalDate returnDate) {

        this.id = id;
        this.bookId = bookId;
        this.iduser = iduser;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}