package com.example.librarydb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

// Mapea la clase a la colección "loans" en MongoDB
@Document(collection = "loans")
public class Loan {

    @Id
    private String id;

    // ID del libro asociado al préstamo
    // @Indexed agiliza las búsquedas por bookId
    @Indexed
    private String bookId;

    // ID del usuario que realiza el préstamo
    // Se almacena el ID plano en lugar de utilizar @DBRef
    // @Indexed agiliza las búsquedas por iduser
    @Indexed
    private String iduser;

    private LocalDate loanDate;

    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(
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