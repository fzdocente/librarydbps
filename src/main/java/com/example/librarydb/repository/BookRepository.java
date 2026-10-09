package com.example.librarydb.repository;


import com.example.librarydb.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // MongoRepository provee automaticamente los metodos save(), findById(), findAll(), deleteById(), etc.
    // Buscar libros cuyo título contenga el texto indicado
    List<Book> findByTitleContainingIgnoreCase(String title);
}