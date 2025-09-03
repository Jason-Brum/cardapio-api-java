package br.com.cardapio.cardapio_api.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String nome;
    private String email;
    private String senha; // Recebemos a senha em texto puro
    private String perfil;
}

