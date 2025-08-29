package br.com.cardapio.cardapio_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.model.Restaurante;
import br.com.cardapio.cardapio_api.repository.RestauranteRepository;

@Service
public class PublicService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante getMenuBySlug(String urlSlug) {
        // Usa o novo método do repository para encontrar o restaurante
        Restaurante restaurante = restauranteRepository.findByUrlSlug(urlSlug)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado com o slug: " + urlSlug));
        
        return restaurante;
    }
}
