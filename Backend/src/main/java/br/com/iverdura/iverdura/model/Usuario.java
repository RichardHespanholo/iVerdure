package br.com.iverdura.iverdura.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class Usuario  {

    @Id
    private String id;

    private Long idUsuario;

    @NotNull
    @Size(max = 100,message = "Tamanho do campo nome inválido.")
    private String nome;

    private String sobrenome;

    private Date dataNascimento;

    @NotNull
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private Long tipo;

    @NotNull
    @Size(max=10, message="Telefone inválido.")
    private String telefone;

    @NotNull
    @Email
    private String email;

    @NotNull
    @CPF
    private String cpf;

    @NotNull
    private Endereco endereco;


    public Usuario() {

    }

    public Usuario(Long idUsuario, String nome, String sobrenome, Date dataNascimento, String usuario, String senha, Long tipo, String telefone, String email, String cpf) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(getUsuario(), usuario1.getUsuario()) &&
                Objects.equals(getSenha(), usuario1.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getSenha());
    }
}

