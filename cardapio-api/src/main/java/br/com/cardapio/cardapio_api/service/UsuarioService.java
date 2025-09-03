package br.com.cardapio.cardapio_api.service;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cardapio.cardapio_api.dto.LoginRequestDTO;
import br.com.cardapio.cardapio_api.dto.RegisterRequestDTO; // 1. Importa o novo DTO
import br.com.cardapio.cardapio_api.enums.Perfil;
import br.com.cardapio.cardapio_api.model.Usuario;
import br.com.cardapio.cardapio_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    // --- 2. MÉTODO ALTERADO PARA USAR O DTO ---
    public Usuario registrarUsuario(RegisterRequestDTO data) {
        if (usuarioRepository.findByEmail(data.getEmail()) != null) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }

        // Cria um novo objeto Usuario a partir dos dados do DTO
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(data.getNome());
        novoUsuario.setEmail(data.getEmail());
        
        String senhaCriptografada = passwordEncoder.encode(data.getSenha());
        novoUsuario.setSenhaHash(senhaCriptografada);

        // Converte a String do perfil para o tipo enum
        novoUsuario.setPerfil(Perfil.valueOf(data.getPerfil().toUpperCase()));
        
        // Define os valores padrão
        novoUsuario.setDataCriacao(new Timestamp(System.currentTimeMillis()));
        novoUsuario.setEmailVerificado(false);
        novoUsuario.setTokenVerificacao(UUID.randomUUID().toString());

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        // Envia o e-mail de verificação
        mailService.sendVerificationEmail(usuarioSalvo.getEmail(), usuarioSalvo.getTokenVerificacao());

        return usuarioSalvo;
    }

    public Usuario autenticarUsuario(LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario != null && passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenhaHash())) {
            // Verifica se o e-mail foi verificado
            if (!usuario.getEmailVerificado()) {
                throw new RuntimeException("Por favor, verifique seu e-mail antes de fazer login.");
            }
            return usuario;
        }

        throw new RuntimeException("Credenciais inválidas.");
    }
}

