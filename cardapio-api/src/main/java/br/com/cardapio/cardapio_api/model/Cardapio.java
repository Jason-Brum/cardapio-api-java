package br.com.cardapio.cardapio_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cardapio")
@Data
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Boolean ativo; // ALTERADO DE VOLTA PARA Boolean

    @ManyToOne
    @JoinColumn(name = "restaurantes_id", nullable = false)
    @JsonBackReference
    private Restaurante restaurante;

    @OneToMany(mappedBy = "cardapio")
    @JsonManagedReference
    private List<ItemCardapio> itensCardapio;
}

