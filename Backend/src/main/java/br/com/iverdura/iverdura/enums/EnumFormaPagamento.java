package br.com.iverdura.iverdura.enums;

public enum EnumFormaPagamento {

    CARTAO(1l);

    private Long id;

    EnumFormaPagamento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static EnumFormaPagamento parse(Long id) {
        EnumFormaPagamento enumFormaPagamento = null;
        for (EnumFormaPagamento item : EnumFormaPagamento.values()) {
            if (item.getId() == id) {
                enumFormaPagamento = item;
                break;
            }
        }
        return enumFormaPagamento;
    }

}


