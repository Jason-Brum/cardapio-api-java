package br.com.cardapio.cardapio_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardapio.cardapio_api.model.ItemCardapio;

// Esta interface estende JpaRepository, dando-nos acesso a todas as operações CRUD básicas
// para a entidade ItemCardapio, que tem um ID do tipo Integer.
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Integer> {
    
}
