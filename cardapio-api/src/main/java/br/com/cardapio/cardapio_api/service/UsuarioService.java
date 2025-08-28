package br.com.cardapio.cardapio_api.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.dto.LoginRequestDTO;
import br.com.cardapio.cardapio_api.model.Usuario;
import br.com.cardapio.cardapio_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método de registo (já existente)
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenhaHash());
        usuario.setSenhaHash(senhaCriptografada);
        usuario.setDataCriacao(new Timestamp(System.currentTimeMillis()));
        usuario.setEmailVerificado(false);

        return usuarioRepository.save(usuario);
    }

    // --- NOVO MÉTODO PARA AUTENTICAR O UTILIZADOR ---
    public Usuario autenticarUsuario(LoginRequestDTO loginRequest) {
        // 1. Busca o utilizador no banco de dados pelo e-mail fornecido.
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        // 2. Verifica se o utilizador foi encontrado E se a senha fornecida
        //    corresponde à senha encriptada no banco de dados.
        if (usuario != null && passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenhaHash())) {
            // Se as credenciais estiverem corretas, retorna os dados do utilizador.
            return usuario;
        }

        // 3. Se o utilizador não for encontrado ou a senha estiver incorreta,
        //    lança uma exceção.
        throw new RuntimeException("Credenciais inválidas.");
    }
}
