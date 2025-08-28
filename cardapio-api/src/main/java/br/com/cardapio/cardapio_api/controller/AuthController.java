package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    // --- NOVO ENDPOINT DE LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequestDTO loginRequest) {
        try {
            // 1. O Controller recebe os dados de login (e-mail e senha).
            // 2. Ele passa os dados para o Service, que contém a lógica de autenticação.
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(loginRequest);

            // 3. Se o Service retornar os dados do utilizador, o login foi um sucesso.
            //    Retornamos uma resposta 200 OK com os dados do utilizador.
            return new ResponseEntity<>(usuarioAutenticado, HttpStatus.OK);

        } catch (RuntimeException e) {
            // 4. Se o Service lançar uma exceção (credenciais inválidas),
            //    o Controller captura o erro e retorna uma resposta 401 Unauthorized.
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
