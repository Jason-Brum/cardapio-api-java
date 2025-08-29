package br.com.cardapio.cardapio_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardapio.cardapio_api.model.Cardapio;

// Esta interface estende JpaRepository, dando-nos acesso a todas as operações CRUD básicas
// para a entidade Cardapio, que tem um ID do tipo Integer.
public interface CardapioRepository extends JpaRepository<Cardapio, Integer> {
    
}
