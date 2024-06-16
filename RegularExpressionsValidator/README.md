_ENG_
### Recognizer of Regular Languages

This code implements a recognizer for regular expressions using a deterministic finite automaton (DFA). The program can analyze an input character sequence, classifying them into specific categories according to a transition table.

##### Transition Table
The transition table defines how the automaton changes state based on the input symbol.
Table used:

| δ   | a  | b  | c  | d  |
|-----|----|----|----|----|
| e0  | e8 | e1 | -  | e6 |
| e1  | -  | -  | e2 | -  |
| e2  | -  | e3 | -  | -  |
| e3  | -  | -  | e4 | -  |
| e4  | -  | e1 | -  | e6 |
| e5  | -  | e9 | -  | -  |
| e6  | -  | -  | -  | e7 |
| e7  | -  | -  | -  | e6 |
| e8  | e0 | e9 | -  | -  |
| e9  | -  | -  | e10| -  |
| e10 | -  | e11| -  | e7 |
| e11 | -  | -  | e5 | -  |

#### Observations
* Control characters (space, newline, etc.) are ignored in the output.
* Arithmetic operators and control characters are used as token delimiters.
* For the sentence to be recognized, characters must be read one by one.

### Example

**Input**
```
aabcbcd       +bcbcddd 
aaabc  /abcdyyyyy** 
bcbcbcddddd 
dddaa             834aad 
xxxycde
```

**Output**
```
valid sentence:   aabcbcd
arithmetic operator:   +
valid sentence:   bcbcddd
valid sentence:   aaabc
ERROR: invalid sentence:   aabcbcbc
ERROR: invalid sentence:   bca
valid sentence:   d
arithmetic operator:   /
ERROR: invalid sentence:  abcdyyyyy
arithmetic operator:   *
arithmetic operator:   *
ERROR: invalid sentence:  bcbcbcddddd
ERROR: invalid sentence:  dddaa
ERROR: invalid symbol(s): 834aad
ERROR: invalid symbol(s): xxxycde
```

---

_PT_BR_
### Reconhecedor de Linguagens Regulares
Este código implementa um reconhecedor de expressões regulares usando um autômato finito determinístico (AFD)
O programa é capaz de analisar uma sequência de caracteres de entrada, classificando-os em categorias específicas de acordo com uma tabela de transição.

##### Tabela de Transição
A tabela de transição define como o autômato muda de estado com base no símbolo de entrada. 
Tabela usada:

| δ   | a  | b  | c  | d  |
|-----|----|----|----|----|
| e0  | e8 | e1 | -  | e6 |
| e1  | -  | -  | e2 | -  |
| e2  | -  | e3 | -  | -  |
| e3  | -  | -  | e4 | -  |
| e4  | -  | e1 | -  | e6 |
| e5  | -  | e9 | -  | -  |
| e6  | -  | -  | -  | e7 |
| e7  | -  | -  | -  | e6 |
| e8  | e0 | e9 | -  | -  |
| e9  | -  | -  | e10| -  |
| e10 | -  | e11| -  | e7 |
| e11 | -  | -  | e5 | -  |

#### Observações
* Os caracteres de controle (espaço, quebra de linha, etc.) são ignorados na saída
* Os operadores aritméticos e caracteres de controle são usados como delimitadores de tokens
* Para que a sentença seja reconhecida, os caracteres devem ser lidos um a um

### Exemplo

**Entrada**
```
aabcbcd       +bcbcddd 
aaabc  /abcdyyyyy** 
bcbcbcddddd 
dddaa             834aad 
xxxycde
```

**Saída**
```
sentença válida:   aabcbcd
operador aritmético:   +
sentença válida:   bcbcddd
sentença válida:   aaabc
ERRO: sentença inválida:   aabcbcbc
ERRO: sentença inválida:   bca
sentença válida:   d
operador aritmético:   /
ERRO: sentença inválida:  abcdyyyyy
operador aritmético:   *
operador aritmético:   *
ERRO: sentença inválida:  bcbcbcddddd
ERRO: sentença inválida:  dddaa
ERRO: símbolo(s) inválido(s): 834aad
ERRO: símbolo(s) inválido(s): xxxycde
```
