package br.com.cardapio.cardapio_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardapio.cardapio_api.model.Usuario;

// Esta interface herda do JpaRepository.
// A alteração está aqui: mudámos o 'Long' para 'Integer' para corresponder
// ao tipo de ID da nossa classe Usuario.
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Este método continua correto.
    Usuario findByEmail(String email);

}
