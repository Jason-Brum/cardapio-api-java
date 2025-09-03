package br.com.cardapio.cardapio_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "Confirme seu Cadastro - Cardápio Online";
        // O link que será enviado no e-mail, apontando para o nosso front-end
        String confirmationUrl = "http://localhost:5173/verify-email/" + token;
        String message = "Obrigado por se cadastrar! Por favor, clique no link abaixo para ativar sua conta:\n\n" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setText(message);
        // O e-mail de remetente será pego automaticamente do application.properties
        // email.setFrom("seu-email@seudominio.com"); 

        mailSender.send(email);
        System.out.println("E-mail de verificação enviado para " + toEmail);
    }
}
