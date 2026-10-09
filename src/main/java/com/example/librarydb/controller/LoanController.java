package com.example.librarydb.controller;

import com.example.librarydb.dto.LoanDTO;
import com.example.librarydb.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // ==========================================================
    // GET /api/loans
    // Obtener todos los préstamos
    // ==========================================================

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {

        return ResponseEntity.ok(
                loanService.getAllLoans()
        );
    }

    // ==========================================================
    // GET /api/loans/book/{bookId}
    // Obtener préstamos de un libro
    // ==========================================================

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookId(
            @PathVariable String bookId) {

        return ResponseEntity.ok(
                loanService.getLoansByBookId(bookId)
        );
    }

    // ==========================================================
    // GET /api/loans/user/{iduser}
    // Obtener préstamos de un usuario
    // ==========================================================

    @GetMapping("/user/{iduser}")
    public ResponseEntity<List<LoanDTO>> getLoansByIduser(
            @PathVariable String iduser) {

        return ResponseEntity.ok(
                loanService.getLoansByIduser(iduser)
        );
    }

    // ==========================================================
    // POST /api/loans
    // Crear préstamo
    // ==========================================================

    @PostMapping
    public ResponseEntity<?> createLoan(
            @Valid @RequestBody LoanDTO dto) {

        try {

            LoanDTO createdLoan =
                    loanService.createLoan(dto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdLoan);

        } catch (RuntimeException ex) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "message",
                            ex.getMessage()
                    ));
        }
    }

    // ==========================================================
    // DELETE /api/loans/{id}
    // Registrar devolución
    // ==========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnLoan(
            @PathVariable String id) {

        loanService.returnLoan(id);

        return ResponseEntity.noContent().build();
    }
}