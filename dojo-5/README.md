# Dojo 5
## Desafio de Natal

Em tempos de corona não está fácil nem para o papai noel, o bom velhinho não está podendo sair de casa. 
O natal está chegando e junto com ele várias cartinhas com milhares de pedidos e o velhinho 
não quer deixar ninguém na mão.

Nos natais anteriores o bom velhinho sempre deu conta do recado, dizem que ele faz todas as entregas na velocidade da luz. 
Nesse ano temos uma lista de voluntários de todos os continentes, prontos para ajudar nessa missão. 
Agora cabe ao Sistema Natalino de Entregas (SNE) coordenar as ações em cada continente.

Todo o ano o SNE realiza uma triagem para separar e organizar as cartinhas de natal que são enviadas de 
vários lugares do mundo. O sistema de triagem processa mensagens enviadas por email e correios separando em duas 
listas que são enviadas para avaliação.

O Noel fica responsável por avaliar o comportamento das crianças ao longo do ano e faz uma anotação em cada lista
que por fim é enviada para a Fábrica Natalina (FN) que dá sequência na produção.

A FN recebe as listas e consolida numa lista única sendo que nesta consolidação é aplicado um filtro para remover 
da lista as crianças que tiveram mau comportamento.

No dia 24 de dezembro é feito o carregamento do trenó com todos os presentes, Noel recebe a lista de pedidos organizada e parte para as entregas.

Esse ano como Noel não vai poder fazer as entregas, voluntários de vários continentes se prontificaram para ajudar. 
Porém, para isso precisamos alterar alguns fluxos do SNE para separar todos os pedidos em várias listas.

Precisamos do seguinte:

1. Ler as listas de pedidos
    1. Lista do sistema NatalWeb
    2. Lista do sistema PostalService
2. Consolidar os dados
3. Remover da lista os pedidos das crianças que tiveram mau comportamento (`bad_child = true`)
4. Agrupar por continentes (1 lista por continente)

Formato das listas de pedidos:
```json
[
  {
    "name": "Luke Barnes",
    "gender": "Male",
    "address": "20 Quilling Close Trowbridge",
    "continent": "Australia",
    "email": "l.barnes@randatmail.com",
    "wish_gift": "puzzle",
    "bad_child": false
  },
  ...
]
```

Formato de saída esperado para cada lista:
```json
{
  "continent": "Australia",
  "orders": [
    {
      "name": "Luke Barnes",
      "gender": "Male",
      "address": "20 Quilling Close Trowbridge",
      "email": "l.barnes@randatmail.com",
      "gift": "puzzle"
    },
    ...
  ]
}
```

## **Requisitos**

- Não realizar operações de I/O na thread principal.
- Não executar filtros na thread principal.
- Otimizar o tempo de execução priorizando tarefas que podem ser feitas em paralelo.
- Escrever teste

## Conhecimentos abordados nesse desafio

- Coroutines - [Composite Suspending Functions](https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html)
- Coroutines - [Context e Dispatchers](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html)
- Kotlin - [Grouping](https://kotlinlang.org/docs/reference/collection-grouping.html)
- Kotlin - [Filter](https://kotlinlang.org/docs/reference/collection-filtering.html)
- File - [Read Write](https://khan.github.io/kotlin-for-python-developers/#file-io)
- Kotlin - [Serialization](https://kotlinlang.org/docs/reference/serialization.html#serialization)