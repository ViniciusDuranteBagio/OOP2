package com.aula.oop.app;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aula.oop.app.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LivroControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LivroRepository livroRepository;

    @BeforeEach
    void limparBanco() {
        livroRepository.deleteAll();
    }

    @Test
    void deveCadastrarLivro() throws Exception {
        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Clean Code",
                                  "autor": "Robert C. Martin",
                                  "codigo": "LIVRO1",
                                  "anoPublicacao": 2008,
                                  "preco": 89.90
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Clean Code"))
                .andExpect(jsonPath("$.codigo").value("LIVRO1"));
    }

    @Test
    void naoDeveCadastrarCodigoDuplicado() throws Exception {
        String livro = """
                {
                  "titulo": "Livro A",
                  "autor": "Autor A",
                  "codigo": "LIVRO1",
                  "anoPublicacao": 2020,
                  "preco": 50.0
                }
                """;

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livro))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livro))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Codigo duplicado"));
    }

    @Test
    void deveValidarCamposObrigatorios() throws Exception {
        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "",
                                  "autor": "",
                                  "codigo": "",
                                  "anoPublicacao": 1000,
                                  "preco": 0
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Dados invalidos"))
                .andExpect(jsonPath("$.mensagens", hasItem("titulo: O titulo e obrigatorio.")))
                .andExpect(jsonPath("$.mensagens", hasItem("preco: O preco deve ser maior que zero.")));
    }

    @Test
    void deveRetornarErroQuandoLivroNaoExiste() throws Exception {
        mockMvc.perform(get("/livros/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value("Livro nao encontrado"));
    }
}
