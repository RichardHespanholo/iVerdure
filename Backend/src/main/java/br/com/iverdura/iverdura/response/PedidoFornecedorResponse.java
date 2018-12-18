package br.com.iverdura.iverdura.response;

public class PedidoFornecedorResponse {

    private Double pedidoMinimo;
    private Double totalPedido;
    private Long idFornecedor;
    private String nomeFantasia;


    public Double getPedidoMinimo() {
        return pedidoMinimo;
    }

    public void setPedidoMinimo(Double pedidoMinimo) {
        this.pedidoMinimo = pedidoMinimo;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
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
