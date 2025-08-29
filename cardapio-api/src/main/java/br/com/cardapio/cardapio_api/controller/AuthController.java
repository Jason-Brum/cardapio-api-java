package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping; // Importa o GetMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.cardapio_api.dto.LoginRequestDTO;
import br.com.cardapio.cardapio_api.model.Usuario;
import br.com.cardapio.cardapio_api.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;


    // Endpoint de registo (já existente)
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint de login (já existente)
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
