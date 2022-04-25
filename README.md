# Desafio AZship

Aplicação REST Java + Spring + PostgreSQL para listagem, consulta, cadastro e deleção de clientes, veiculo, carga e frete.

## Autores

- [@franklimpaulino](https://www.github.com/frank000)



## Instalação

Fazer o clone do projeto e iniciar o projeto:

```bash
  git clone https://github.com/frank000/adm-azs-shipping.git
  cd adm-azs-shipping
  docker-compose up --build
```
    

## Documentação da API

#### Retorna todos os clientes

```http
  GET /api/clientes
```


#### Retorna um cliente

```http
  GET /api/clientes/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |



#### Retorna todos os veiculos

```http
  GET /api/veiculos
```


#### Retorna um veiculos

```http
  GET /api/veiculos/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

