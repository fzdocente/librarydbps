package com.example.librarydb.repository;

import com.example.librarydb.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {

    // Buscar todos los préstamos de un libro
    List<Loan> findByBookId(String bookId);

    // Buscar todos los préstamos de un usuario
    List<Loan> findByIduser(String iduser);
}
