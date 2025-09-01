package br.com.cardapio.cardapio_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.model.Cardapio;
import br.com.cardapio.cardapio_api.model.Restaurante;
import br.com.cardapio.cardapio_api.repository.CardapioRepository;
import br.com.cardapio.cardapio_api.repository.RestauranteRepository;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Cardapio criarCardapio(Integer restauranteId, Cardapio cardapioData) {
        // 1. Procura o restaurante pelo ID para garantir que ele existe
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restauranteId);

        if (restauranteOptional.isEmpty()) {
            throw new RuntimeException("Restaurante não encontrado.");
        }

        Restaurante restaurante = restauranteOptional.get();

        // 2. Prepara os dados do novo cardápio
        Cardapio novoCardapio = new Cardapio();
        novoCardapio.setTitulo(cardapioData.getTitulo());
        novoCardapio.setAtivo(true); // Por defeito, um novo cardápio é ativo
        novoCardapio.setRestaurante(restaurante); // Associa o restaurante encontrado

        // 3. Salva o novo cardápio no banco de dados
        return cardapioRepository.save(novoCardapio);
    }
}

