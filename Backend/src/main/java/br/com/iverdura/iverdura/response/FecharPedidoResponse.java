package br.com.iverdura.iverdura.response;

import java.util.List;

public class FecharPedidoResponse {

    private String msg;
    private List<EstoqueResponse> produtosIndisponiveis;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<EstoqueResponse> getProdutosIndisponiveis() {
        return produtosIndisponiveis;
    }

    public void setProdutosIndisponiveis(List<EstoqueResponse> produtosIndisponiveis) {
        this.produtosIndisponiveis = produtosIndisponiveis;
    }
}
