# Boletim

### Problema

Na escola CodeMN todo o final de semestre temos que gerar o boletim com as notas dos alunos. Atualmente esse processo é executado de forma manual utilizando apenas uma planilha para fazer o calculo das notas junto com formatação condicional para saber o status do aluno em função da nota.

Ao longo do semestre o aluno faz 3 atividades:

- Atividade feita em sala de aula (Task)
- Prova 1 (Test 1)
- Prova 2 (Test 2)

Abaixo temos os campos dessa planilha:

| id  	| name          	| subject 	| type   	| value 	|
|-----	|---------------	|---------	|--------	|-------	|
| 001 	| João da Silva 	| 01-Java 	| Task   	| 10    	|
| 001 	| João da Silva 	| 01-Java 	| Test 1 	| 10    	|
| 001 	| João da Silva 	| 01-Java 	| Test 2 	| 8     	|
| 002 	| Maria de Souza 	| 01-Java 	| Task   	| 5     	|
| 002 	| Maria de Souza 	| 01-Java 	| Test 1 	| 4     	|
| 002 	| Maria de Souza 	| 01-Java 	| Test 2 	| 5     	|


No final do semestre uma pessoa fica responsável por filtrar e calcular as notas dos alunos e consolidar em outra planilha.

Para calcular a nota de cada matéria temos que **descartar a menor nota que o aluno tirou na Prova** e depois fazer o cálculo da média com as demais. 

Feito isso, atualizamos o valor na planilha de cálculo  da média final.

| id  	| name          	| 01-Java 	| 02-Kotlin | 03-Python | Final     | Status |
|-----	|---------------	|---------	|--------	|-------	|-------    |------- |
| 001 	| João da Silva 	| 10     	| 10    	| 10    	|10         |Approved|
| 002 	| Maria de Souza 	| 5     	| 5     	| 5     	|5          |Reproved|


Nesta planilha temos a média final do aluno que é calculada com base nas médias de cada matéria. 

Para ser aprovado o aluno deve ter uma **média final igual ou superior a 6** caso contrário o mesmo será reprovado. 

Finalizado esse processo de cálculo o responsável utiliza um template do boletim e atualiza com as notas de cada aluno que é impressa e enviada por email para os pais.

## Solução

Para resolver esse problema a escola contratou a MN Software para criar um sistema para gerar o boletim dos alunos.

A escola já desenvolveu um pequeno sistema para os professores lançarem as notas dos alunos. No final do semestre esse sistema gera um arquivo com as notas de todos os alunos (tasks.json).

A MN fica responsável por desenvolver dois sistemas, são eles:

### Cálculo das notas

Este sistema é responsável por consolidar as notas dos alunos abaixo segue uma ideia levantada por um dos analistas da MN de como o sistema deve funcionar:

1. Recuperar arquivo tasks.json.
2. Serializar os dados.
3. Fazer o cálculo da nota de cada matéria de cada aluno.
4. Criar um arquivo **final_media.json** e salvar o resultado.

### Emissão de boletim

Neste desenvolvimento não vamos integrar o sistema de boletim com a impressora, então devemos criar um sistema para gerar os boletins para serem impressos e enviados para os pais dos alunos.
1. Recuperar o arquivo.
2. Serializar os dados.
3. Criar um arquivo para cada aluno com o nome: **ano-id-nome_do_aluno.txt**
O arquivo deverá conter a seguinte formatação:
```
NOME DO ALUNO - STATUS - NOTA FINAL

DISCIPLINA 1 - NOTA
DISCIPLINA 2 - NOTA
DISCIPLINA 3 - NOTA
```
Exemplo:
```
JOÃO DA SILVA - APPROVADO - 9.5

01-JAVA   -  9.5
02-KOTLIN - 10.0
03-PYTHON -  9.0
```