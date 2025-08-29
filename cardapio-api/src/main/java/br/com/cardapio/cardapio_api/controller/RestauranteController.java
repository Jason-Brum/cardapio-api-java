package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Endpoint para criar um novo restaurante
    @PostMapping
    public ResponseEntity<?> criarRestaurante(
            @RequestBody Restaurante restaurante,
            @RequestHeader("X-User-Id") Integer usuarioId) {
        try {
            Restaurante novoRestaurante = restauranteService.criarRestaurante(restaurante, usuarioId);
            return new ResponseEntity<>(novoRestaurante, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
