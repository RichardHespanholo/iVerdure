package br.com.iverdura.iverdura.enums;

public enum EnumPerfilUsuario {

        IVERDURE(1l),
        CLIENTE(2l),
        FORNECEDOR(3l);

    private Long id;

    EnumPerfilUsuario(Long id) {
        this.id = id;
    }

    public static EnumPerfilUsuario parse(Long id) {
        EnumPerfilUsuario enumPerfilUsuario = null;
        for (EnumPerfilUsuario item : EnumPerfilUsuario.values()) {
            if (item.getId() == id) {
                enumPerfilUsuario = item;
                break;
            }
        }
        return enumPerfilUsuario;
    }

    public Long getId() {
        return id;
    }
}
