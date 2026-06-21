package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service -> marca esta classe como um "componente de servico" do Spring.
// E aqui que fica a LOGICA DE NEGOCIO da aplicacao: validar regras, decidir
// o que fazer em cada situacao, e orquestrar as chamadas ao Repository.
// O Controller NUNCA deve falar direto com o Repository -> ele sempre passa pelo Service.
@Service
@RequiredArgsConstructor
// @RequiredArgsConstructor (Lombok) -> gera automaticamente um construtor
// recebendo todos os campos "final" da classe (no caso, o livroRepository).
// Isso e o que chamamos de "injecao de dependencia via construtor": o Spring
// ve que o LivroService precisa de um LivroRepository, e injeta automaticamente
// a instancia correta quando cria o LivroService.
public class LivroService {

    private final LivroRepository livroRepository;

    // CADASTRAR um novo livro (POST /livros)
    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {

        // REGRA DE NEGOCIO: "Não deve ser possível cadastrar dois livros com o mesmo código"
        if (livroRepository.existsByCodigo(dto.getCodigo())) {
            // Lancamos uma exececao customizada. Ela sera capturada pelo
            // GlobalExceptionHandler, que converte isso numa resposta HTTP 409
            // com uma mensagem amigavel, sem nunca quebrar a aplicacao.
            throw new BusinessException(
                    "Ja existe um livro cadastrado com o codigo '" + dto.getCodigo() + "'"
            );
        }

        // Convertendo o DTO de entrada (o que o cliente enviou) para a Entity
        // (o que sera de fato salvo no banco)
        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .codigo(dto.getCodigo())
                .anoPublicacao(dto.getAnoPublicacao())
                .preco(dto.getPreco())
                .build();

        // .save() insere o registro no banco e retorna o objeto JA COM O ID gerado
        Livro livroSalvo = livroRepository.save(livro);

        // Convertendo a Entity salva para o DTO de resposta (o que sera devolvido ao cliente)
        return toResponseDTO(livroSalvo);
    }

    // LISTAR todos os livros (GET /livros)
    public List<LivroResponseDTO> listarTodos() {

        // findAll() retorna List<Livro> (entidades). Precisamos converter
        // CADA elemento dessa lista para LivroResponseDTO antes de devolver,
        // pois a Entity nunca deve "escapar" para fora do Service.
        return livroRepository.findAll()
                .stream()
                .map(this::toResponseDTO) // aplica a conversao em cada item da lista
                .toList();
    }

    // BUSCAR um livro por ID (GET /livros/{id})
    public LivroResponseDTO buscarPorId(Long id) {

        // REGRA DE NEGOCIO: "Ao buscar por ID, deve ser retornado um erro
        // caso o livro não seja encontrado"
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livro com id " + id + " nao foi encontrado"
                ));
        // .orElseThrow(...) -> se o Optional retornado pelo findById estiver
        // vazio (livro nao existe), executa a funcao lambda, que lanca a exececao.
        // Se existir, simplesmente "desembrulha" o Optional e devolve o Livro.

        return toResponseDTO(livro);
    }

    // ATUALIZAR um livro existente (PUT /livros/{id})
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

        // REGRA DE NEGOCIO: "Ao atualizar, deve ser verificado se o livro
        // existe antes de realizar a alteração"
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livro com id " + id + " nao foi encontrado"
                ));

        // REGRA DE NEGOCIO (cuidado extra): se o usuario esta tentando mudar
        // o "codigo" do livro para um codigo que JA PERTENCE A OUTRO LIVRO,
        // isso tambem deve ser bloqueado. Mas se o codigo enviado e o MESMO
        // que o livro ja tinha, nao ha problema (ele nao esta duplicando, so mantendo).
        boolean codigoMudou = !livroExistente.getCodigo().equals(dto.getCodigo());
        if (codigoMudou && livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException(
                    "Ja existe um livro cadastrado com o codigo '" + dto.getCodigo() + "'"
            );
        }

        // Atualiza os campos do objeto que ja existe no banco (gerenciado pelo JPA)
        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAutor(dto.getAutor());
        livroExistente.setCodigo(dto.getCodigo());
        livroExistente.setAnoPublicacao(dto.getAnoPublicacao());
        livroExistente.setPreco(dto.getPreco());

        // save() aqui funciona como "UPDATE", pois o objeto ja possui um ID existente
        Livro livroAtualizado = livroRepository.save(livroExistente);

        return toResponseDTO(livroAtualizado);
    }

    // REMOVER um livro (DELETE /livros/{id})
    public void remover(Long id) {

        // Tambem verificamos a existencia antes de deletar, para podermos
        // devolver uma mensagem amigavel de "nao encontrado" em vez de um erro tecnico
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Livro com id " + id + " nao foi encontrado"
            );
        }

        livroRepository.deleteById(id);
    }

    // Metodo auxiliar PRIVADO: converte Entity -> Response DTO
    // Centralizar essa conversao em um unico metodo evita repetir o mesmo
    // codigo em cadastrar(), listarTodos(), buscarPorId() e atualizar().
    private LivroResponseDTO toResponseDTO(Livro livro) {
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .codigo(livro.getCodigo())
                .anoPublicacao(livro.getAnoPublicacao())
                .preco(livro.getPreco())
                .build();
    }

}
