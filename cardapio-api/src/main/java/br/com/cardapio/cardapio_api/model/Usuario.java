package br.com.cardapio.cardapio_api.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.cardapio.cardapio_api.enums.Perfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    // --- CORREÇÃO AQUI ---
    // Diz ao banco para guardar o NOME do enum como um texto
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    @Column(name = "data_criacao", nullable = false)
    private Timestamp dataCriacao;

    @Column(name = "email_verificado")
    private Boolean emailVerificado;

    private String tokenVerificacao;
    private String tokenResetSenha;
    private Timestamp tokenResetExpira;

    // Relacionamento com Restaurantes
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Restaurante> restaurantes;
}

