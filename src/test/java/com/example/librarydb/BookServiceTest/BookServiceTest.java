package com.example.librarydb.BookServiceTest;

import com.example.librarydb.dto.BookDTO;
import com.example.librarydb.model.Book;
import com.example.librarydb.repository.BookRepository;
import com.example.librarydb.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Usar el Simulador de la prueba - Mockito
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    // Simular el repositorio - Persistencia de los datos
    @Mock
    private BookRepository bookRepository;

    // Inyectar, dentro del repositorio, el servicio
    @InjectMocks
    private BookService bookService;

    // Definir variables globales para esta clase
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setup(){
        book1 = new Book();
        book1.setId("B01");
        book1.setTitle("Big Data");
        book1.setAuthor("Alan Twister");
        book1.setIsbn("1234567890001");
        book1.setAvailable(true);
        book2 = new Book("B02","Data Analytics","Elon Musk","3698521470123",true);
        book3 = new Book("B03","AI Times","Mark Zuckerberg","7698521470123",false);
    }
    // Prueba para retornar todos los Books
    @Test
    void shouldReturnAllBooks(){
        // Arrange - Preparar
        // Lista con los datos quemados (hard Code)
        List<Book> books = List.of(book1,book2,book3);
        when(bookRepository.findAll()).thenReturn(books);
        // Act - Ejecutar
        List<BookDTO> result = bookService.getAllBooks();
        // Assert - Verificaciones
        assertNotNull(result);
        //assertNull(result);
        assertEquals("B02",result.get(1).getId());
        assertEquals("Big Data",result.get(0).getTitle());
        assertTrue(result.get(2).getAuthor().equals("Mark Zuckerberg"));
        assertFalse(result.get(2).getAuthor().equals("Mark Twain"));
        // Verificar si se llama el metodo findAll
        verify(bookRepository).findAll();
    }
    // Test para recuperar un Book por Id
    @Test
    void shouldFindBookByIdIfExists(){
        // Arrange
        String id = "B01"; // Buscar este id
        Book book = new Book(
                id,
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                true
        );
        when(bookRepository.findById(id))
                .thenReturn(Optional.of(book1));
        // Act
        BookDTO result = bookService.getBookById(id);

        // Assert
        assertNotNull(result);
        assertEquals("B01", result.getId());
        assertEquals("Big Data", result.getTitle());
        verify(bookRepository).findById(id);
    }
    // =====================================================
    // 3. PRUEBA: getBookById() - LIBRO NO EXISTE
    // =====================================================

    @Test
    void shouldThrowExceptionWhenBookDoesNotExist() {

        // Arrange
        String id = "99";

        when(bookRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> bookService.getBookById(id)
        );
        // Revisar si el mensaje coincide con lo que devuelve la excepcion
        assertEquals(
                "Libro no encontrado con el ID: " + id,
                exception.getMessage()
        );

        verify(bookRepository).findById(id);
    }
    // =====================================================
    // 4. PRUEBA: getBooksByTitle()
    // =====================================================

    @Test
    void shouldReturnBooksByTitle() {

        // Arrange
        String title = "java";

        Book book1 = new Book(
                "1",
                "Effective Java",
                "Joshua Bloch",
                "9780134685991",
                true
        );

        Book book2 = new Book(
                "2",
                "Java Concurrency in Practice",
                "Brian Goetz",
                "9780321349606",
                true
        );

        when(bookRepository.findByTitleContainingIgnoreCase(title))
                .thenReturn(List.of(book1, book2, book3));

        // Act
        List<BookDTO> result = bookService.getBooksByTitle(title);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals("Effective Java", result.get(0).getTitle());
        assertEquals("Java Concurrency in Practice", result.get(1).getTitle());
        assertEquals("AI Times", result.get(2).getTitle());

        verify(bookRepository)
                .findByTitleContainingIgnoreCase(title);
    }






}
