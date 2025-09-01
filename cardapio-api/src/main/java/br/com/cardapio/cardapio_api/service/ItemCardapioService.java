package br.com.cardapio.cardapio_api.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.model.Cardapio;
import br.com.cardapio.cardapio_api.model.ItemCardapio;
import br.com.cardapio.cardapio_api.repository.CardapioRepository; // Importa a classe Timestamp
import br.com.cardapio.cardapio_api.repository.ItemCardapioRepository;

@Service
public class ItemCardapioService {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    public ItemCardapio criarItemCardapio(Integer cardapioId, ItemCardapio itemData) {
        // 1. Procura o cardápio pelo ID para garantir que ele existe
        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(cardapioId);

        if (cardapioOptional.isEmpty()) {
            throw new RuntimeException("Cardápio não encontrado.");
        }

        Cardapio cardapio = cardapioOptional.get();

        // 2. Prepara os dados do novo item
        ItemCardapio novoItem = new ItemCardapio();
        novoItem.setNome(itemData.getNome());
        novoItem.setDescricao(itemData.getDescricao());
        novoItem.setPreco(itemData.getPreco());
        novoItem.setCategoria(itemData.getCategoria());
        novoItem.setUrlImagem(itemData.getUrlImagem());
        novoItem.setAtivo(true); // Por defeito, um novo item é ativo
        
        // --- CORREÇÃO AQUI ---
        // Define a data de criação atual antes de salvar
        novoItem.setDataCriacao(new Timestamp(System.currentTimeMillis()));

        novoItem.setCardapio(cardapio); // Associa o item ao cardápio encontrado

        // 3. Salva o novo item no banco de dados
        return itemCardapioRepository.save(novoItem);
    }
}

