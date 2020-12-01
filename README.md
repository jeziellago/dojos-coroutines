# Boletim

### Problema

Nas escola CodeMN todo o final de semestre temos que gerar o boletim com as notas dos alunos. Atualmente esse processo é executado de forma manual utilizando apenas uma planilha para fazer o calculo das notas junto com formatação condicional para saber o status do aluno em função da nota.

Ao longo do semestre o aluno faz 3 tipos de atividade:

- Atividade feita em sala de aula (Task)
- Prova 1 (Test 1)
- Prova 2 (Test 2)

Abaixo temos os campos dessa planilha:

| id  	| name          	| subject 	| type   	| value 	|
|-----	|---------------	|---------	|--------	|-------	|
| 001 	| User Name 001 	| 01-Java 	| Task   	| 10    	|
| 001 	| User Name 001 	| 01-Java 	| Test 1 	| 10    	|
| 001 	| User Name 001 	| 01-Java 	| Test 2 	| 8     	|
| 002 	| User Name 002 	| 01-Java 	| Task   	| 5     	|
| 002 	| User Name 002 	| 01-Java 	| Test 1 	| 4     	|
| 002 	| User Name 002 	| 01-Java 	| Test 2 	| 5     	|


Todo fim de semestre uma pessoa fica responsável por filtrar e calcular as notas dos alunos e consolidar em outra planilha

Para calcular a nota de cada matéria temos que **descartar a menor nota que o aluno tirou na Prova** e depois fazer o calculo da média com as demais. 

Feito isso atualizamos o valor na planilha de calculo  da média final.

TODO Adicionar tabela

Nesta planilha temos a média final do aluno que é calculada com base nas médias de cada matéria. 

Para ser aprovado o aluno deve ter uma **média final igual ou superior a 6** caso contrario o mesmo será reprovado. 

Finalizado esse processo de calculo o responsável utiliza um template do boletim e atualiza com as notas de cada aluno que é impressa e enviada por email para os pais.
