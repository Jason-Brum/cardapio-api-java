package br.com.cardapio.cardapio_api.dto;

// Esta é uma classe simples (POJO) para representar os dados do pedido de login.
public class LoginRequestDTO {

    private String email;
    private String senha;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
