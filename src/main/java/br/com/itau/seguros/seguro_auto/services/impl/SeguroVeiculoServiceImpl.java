package br.com.itau.seguros.seguro_auto.services.impl;

import br.com.itau.seguros.seguro_auto.dto.CustomerDTO;
import br.com.itau.seguros.seguro_auto.services.SeguroVeiculoService;
import br.com.itau.seguros.seguro_auto.domain.CalculoTaxa;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SeguroVeiculoServiceImpl implements SeguroVeiculoService {

    private static final String ERRO_CALCULO_TAXA = "Não foi possível calcular a taxa para o veículo.";

    @Override
    public CustomerDTO calcularSeguroVeiculo(CustomerDTO customer) {

        var valorSeguro = BigDecimal.ZERO;

        BigDecimal valorVeiculo = new BigDecimal(customer.getVehicle_value());
        String cidade = customer.getLocation();

        try {
            var taxa = CalculoTaxa.calcularTaxa(valorVeiculo, cidade);
            valorSeguro = taxa.multiply(valorVeiculo);

        } catch (Exception e) {
            throw new RuntimeException(ERRO_CALCULO_TAXA + e.getMessage());
        }

        // retornar mensagem api valor seguro invalido

        if (valorSeguro.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        return new CustomerDTO(
                customer.getName(),
                null,
                null,
                customer.getLocation(),
                null,
                valorSeguro.toString());
    }
}
