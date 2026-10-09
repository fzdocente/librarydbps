package com.example.librarydb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraBasicaTest {
    private CalculadoraBasica calculadoraBasica;
    @BeforeEach
    void setup(){
        // Arrange
        calculadoraBasica = new CalculadoraBasica();

    } 

    @Test
    void sumar2NumerosPositivos(){
        // Arrange - Preparar
        //CalculadoraBasica calculadoraBasica = new CalculadoraBasica();
        // Act - Actuar
        Double myResult = calculadoraBasica.sumar(2.0, 3.0);
        // Assert - Verificar
        assertEquals(5.0,myResult);
    }
    @Test 
    public void sumar2NumerosUnoPositivoOtroNegativo(){
        // Arrange
        //CalculadoraBasica calculadoraBasica = new CalculadoraBasica();
        // Act - Assert
        Double valor1 = 9.0;
        Double valor2 = -4.5;
        Double valorEsperado = 4.5;
        assertEquals(valorEsperado,calculadoraBasica.sumar(valor1, valor2),"Debe ser uno positivo y otro negativo");
    }
    @Test
    public void sumar2NumerosNegativos(){
        // Arrange
        //CalculadoraBasica calculadoraBasica = new CalculadoraBasica();
        // Act - Assert
        Double valor1 = -7.5;
        Double valor2 = -2.5;
        Double valorEsperado = -10.0;
        assertEquals(valorEsperado,calculadoraBasica.sumar(valor1, valor2));
    }
    @Test 
    void sumar2NumerosConCondicional(){
        // Act
        Double result = calculadoraBasica.sumar(50.0,45.0);
        // Assert
        assertTrue(result > 12);
    }
    @Test 
    void sumar2NumerosConNull(){
        // Act
        Double result = calculadoraBasica.sumar(null, 8.0);
        // Assert
        assertNull(result);
    }

    @Test 
    void dividir2NumerosConDenonimadorNullOCero(){
        // Assert
        assertThrows(IllegalArgumentException.class, ()->{
            calculadoraBasica.dividir(5.0,0.0);
        });

    }
}
