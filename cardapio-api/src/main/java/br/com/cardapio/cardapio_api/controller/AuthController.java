package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.cardapio_api.dto.LoginRequestDTO;
import br.com.cardapio.cardapio_api.dto.RegisterRequestDTO; // 1. Importa o novo DTO
import br.com.cardapio.cardapio_api.model.Usuario;
import br.com.cardapio.cardapio_api.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // --- 2. MÉTODO ALTERADO PARA USAR O DTO ---
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegisterRequestDTO data) {
        try {
            Usuario novoUsuario = usuarioService.registrarUsuario(data);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Retorna a mensagem de erro específica do serviço
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(loginRequest);
            return new ResponseEntity<>(usuarioAutenticado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}

