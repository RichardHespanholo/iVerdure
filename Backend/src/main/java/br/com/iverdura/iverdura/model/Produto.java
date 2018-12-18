package br.com.iverdura.iverdura.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class Produto {

    @Id
    private String id;

    private Long idProduto;

    @NotNull
    private String nome;

    @NotNull
    private Long qtd;

    @NotNull
    private String categoria;

    @NotNull
    @PositiveOrZero
    private Double preco;

    @NotNull
    @PositiveOrZero
    private Double desconto;

    @NotNull
    @PositiveOrZero
    private Long qualidade;

    @NotNull
    private Long idFornecedor;

    @NotNull
    private String nomeFantasia;

    private String descricao;

    @NotNull
    private String urlImg;

    public Produto(){


    }


    public Produto(Long idProduto,String nome, Long qtd, String categoria, Double preco, Double desconto,Long qualidade,String urlImg,Long idFornecedor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.qtd = qtd;
        this.categoria = categoria;
        this.preco = preco;
        this.desconto = desconto;
        this.qualidade = qualidade;
        this.urlImg = urlImg;
        this.idFornecedor = idFornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getQualidade() {
        return qualidade;
    }

    public void setQualidade(Long qualidade) {
        this.qualidade = qualidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQtd() {
        return qtd;
    }

    public void setQtd(Long qtd) {
        this.qtd = qtd;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}

