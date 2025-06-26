package br.com.itau.seguros.seguro_auto.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiPredicate;

public class CalculoTaxa {

    private record Regra(BiPredicate<BigDecimal, String> condicao, BigDecimal taxa) {
    }

    private static final BigDecimal SETENTA_MIL = new BigDecimal("70000");
    private static final BigDecimal CEM_MIL = new BigDecimal("100000");

    private static final List<Regra> regras = List.of(
            new Regra((valor, cidade) -> valor.compareTo(SETENTA_MIL) >= 0 && "SP".equalsIgnoreCase(cidade), new BigDecimal("0.05")),
            new Regra((valor, cidade) -> valor.compareTo(SETENTA_MIL) >= 0, new BigDecimal("0.04")),
            new Regra((valor, cidade) -> valor.compareTo(SETENTA_MIL) >= 0 && valor.compareTo(CEM_MIL) < 0, new BigDecimal("0.055")),
            new Regra((valor, cidade) -> valor.compareTo(CEM_MIL) >= 0, new BigDecimal("0.06"))
    );

    public static BigDecimal calcularTaxa(BigDecimal valorVeiculo, String cidade) {
        return regras.stream()
                .filter(regra -> regra.condicao.test(valorVeiculo, cidade))
                .map(Regra::taxa)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }
}
