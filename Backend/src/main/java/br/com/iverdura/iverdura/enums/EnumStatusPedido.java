package br.com.iverdura.iverdura.enums;

public enum EnumStatusPedido {

    PENDENTE("PENDENTE"),
    ENTREGUE("ENTREGUE");

    private String status;

    EnumStatusPedido(String status){
        this.status = status;
    }

    public static EnumStatusPedido parse(String status) {
        EnumStatusPedido enumStatusPedido = null;
        for (EnumStatusPedido item : EnumStatusPedido.values()) {
            if (item.getStatus().equals(status)) {
                enumStatusPedido = item;
                break;
            }
        }
        return enumStatusPedido;
    }

    public String getStatus() {
        return status;
    }
}
