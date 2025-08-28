package br.com.cardapio.cardapio_api.model;

import java.sql.Timestamp;
import java.util.List; // Importa a classe List

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private Perfil perfil;

    @Column(name = "data_criacao", nullable = false)
    private Timestamp dataCriacao;

    @Column(name = "email_verificado")
    private Boolean emailVerificado;

    @Column(name = "token_verificacao")
    private String tokenVerificacao;

    @Column(name = "token_reset_senha")
    private String tokenResetSenha;

    @Column(name = "token_reset_expira")
    private Timestamp tokenResetExpira;

    // --- NOVO RELACIONAMENTO COM RESTAURANTE ---
    // @OneToMany indica que Um Usuário pode ter Muitos Restaurantes.
    // "mappedBy = 'usuario'" diz ao Hibernate que a gestão deste relacionamento
    // é feita pelo campo 'usuario' na classe Restaurante.
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Evita loops infinitos ao converter para JSON
    private List<Restaurante> restaurantes;


    public enum Perfil {
        admin,
        editor
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getEmailVerificado() {
        return emailVerificado;
    }

    public void setEmailVerificado(Boolean emailVerificado) {
        this.emailVerificado = emailVerificado;
    }

    public String getTokenVerificacao() {
        return tokenVerificacao;
    }

    public void setTokenVerificacao(String tokenVerificacao) {
        this.tokenVerificacao = tokenVerificacao;
    }

    public String getTokenResetSenha() {
        return tokenResetSenha;
    }

    public void setTokenResetSenha(String tokenResetSenha) {
        this.tokenResetSenha = tokenResetSenha;
    }

    public Timestamp getTokenResetExpira() {
        return tokenResetExpira;
    }

    public void setTokenResetExpira(Timestamp tokenResetExpira) {
        this.tokenResetExpira = tokenResetExpira;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
