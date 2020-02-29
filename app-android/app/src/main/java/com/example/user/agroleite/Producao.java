package com.example.user.agroleite;

import java.util.Date;

/**
 * Created by Seduc on 04/09/2017.
 */

public class Producao {

    private String dataOrdenha;

    private String quantidadeLitros;
    private String valorTotalProDiaria;

    public String getDataOrdenha() {
        return dataOrdenha;
    }

    public void setDataOrdenha(String dataOrdenha) {
        this.dataOrdenha = dataOrdenha;
    }

    public String getQuantidadeLitros() {
        return quantidadeLitros;
    }

    public void setQuantidadeLitros(String quantidadeLitros) {
        this.quantidadeLitros = quantidadeLitros;
    }

    public String getValorTotalProDiaria() {
        return valorTotalProDiaria;
    }

    public void setValorTotalProDiaria(String valorTotalProDiaria) {
        this.valorTotalProDiaria = valorTotalProDiaria;
    }


    @Override
    public String toString() {
        return "Data da Ordenha " + dataOrdenha  +
                " quantidade de Litros      " + quantidadeLitros  +
                "    valor Total  R$ " + valorTotalProDiaria
                ;
    }
}
