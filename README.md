# Desafio AZship

Aplicação REST Java + Spring + PostgreSQL para listagem, consulta, cadastro e deleção de clientes, veiculo, carga e frete.

### Arquivo de auxilio ao teste:

- [Collection do Postman com Endpoints + paramentros](https://github.com/frank000/adm-azs-shipping/blob/master/AZShip%20-%20Collection.postman_collection.json)

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

#### Cadastrar um cliente

```http
  POST /api/clientes/
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nome`      | `string` | **Obrigatório**. Nome do cliente |
| `email`      | `string` | **Obrigatório**. Email do cliente |
| `telefone`      | `string` | **Obrigatório**. Telefone do cliente |

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




#### Listar Fretes

```http
  GET /api/fretes/
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `cliente`      | `string` | **Opcional**. Cliente contratante do frete |
| `veiculo`      | `string` | **Opcional**. Descrição veículo responsável pelo frete  |
| `carga`      | `string` | **Opcional**. Descrição da carga |
| `placa`      | `string` | **Opcional**. Placa do veiculo responsável do frete |

#### Cadastrar um Frete

```http
  POST /api/fretes/
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `cliente`      | `string` | **Obrigatório**. ID do cliente |
| `veiculo`      | `string` | **Obrigatório**. ID do veículo |
| `tipo`      | `char` | **Obrigatório**. "P" = PESO, "C" = CUBAGEM, "N" = NÃO SE APLICA |
| `carga`      | `array` | **Obrigatório**. KEY-VALUES da tabela abaixo |

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `descricao`      | `string` | **Obrigatório**. Descrição do objeto da carga |
| `valor`      | `string` | **Obrigatório**. Valor do objeto |
| `seguro`      | `boolean` | **Obrigatório**. Se tem seguro ou não |
| `peso`      | `string` | **Opcional**. Peso do objeto |
| `largura`      | `string` | **Opcional**. Largura do objeto em metros|
| `profundidade`      | `string` | **Opcional**. Profundidade do objeto em metros |
| `altura`      | `string` | **Opcional**. Altura do objeto em metro |
