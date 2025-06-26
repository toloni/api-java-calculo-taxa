package br.com.itau.seguros.seguro_auto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.seguros.seguro_auto.dto.CustomerWrapperDTO;
import br.com.itau.seguros.seguro_auto.services.SeguroVeiculoService;

@RestController
@RequestMapping("/api/v1/calculos-seguro-auto")
public class CalculoSeguroController {

  @Autowired
  private SeguroVeiculoService seguroVeiculoService;

  @PostMapping
  public CustomerWrapperDTO calcularSeguroVeiculo(@RequestBody CustomerWrapperDTO body) {
    return new CustomerWrapperDTO(seguroVeiculoService.calcularSeguroVeiculo(body.getCustomer()));
  }

}
