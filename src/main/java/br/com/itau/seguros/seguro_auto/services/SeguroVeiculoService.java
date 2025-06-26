package br.com.itau.seguros.seguro_auto.services;

import org.springframework.stereotype.Service;

import br.com.itau.seguros.seguro_auto.dto.CustomerDTO;

public interface SeguroVeiculoService {

    public CustomerDTO calcularSeguroVeiculo(CustomerDTO customerDTO);

}
