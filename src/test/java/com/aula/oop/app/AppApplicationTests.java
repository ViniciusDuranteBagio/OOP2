package com.aula.oop.app;

import com.aula.oop.app.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

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

		mockMvc.perform(put("/livros/1")
						.contentType(APPLICATION_JSON)
						.content(livroAtualizado))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.titulo").value("Clean Code - Edicao Atualizada"))
				.andExpect(jsonPath("$.codigo").value("CC-002"))
				.andExpect(jsonPath("$.anoPublicacao").value(2009))
				.andExpect(jsonPath("$.preco").value(120.5));

		mockMvc.perform(delete("/livros/1"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/livros"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
}
