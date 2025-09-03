package br.com.cardapio.cardapio_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.model.Restaurante;
import br.com.cardapio.cardapio_api.model.Usuario;
import br.com.cardapio.cardapio_api.repository.RestauranteRepository;
import br.com.cardapio.cardapio_api.repository.UsuarioRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Restaurante criarRestaurante(Restaurante restaurante, Integer usuarioId) {
        // 1. Procura o utilizador dono, findById retorna uma "caixa" (Optional)
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

        // 2. Verifica se a "caixa" está vazia. Se estiver, lança um erro.
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Utilizador não encontrado.");
        }

        // 3. Se a "caixa" não está vazia, podemos pegar o utilizador de dentro dela.
        Usuario usuarioAdmin = usuarioOptional.get();

        // 4. Associa o utilizador encontrado ao novo restaurante.
        restaurante.setUsuario(usuarioAdmin);

        // 5. Salva o novo restaurante no banco de dados
        return restauranteRepository.save(restaurante);
    }

    // --- NOVO MÉTODO ADICIONADO AQUI ---
    public Restaurante buscarPorUsuarioId(Integer usuarioId) {
        // Usa o novo método do repositório para encontrar o restaurante
        // Se não encontrar, retorna null (ou lança um erro, se preferir)
        return restauranteRepository.findByUsuarioId(usuarioId).orElse(null);
    }
}
