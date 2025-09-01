package br.com.cardapio.cardapio_api.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.cardapio.cardapio_api.enums.Categoria;
import jakarta.persistence.Column; // Importa tudo de jakarta.persistence
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "itens_cardapio")
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    // --- CORREÇÃO AQUI ---
    // Diz ao banco para guardar o NOME do enum como um texto
    // Isto remove a necessidade do CategoriaConverter.java
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    private String urlImagem;

    @Column(nullable = false)
    private Boolean ativo;

    private Integer ordemExibicao;

    @Column(nullable = false)
    private Timestamp dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cardapio_id", nullable = false)
    @JsonBackReference
    private Cardapio cardapio;
}

