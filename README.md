# Sistema de Gerenciamento Acadêmico

Projeto acadêmico desenvolvido em **Java** utilizando o padrão **MVC**, interface gráfica em **Swing**, estrutura de dados própria (`Lista<T>`) e persistência em **arquivos CSV**.

---

## Arquitetura do Sistema (MVC)

### **Model**  
Contém as classes que representam as entidades principais:  
- Curso  
- Disciplina  
- Professor  
- Inscrito  

---

### **View**  
Agrupa todas as telas desenvolvidas em Swing:  
- CRUD de cursos, disciplinas e professores  
- Tela de inscrição  
- Telas de consulta (inscritos, disciplinas do curso etc.)

A interface tem o papel de apenas exibir dados e capturar interações.

---

### **Controller**  
Responsável pela lógica da aplicação:  
- Validações  
- Comunicação entre tela e modelo  
- Leitura e escrita dos arquivos CSV  
- Atualização de listas  
- Abertura das telas

Cada entidade possui seu próprio controller especializado.

---

## Consultas por Código  
As buscas realizadas no sistema utilizam identificadores específicos:  
- **Professor:** consulta pelo **CPF**  
- **Inscrições:** consulta pelo **CPF** e **código da disciplina**  
- **Curso:** consulta pelo código do curso  
- **Disciplina:** consulta pelo código da disciplina  

---

## Diretório de Arquivos CSV

O sistema utiliza arquivos CSV para armazenar dados:

- `cursos.csv`  
- `disciplinas.csv`  
- `professores.csv`  
- `inscritos.csv`

### Observação importante  
É **recomendável** que o usuário crie a pasta:

```
C:\temp
```

Pois é onde o sistema está configurado para ler e salvar os arquivos CSV por padrão.

No entanto, **o diretório pode ser alterado manualmente** modificando os caminhos configurados nos arquivos de **controller**, caso o usuário deseje usar outra pasta.

---

## Funcionalidades

### ✔️ Cursos  
- Cadastrar  
- Editar  
- Remover  
- Listar  
- Exibir disciplinas vinculadas  

### ✔️ Disciplinas  
- Cadastrar  
- Editar  
- Remover  
- Listar  
- Filtrar por curso  

### ✔️ Professores  
- Cadastrar  
- Editar  
- Remover  
- Listar  
- Associar disciplinas  

### ✔️ Inscrições  
- Registrar inscrição  
- Consultar inscritos por disciplina  
- Filtrar inscrições por CPF  

---

## Estrutura do Projeto

```
/Semestral
 ├── controller/
 │     ├── curso/
 │     ├── disciplina/
 │     ├── professor/
 │     └── inscricao/
 ├── model/
 │     ├── curso/
 │     ├── disciplina/
 │     ├── professor/
 │     └── inscrito/
 ├── view/
 │     ├── cursos/
 │     ├── disciplinas/
 │     ├── professor/
 │     └── inscricao/
 ├── lista/
 ├── arquivos CSV
 └── Main.java
```

---

## Como Executar

1. Crie a pasta recomendada `C:\temp` **ou** ajuste os diretórios nos controllers.  
2. Coloque os arquivos CSV dentro dessa pasta.  
3. Importe o projeto na IDE (Eclipse recomendado).  
4. Execute a classe `Main.java`.  
5. Navegue normalmente pelas telas do sistema.

---

## Tecnologias Utilizadas

- Java 17+  
- Swing  
- Estrutura de dados própria (`Lista<T>`)  
- MVC  
- Persistência com arquivos CSV  
```
