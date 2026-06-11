package br.com.mercado.model;

public class Usuario {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String cargo;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // ✅ CORRIGIDO: era Login() sem "get"
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    // ✅ CORRIGIDO: era Senha() sem "get"
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}