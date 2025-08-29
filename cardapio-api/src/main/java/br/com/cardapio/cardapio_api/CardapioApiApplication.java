package br.com.cardapio.cardapio_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // 1. Importa a anotação

// @SpringBootApplication já faz muita coisa, mas vamos ser explícitos.
@SpringBootApplication
// 2. Adiciona a anotação @ComponentScan, dizendo ao Spring para procurar
//    componentes no pacote "br.com.cardapio.cardapio_api" e em todos os seus sub-pacotes.
@ComponentScan(basePackages = "br.com.cardapio.cardapio_api")
public class CardapioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardapioApiApplication.class, args);
	}

}
