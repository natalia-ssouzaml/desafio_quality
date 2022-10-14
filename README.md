<div align="center">
  <img src="meli.png" alt="imagem meli"/>
</div>

# Desafio Quality (Testing) - Grupo8

Implemente validações e testes para um cenário determinado: A partir de um cenário conhecido, deverá se estabelecer os
diferentes processos de validação de dados e processos de testes unitários necessários. E para isso, imagine que a
empresa "Seu Imóvel" precisa conseguir calcular a metragem e o custo dos diferentes imóveis que possui em sua
carteira de clientes.

Para isso, solicita de cada **Imóvel**:

- *um nome do imóvel,*
- *um bairro,*
- *e a lista de cômodos.*

Um **Bairro** deve ter:

- *nome do bairro,*
- *valor do metro quadrado no bairro*

Cada **cômodo** tem:

- *um nome,*
- *uma largura e*
- *um comprimento.*

## :sparkles: Funcionalidades

- **US-0001**: calcular a área total de uma propriedade.
- **US-0002**: indicar o preço dessa mesma propriedade com base na área total. Lembre-se que os preços por metro
  quadrado são determinados em função do bairro.
- **US-0003**: determinar qual é o maior cômodo da propriedade.
- **US-0004**: determinar a área de cada cômodo.

## :busts_in_silhouette: Autores

- [@heitorsguedes](https://www.github.com/heitorsguedes)
- [@LucasVG97](https://www.github.com/LucasVG97)
- [@matkaf](https://www.github.com/matkaf)
- [@matheusbruder](https://www.github.com/matheusbruder)
- [@natalia-ssouzaml](https://www.github.com/natalia-ssouzaml)

## :books: Documentação da API

### Property endpoints

#### Retorna a propriedade criada

```http
  POST localhost:8080/property
```

###### **@RequestBody**

```json
{
  "name": "Hotel do Mauri",
  "districtId": 4,
  "rooms": [
    {
      "name": "Hall",
      "width": 15.0,
      "length": 7.0
    },
    {
      "name": "Quarto",
      "width": 6.0,
      "length": 7.0
    },
    {
      "name": "Banheiro",
      "width": 3.0,
      "length": 10.0
    }
  ]
}
```

#### Retorna cálculo da área total de uma propriedade (US-0001).

```http
  GET localhost:8080/property/totalM2/{property}
```

#### Retorna preço da propriedade com base na área total (US-0002).

```http
  GET localhost:8080/property/price/{property}
```

#### Retorna uma lista com a área de cada cômodo da propriedade (US-0004).

```http
  GET localhost:8080/property/totalM2ByRooms/{property}
```

#### Retorna o maior cômodo da propriedade (US-0003).

```http
  GET localhost:8080/property/biggestRoom/{property}
```

## :file_folder: Download Endpoints

- [Collection (endpoints)](/DesafioQuality.postman_collection.json)
