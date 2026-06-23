# Orientação a Objetos 2 — Projeto de estudo (5ª fase)

Repositório da disciplina **Orientação a Objetos 2** (5ª fase), usado para prática em sala de aula e estudos ao longo do semestre.

A aplicação é um projeto **Spring Boot** com **Maven**, **Java 21**, JPA, H2 e API REST. O código base serve de ponto de partida; cada aluno desenvolve nas **próprias atividades** em uma **branch separada**.

> **Importante:** a branch `main` é protegida e **não deve receber commits diretos**. Trabalhe sempre na sua branch pessoal.

---

## Pré-requisitos

Antes de começar, instale:

| Ferramenta | Versão sugerida |
|------------|-----------------|
| [Git](https://git-scm.com/) | Qualquer versão recente |
| [Java JDK](https://adoptium.net/) | **21** |
| IDE (opcional) | IntelliJ IDEA, VS Code/Cursor ou Eclipse |

Para conferir no terminal:

```bash
git --version
java -version
```

A saída do `java -version` deve indicar **21** (ou superior compatível).

---

## Tutorial 1 — Clonar o repositório

### Passo 1: Escolher onde salvar o projeto

Abra o terminal e vá para a pasta onde você guarda os projetos da faculdade, por exemplo:

```bash
cd ~/Documentos/faculdade
```

### Passo 2: Clonar

Use **uma** das opções abaixo (HTTPS é mais simples na primeira vez; SSH exige chave configurada no GitHub).

**HTTPS (recomendado para iniciantes):**

```bash
git clone https://github.com/ViniciusDuranteBagio/OOP2.git
cd OOP2
```

**SSH (se você já configurou chave no GitHub):**

```bash
git clone git@github.com:ViniciusDuranteBagio/OOP2.git
cd OOP2
```

### Passo 3: Conferir se deu certo

```bash
git status
git branch
```

Você deve ver a branch `main` e a mensagem de que o working tree está limpo (ou apenas arquivos que você ainda não alterou).

### Passo 4: Rodar o projeto (opcional, para validar o ambiente)

Na pasta do projeto (onde está o `pom.xml`):

```bash
./mvnw spring-boot:run
```

No Windows (Prompt ou PowerShell):

```bash
mvnw.cmd spring-boot:run
```

Se subir sem erro, o ambiente está ok. Para parar o servidor: `Ctrl + C`.

---

## Tutorial 2 — Criar e usar a sua branch

Cada aluno trabalha na **própria branch**. Assim ninguém sobrescreve o trabalho do colega nem tenta enviar código direto para a `main`.

### Convenção de nome da branch

Use um padrão fácil de identificar, por exemplo:

```text
aluno/seu-nome-sobrenome
```

Exemplos:

- `aluno/maria-silva`
- `aluno/joao-santos`
- `aluno/ana-costa`

Use **letras minúsculas**, **hífen** entre palavras e **sem espaços**.

### Passo 1: Atualizar a `main` local

Sempre comece com a `main` atualizada (só leitura — você não vai commitar nela):

```bash
git checkout main
git pull origin main
```

### Passo 2: Criar sua branch a partir da `main`

Substitua pelo **seu** nome:

```bash
git checkout -b aluno/maria-silva
```

Isso cria a branch e já muda para ela. Confira:

```bash
git branch
```

O asterisco (`*`) deve estar na sua branch, não na `main`.

### Passo 3: Desenvolver e salvar commits na **sua** branch

Depois de editar arquivos:

```bash
git status
git add .
git commit -m "Descrição clara do que você fez"
```

Exemplos de mensagem:

- `Adiciona entidade Produto e repositório JPA`
- `Implementa endpoint GET /api/clientes`

### Passo 4: Enviar **sua** branch para o GitHub

Na primeira vez:

```bash
git push -u origin aluno/maria-silva
```

Nas próximas vezes (já na sua branch):

```bash
git push
```

### Passo 5: Voltar para a sua branch depois de abrir o projeto de novo

Se você fechou o computador ou o terminal:

```bash
cd ~/Documentos/faculdade/OOP2   # ajuste o caminho
git fetch origin
git checkout aluno/maria-silva
git pull origin aluno/maria-silva
```

---

## O que **não** fazer

| Evite | Por quê |
|-------|---------|
| `git push origin main` | A `main` é protegida; o push será bloqueado ou gerará conflito com o time |
| Trabalhar na branch de outro aluno | Mistura o trabalho de duas pessoas |
| Commitar na `main` local e depois “empurrar” | Mesmo localmente, o fluxo correto é **sua branch** |
| Subir senhas, tokens ou `.env` | Use `application-local.properties` (já está no `.gitignore`) |

Se você estiver na `main` por engano:

```bash
git checkout aluno/seu-nome-sobrenome
```

---

## Fluxo resumido (cola rápida)

```bash
# 1. Clonar (só uma vez)
git clone https://github.com/ViniciusDuranteBagio/OOP2.git
cd OOP2

# 2. Criar sua branch (só uma vez)
git checkout main
git pull origin main
git checkout -b aluno/seu-nome-sobrenome

# 3. Dia a dia
git checkout aluno/seu-nome-sobrenome
git pull origin aluno/seu-nome-sobrenome   # se a branch já existir no remoto
# ... editar código ...
git add .
git commit -m "Sua mensagem"
git push -u origin aluno/seu-nome-sobrenome   # primeira vez
git push                                       # demais vezes
```

---

## Estrutura do projeto

```text
.
├── src/main/java/          # Código da aplicação
├── src/main/resources/     # Configurações (ex.: application.properties)
├── src/test/java/          # Testes
├── pom.xml                 # Dependências Maven
├── mvnw / mvnw.cmd         # Maven Wrapper (não precisa instalar Maven globalmente)
└── .gitignore              # Arquivos que não vão para o Git
```

---

## Configuração local (opcional)

Para ajustes só na sua máquina (porta, usuário de banco, etc.), crie:

`src/main/resources/application-local.properties`

Esse arquivo **não** é versionado. No `application.properties` compartilhado, mantenha apenas o que vale para todos.

---

## Dúvidas comuns

**“Dei push na branch errada.”**  
Peça orientação ao professor antes de forçar push (`--force`). Em geral, o correto é criar commits na branch certa e não reescrever histórico do colega.

**“Apareceu conflito no `git pull`.”**  
Normal quando a `main` avançou. Na **sua** branch: `git pull origin main` (ou siga o passo a passo que o professor passar em aula) e resolva os arquivos marcados como conflito.

**“O Git pede usuário e senha.”**  
No HTTPS, o GitHub usa **Personal Access Token** no lugar da senha da conta. Veja a documentação: [Creating a personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token).

---

## Repositório

- **GitHub:** [github.com/ViniciusDuranteBagio/OOP2](https://github.com/ViniciusDuranteBagio/OOP2)

Em caso de dúvida sobre Git ou o projeto, fale com o professor na aula de **Orientação a Objetos 2**.
