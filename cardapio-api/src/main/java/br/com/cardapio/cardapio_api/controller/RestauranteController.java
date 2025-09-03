package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.cardapio_api.model.Restaurante;
import br.com.cardapio.cardapio_api.service.RestauranteService;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    // Endpoint para criar um novo restaurante (já existente)
    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(
            @RequestBody Restaurante restaurante,
            @RequestHeader("X-User-Id") Integer usuarioId) {
        try {
            Restaurante novoRestaurante = restauranteService.criarRestaurante(restaurante, usuarioId);
            return new ResponseEntity<>(novoRestaurante, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // --- NOVO ENDPOINT ADICIONADO AQUI ---
    @GetMapping("/meu-restaurante")
    public ResponseEntity<Restaurante> buscarMeuRestaurante(@RequestHeader("X-User-Id") Integer usuarioId) {
        Restaurante restaurante = restauranteService.buscarPorUsuarioId(usuarioId);
        if (restaurante != null) {
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        } else {
            // Retorna 200 OK com corpo nulo se o utilizador não tiver restaurante,
            // para o front-end saber que precisa de mostrar a tela de criação.
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
