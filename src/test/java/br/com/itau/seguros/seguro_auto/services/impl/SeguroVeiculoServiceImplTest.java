package br.com.itau.seguros.seguro_auto.services.impl;

import br.com.itau.seguros.seguro_auto.domain.CalculoTaxa;
import br.com.itau.seguros.seguro_auto.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class SeguroVeiculoServiceImplTest {

    private final SeguroVeiculoServiceImpl service = new SeguroVeiculoServiceImpl();

    @Test
    void testCalcularSeguroVeiculoComSucesso() {
        // Arrange
        CustomerDTO input = new CustomerDTO(
                "João",
                null,
                null,
                "SP",
                "50000", // vehicle_value
                null     // insurance_value
        );

        try (MockedStatic<CalculoTaxa> mocked = org.mockito.Mockito.mockStatic(CalculoTaxa.class)) {
            mocked.when(() -> CalculoTaxa.calcularTaxa(new BigDecimal("50000"), "SP"))
                    .thenReturn(new BigDecimal("0.02"));

            // Act
            CustomerDTO result = service.calcularSeguroVeiculo(input);

            // Assert
            assertEquals("1000.00", result.getValue());
            assertEquals("João", result.getName());
            assertEquals("SP", result.getLocation());
        }
    }

    @Test
    void testCalcularSeguroVeiculoComErro() {
        CustomerDTO input = new CustomerDTO(
                "Maria",
                null,
                null,
                "BH",
                "-50000", // vehicle_value
                null
        );

        try (MockedStatic<CalculoTaxa> mocked = org.mockito.Mockito.mockStatic(CalculoTaxa.class)) {
            mocked.when(() -> CalculoTaxa.calcularTaxa(new BigDecimal("-50000"), "BH"))
                    .thenThrow(new RuntimeException("Valor Invalido"));

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.calcularSeguroVeiculo(input);
            });

            assertTrue(exception.getMessage().contains("Valor Invalido"));
        }
    }
}