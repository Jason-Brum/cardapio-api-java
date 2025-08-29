package br.com.cardapio.cardapio_api.dto;

import lombok.Data;

// Esta é uma classe simples (POJO) para representar os dados do pedido de login.
@Data
public class LoginRequestDTO {

    private String email;
    private String senha;
    
}
