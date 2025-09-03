package br.com.cardapio.cardapio_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.cardapio_api.model.Usuario;

@Repository
// A correção está aqui: alteramos o tipo do ID de Long para Integer
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Este método permite-nos encontrar um utilizador pelo seu e-mail.
    // O Spring Data JPA cria a implementação automaticamente.
    Usuario findByEmail(String email);
}
