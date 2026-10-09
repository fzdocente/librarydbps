package com.example.librarydb.service;

import com.example.librarydb.dto.LoanDTO;
import com.example.librarydb.model.Book;
import com.example.librarydb.model.Loan;
import com.example.librarydb.model.User;
import com.example.librarydb.repository.BookRepository;
import com.example.librarydb.repository.LoanRepository;
import com.example.librarydb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(
            LoanRepository loanRepository,
            BookRepository bookRepository,
            UserRepository userRepository) {

        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // ==========================================================
    // OBTENER TODOS LOS PRÉSTAMOS
    // ==========================================================

    public List<LoanDTO> getAllLoans() {

        return loanRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================================
    // OBTENER PRÉSTAMOS POR BOOK ID
    // ==========================================================

    public List<LoanDTO> getLoansByBookId(String bookId) {

        return loanRepository.findByBookId(bookId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================================
    // OBTENER PRÉSTAMOS POR USER ID
    // ==========================================================

    public List<LoanDTO> getLoansByIduser(String iduser) {

        return loanRepository.findByIduser(iduser)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================================
    // CREAR PRÉSTAMO
    // ==========================================================

    public LoanDTO createLoan(LoanDTO dto) {

        // ------------------------------------------------------
        // 1. Validar que exista el libro
        // ------------------------------------------------------

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe ningún libro con el ID proporcionado: "
                                        + dto.getBookId()
                        )
                );

        // ------------------------------------------------------
        // 2. Validar que exista el usuario
        // ------------------------------------------------------

        User user = userRepository.findById(dto.getIduser())
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe ningún usuario con el ID proporcionado: "
                                        + dto.getIduser()
                        )
                );

        // ------------------------------------------------------
        // 3. Validar que el usuario NO esté sancionado
        // ------------------------------------------------------

        if (user.isSanctioned()) {

            throw new RuntimeException(
                    "El usuario '" + user.getFullname()
                            + "' está sancionado y no puede realizar préstamos"
            );
        }

        // ------------------------------------------------------
        // 4. Validar disponibilidad del libro
        // ------------------------------------------------------

        if (!book.isAvailable()) {

            throw new RuntimeException(
                    "El libro '" + book.getTitle()
                            + "' no está disponible para préstamo en este momento"
            );
        }

        // ------------------------------------------------------
        // 5. Cambiar disponibilidad del libro
        // ------------------------------------------------------

        book.setAvailable(false);

        bookRepository.save(book);

        // ------------------------------------------------------
        // 6. Crear el préstamo
        // ------------------------------------------------------

        Loan loan = convertToEntity(dto);

        Loan savedLoan = loanRepository.save(loan);

        // ------------------------------------------------------
        // 7. Retornar DTO
        // ------------------------------------------------------

        return convertToDTO(savedLoan);
    }

    // ==========================================================
    // DEVOLVER LIBRO
    // ==========================================================

    public void returnLoan(String loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Préstamo no encontrado con el ID: "
                                        + loanId
                        )
                );

        // Buscar el libro y volverlo disponible
        bookRepository.findById(loan.getBookId())
                .ifPresent(book -> {

                    book.setAvailable(true);

                    bookRepository.save(book);
                });

        // Eliminar el préstamo
        loanRepository.deleteById(loanId);
    }

    // ==========================================================
    // CONVERTIR ENTITY → DTO
    // ==========================================================

    private LoanDTO convertToDTO(Loan loan) {

        return new LoanDTO(
                loan.getId(),
                loan.getBookId(),
                loan.getIduser(),
                loan.getLoanDate(),
                loan.getReturnDate()
        );
    }

    // ==========================================================
    // CONVERTIR DTO → ENTITY
    // ==========================================================

    private Loan convertToEntity(LoanDTO dto) {

        return new Loan(
                dto.getId(),
                dto.getBookId(),
                dto.getIduser(),
                dto.getLoanDate(),
                dto.getReturnDate()
        );
    }
}
