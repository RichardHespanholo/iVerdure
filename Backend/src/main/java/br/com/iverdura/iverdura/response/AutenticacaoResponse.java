package br.com.iverdura.iverdura.response;

public class AutenticacaoResponse {

    public String nome;
    public Long tipo;
    public Long id;
    public String email;
    public String cpf;

    public AutenticacaoResponse(String cpf,String nome, Long tipo, Long id, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipo = tipo;
        this.id = id;
        this.email = email;
    }

    public AutenticacaoResponse() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
