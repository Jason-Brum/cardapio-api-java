package br.com.cardapio.cardapio_api.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_estabelecimento", nullable = false)
    private String nomeEstabelecimento;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private String endereco;
    private String whatsapp;

    @Column(name = "tema_cardapio", nullable = false)
    private String temaCardapio;

    @Column(name = "url_slug", nullable = false, unique = true)
    private String urlSlug;

    @Column(name = "data_criacao", nullable = false)
    private Timestamp dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id_admin", nullable = false)
    // 2. ADICIONE ESTA ANOTAÇÃO AQUI
    @JsonBackReference 
    private Usuario usuario;

    // Getters e Setters (não precisam de alteração)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTemaCardapio() {
        return temaCardapio;
    }

    public void setTemaCardapio(String temaCardapio) {
        this.temaCardapio = temaCardapio;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
