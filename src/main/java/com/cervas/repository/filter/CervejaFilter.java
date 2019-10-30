package com.cervas.repository.filter;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import com.cervas.model.Estilo;
import com.cervas.model.Origem;
import com.cervas.model.Sabor;

public class CervejaFilter {

    private String sku;
    private String nome;
    private Estilo estilo;
    private Sabor sabor;
    private Origem origem;
    @NumberFormat(pattern="#,##0")
    private BigDecimal valorDe;
    @NumberFormat(pattern="#,##0")
    private BigDecimal valorAte;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public BigDecimal getValorDe() {
        return valorDe;
    }

    public void setValorDe(BigDecimal valorDe) {
        this.valorDe = valorDe;
    }

    public BigDecimal getValorAte() {
        return valorAte;
    }

    public void setValorAte(BigDecimal valorAte) {
        this.valorAte = valorAte;
    }

	@Override
	public String toString() {
		return "CervejaFilter [sku=" + sku + ", valorDe=" + valorDe + ", valorAte=" + valorAte + "]";
	}
    

    
}