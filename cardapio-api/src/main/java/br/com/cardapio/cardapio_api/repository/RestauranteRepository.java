package br.com.cardapio.cardapio_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.cardapio_api.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    // Método para encontrar um restaurante pelo ID do seu dono (já existente)
    Optional<Restaurante> findByUsuarioId(Integer usuarioId);
    
    // --- NOVO MÉTODO ADICIONADO AQUI ---
    // Método que permite ao Spring criar automaticamente uma busca
    // na tabela 'restaurantes' pela coluna 'url_slug'.
    Optional<Restaurante> findByUrlSlug(String urlSlug);
}

