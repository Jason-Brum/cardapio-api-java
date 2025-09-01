package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.cardapio_api.model.ItemCardapio;
import br.com.cardapio.cardapio_api.service.ItemCardapioService;

@RestController
@RequestMapping("/api/cardapios/{cardapioId}/items")
public class ItemCardapioController {

    @Autowired
    private ItemCardapioService itemCardapioService;

    @PostMapping
    public ResponseEntity<?> criarItemCardapio(@PathVariable Integer cardapioId, @RequestBody ItemCardapio itemData) {
        try {
            ItemCardapio novoItem = itemCardapioService.criarItemCardapio(cardapioId, itemData);
            return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
