package br.com.cardapio.cardapio_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.model.Restaurante;
import br.com.cardapio.cardapio_api.model.Usuario;
import br.com.cardapio.cardapio_api.repository.RestauranteRepository;
import br.com.cardapio.cardapio_api.repository.UsuarioRepository;

import java.sql.Timestamp;
import java.util.Optional; // Importa a classe Optional

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método com a lógica de negócio para criar um novo restaurante.
    public Restaurante criarRestaurante(Restaurante restaurante, Integer usuarioId) {
        // 1. Procura o utilizador "dono". findById retorna uma "caixa" (Optional).
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

        // 2. Verifica se a "caixa" está vazia. Se estiver, lança um erro.
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Utilizador não encontrado.");
        }

        // 3. Se a "caixa" não está vazia, podemos pegar o utilizador de dentro dela.
        Usuario usuarioAdmin = usuarioOptional.get();

        // 4. Associa o utilizador encontrado ao novo restaurante.
        restaurante.setUsuario(usuarioAdmin);

        // 5. Define a data de criação.
        restaurante.setDataCriacao(new Timestamp(System.currentTimeMillis()));

        // 6. Salva o novo restaurante no banco de dados.
        return restauranteRepository.save(restaurante);
    }
}
