package br.com.cardapio.cardapio_api.repository;

import java.util.Optional; // Importa a classe Optional

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardapio.cardapio_api.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    
    // O Spring Data JPA vai criar automaticamente a implementação deste método
    // para procurar um restaurante pela coluna "urlSlug".
    Optional<Restaurante> findByUrlSlug(String urlSlug);
}
