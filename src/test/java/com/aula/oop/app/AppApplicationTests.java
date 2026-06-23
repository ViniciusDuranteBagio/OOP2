package com.aula.oop.app;

import com.aula.oop.app.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LivroRepository livroRepository;

	@BeforeEach
	void limparBanco() {
		livroRepository.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void deveTestarPostGetPutDeleteDeLivros() throws Exception {
		String livro = """
				{
				  "titulo": "Clean Code",
				  "autor": "Robert C. Martin",
				  "codigo": "CC-001",
				  "anoPublicacao": 2008,
				  "preco": 99.90
				}
				""";

		mockMvc.perform(post("/livros")
						.contentType(APPLICATION_JSON)
						.content(livro))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.titulo").value("Clean Code"))
				.andExpect(jsonPath("$.autor").value("Robert C. Martin"))
				.andExpect(jsonPath("$.codigo").value("CC-001"))
				.andExpect(jsonPath("$.anoPublicacao").value(2008))
				.andExpect(jsonPath("$.preco").value(99.9));

		mockMvc.perform(get("/livros"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].titulo").value("Clean Code"))
				.andExpect(jsonPath("$[0].codigo").value("CC-001"));

		String livroAtualizado = """
				{
				  "titulo": "Clean Code - Edicao Atualizada",
				  "autor": "Robert C. Martin",
				  "codigo": "CC-002",
				  "anoPublicacao": 2009,
				  "preco": 120.50
				}
				""";

		Long livroId = livroRepository.findByCodigo("CC-001").orElseThrow().getId();

		mockMvc.perform(put("/livros/" + livroId)
						.contentType(APPLICATION_JSON)
						.content(livroAtualizado))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(livroId))
				.andExpect(jsonPath("$.titulo").value("Clean Code - Edicao Atualizada"))
				.andExpect(jsonPath("$.codigo").value("CC-002"))
				.andExpect(jsonPath("$.anoPublicacao").value(2009))
				.andExpect(jsonPath("$.preco").value(120.5));

		mockMvc.perform(delete("/livros/" + livroId))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/livros"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	void deveRetornarConflitoAoCadastrarLivroComCodigoDuplicado() throws Exception {
		String livro = """
				{
				  "titulo": "Clean Code",
				  "autor": "Robert C. Martin",
				  "codigo": "CC-001",
				  "anoPublicacao": 2008,
				  "preco": 99.90
				}
				""";

		mockMvc.perform(post("/livros")
						.contentType(APPLICATION_JSON)
						.content(livro))
				.andExpect(status().isCreated());

		mockMvc.perform(post("/livros")
						.contentType(APPLICATION_JSON)
						.content(livro))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.error").value("Regra de negocio violada"))
				.andExpect(jsonPath("$.message", containsString("codigo 'CC-001'")));
	}

	@Test
	void deveRetornarBadRequestQuandoDadosForemInvalidos() throws Exception {
		String livroInvalido = """
				{
				  "titulo": "",
				  "codigo": "",
				  "anoPublicacao": 1000,
				  "preco": 0
				}
				""";

		mockMvc.perform(post("/livros")
						.contentType(APPLICATION_JSON)
						.content(livroInvalido))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Erro de validacao"))
				.andExpect(jsonPath("$.details", hasSize(5)));
	}

	@Test
	void deveRetornarNotFoundAoBuscarLivroInexistente() throws Exception {
		mockMvc.perform(get("/livros/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Recurso nao encontrado"))
				.andExpect(jsonPath("$.message").value("Livro com id 999 nao foi encontrado."));
	}
}
