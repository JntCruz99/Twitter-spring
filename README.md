# API Twitter Clone

![Twitter Clone Logo](https://cdn-icons-png.flaticon.com/256/1384/1384065.png)

Uma API simples inspirada no Twitter, onde os usuários podem criar perfis, postar twitters, seguir outros usuários e ver um feed personalizado.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- JSON para comunicação

## Funcionalidades Principais

- Cadastro de Usuário
- Postagem de Twitters
- Exibição de Todos os Twitters
- Exibição do Perfil do Usuário
- Seguir Usuários
- Exibir Feed Personalizado

## Endpoints

### Criar Usuário

Cria um novo perfil de usuário.

- **Endpoint:** `POST /twitter`
- **Payload:**


```markdown
{
  "userName": "nome_de_usuario",
  "nome": "Nome Completo"
}
```
### Criar Twitter

Cria um novo tweet associado a um usuário.
- **Endpoint:** `POST /twitter/{iduser}`
- **Payload:**
```markdown
{
  "mensagem": "Conteúdo do Tweet"
}
```

### Exibir Todos os Twitters

Retorna todos os twitters postados.

- **Endpoint:** `GET /twitter`

```markdown
{
  "id": 1,
  "mensagem": "Meu primeiro post",
  "data": "2023-08-23T20:08:17.663939",
  "usuario": "@User"
}
```

### Exibir Perfil do Usuário

Retorna as informações do perfil de um usuário específico.

- **Endpoint:** `GET /twitter/perfil/{idUser}`

```markdown
{
  "id": 1,
  "nome": "User",
  "userName": "@User",
  "twitters": [
  {
  "id": 1,
  "mensagem": "Meu primeiro post",
  "data": "2023-08-23T13:15:52.280983",
  "usuario": "@User"
  }
  ],
  "seguindo": [
  "@Vinicius"
  ],
  "seguidor": [
  "@Rebeca",
  "@Vinicius"
  ]
}
```

### Seguir Usuário

Permite que um usuário siga outro usuário.

- **Endpoint:** `POST /twitter/seguir/{seguidorId}/{seguidoId}`

### Seguir Usuário

Exibir Feed Personalizado

- **Endpoint:** `GET /twitter/feed/{usuarioId}`

### Como Executar

- Clone este repositório.
- Configure seu banco de dados e atualize as configurações em application.properties.
- Execute o aplicativo Spring Boot.

### Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um problema ou enviar um pull request.

### Autor

Jonatas Cruz Carneiro // jntcruz99
