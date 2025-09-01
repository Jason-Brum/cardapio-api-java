package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.cardapio_api.model.Cardapio;
import br.com.cardapio.cardapio_api.service.CardapioService;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/cardapios")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    @PostMapping
    public ResponseEntity<?> criarCardapio(@PathVariable Integer restauranteId, @RequestBody Cardapio cardapioData) {
        try {
            Cardapio novoCardapio = cardapioService.criarCardapio(restauranteId, cardapioData);
            return new ResponseEntity<>(novoCardapio, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
