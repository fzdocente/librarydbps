package com.example.librarydb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BookDTO {

    private String id;

    // Rechaza cadenas vacias, nulas o con solo espacios
    @NotBlank(message = "El titulo no puede estar vacio")
    private String title;

    @NotBlank(message = "El autor es obligatorio")
    private String author;

    // Aplica expresion regular para validar formato valido de ISBN-10 o ISBN-13
    @NotBlank(message = "El ISBN no puede estar vacio")
    @Pattern(regexp = "^(?:\\d{9}[\\dX]|\\d{13})$", message = "Formato de ISBN no valido (ej. 9780307474728)")
    private String isbn;

    private boolean available = true;

    public BookDTO() {}

    public BookDTO(String id, String title, String author, String isbn, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
