package br.com.cardapio.cardapio_api.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "itens_cardapio")
@Data
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column
    private String urlImagem;

    @Column(nullable = false)
    private Boolean ativo; // Corrigido para Boolean

    @Column
    private Integer ordemExibicao;

    @Column(nullable = false)
    private Timestamp dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cardapio_id", nullable = false)
    @JsonBackReference
    private Cardapio cardapio;

    public enum Categoria {
        Pratos_Salgados,
        Sobremesas,
        Bebidas
    }
}

