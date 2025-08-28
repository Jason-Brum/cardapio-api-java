package br.com.cardapio.cardapio_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardapio.cardapio_api.model.Restaurante;

// Esta interface herda do JpaRepository, especificando que vai gerir
// a entidade 'Restaurante' e que a sua chave primária é do tipo 'Integer'.
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    // Por enquanto, não precisamos de métodos customizados aqui.
    // O JpaRepository já nos dá tudo o que é necessário para começar.

}
