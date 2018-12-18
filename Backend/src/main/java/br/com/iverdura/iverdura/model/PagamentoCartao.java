package br.com.iverdura.iverdura.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PagamentoCartao {

    @Id
    private String id;

    @NotNull
    private String numeroCartao;

    @NotNull
    private Long tipoPagamento;

    @NotNull
    @Min(message = "Parcela minima de 1.",value = 1)
    @Max(message = "Parcela maxima de 4.", value = 4)
    private int numeroParcelas;

    @NotNull
    private String nomeTutular;

    @NotNull
    private Long codigoSeguranca;

    @NotNull
    private String dateValidade;


    public PagamentoCartao(){


    }

    public PagamentoCartao(String numeroCartao, Long tipoPagamento, int numeroParcelas, String nomeTutular, Long codigoSeguranca, String dateValidade) {
        this.numeroCartao = numeroCartao;
        tipoPagamento = tipoPagamento;
        numeroParcelas = numeroParcelas;
        this.nomeTutular = nomeTutular;
        this.codigoSeguranca = codigoSeguranca;
        this.dateValidade = dateValidade;
    }


    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Long getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Long tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public String getNomeTutular() {
        return nomeTutular;
    }

    public void setNomeTutular(String nomeTutular) {
        this.nomeTutular = nomeTutular;
    }

    public Long getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(Long codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getDateValidade() {
        return dateValidade;
    }

    public void setDateValidade(String dateValidade) {
        this.dateValidade = dateValidade;
    }
}
