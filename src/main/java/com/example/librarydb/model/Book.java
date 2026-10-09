package com.example.librarydb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapea la clase a la coleccion "books" en MongoDB
@Document(collection = "books")
public class Book {

    // Identificador unico autogenerado por MongoDB (ObjectId)
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;

    // Indicador de disponibilidad para controlar la regla de negocio al prestar
    private boolean available = true;

    // Constructor sin argumentos necesario para la deserializacion de Spring Data
    public Book() {}

    // Constructor con argumentos
    public Book(String id, String title, String author, String isbn, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    // Métodos Getters y Setters
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