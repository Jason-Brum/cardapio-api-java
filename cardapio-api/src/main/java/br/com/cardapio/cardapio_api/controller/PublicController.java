package br.com.cardapio.cardapio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.cardapio_api.model.Restaurante;
import br.com.cardapio.cardapio_api.service.PublicService;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private PublicService publicService;

    // Endpoint para buscar o menu de um restaurante pelo seu link (slug)
    @GetMapping("/menu/{urlSlug}")
    public ResponseEntity<?> getMenuBySlug(@PathVariable String urlSlug) {
        try {
            Restaurante restaurante = publicService.getMenuBySlug(urlSlug);
            return ResponseEntity.ok(restaurante);
        } catch (RuntimeException e) {
            // Se o serviço lançar um erro (não encontrou), retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
