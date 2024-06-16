# Máquina de Turing

Este código implementa uma Máquina de Turing para reconhecer sentenças de linguagens específicas com uma fita. A função de transição é lida a partir de um arquivo texto. A interface permite selecionar o arquivo a ser lido, assim como a sentença a ser reconhecida pela Máquina de Turing. A cada passo da execução, são exibidos na tela o número do passo, o símbolo lido e a fita. Ao final, a aplicação indica se a sentença foi aceita ou rejeitada.

### Definição de Máquina de Turing

Uma Máquina de Turing é composta por três partes principais:
1. **Fita**: Utilizada simultaneamente como dispositivo de entrada, saída e memória de trabalho. A fita é finita à esquerda e infinita à direita, dividida em células que armazenam símbolos.
2. **Unidade de Controle**: Reflete o estado corrente da máquina e possui uma cabeça de leitura/escrita que acessa uma célula da fita de cada vez, movendo-se para a esquerda ou para a direita.
3. **Função de Transição**: Define o estado da máquina e comanda leituras, gravações e o sentido de movimento da cabeça.

A máquina começa no estado inicial e processa a palavra de entrada célula por célula. Dependendo do símbolo lido e do estado atual, a função de transição determina o novo estado, o símbolo a ser escrito e a direção do movimento da cabeça. O processamento continua até que a máquina alcance um estado final, indicando que a palavra foi aceita, ou até que não existam mais transições válidas, indicando que a palavra foi rejeitada.

### Duplo Balanceamento
No exemplo fornecido por "Linguagens Formais e Autômatos - P. B. Menezes", a máquina de Turing processa palavras na forma L = { a^n b^n | n ≥ 0 }:
1. Marca o primeiro 'a' encontrado com 'X' e move-se para a direita para encontrar um 'b'.
2. Marca o 'b' com 'Y' e volta para a esquerda até encontrar um 'X'.
3. Repete o processo até que todos os 'a's e 'b's sejam marcados.
4. Se a fita estiver no formato correto, a máquina aceita a palavra ao alcançar o estado final \(q4\). Caso contrário, rejeita a palavra ao não encontrar transições válidas.

### Regras de Aceitação
Para que uma sentença seja aceita, ela deve seguir a forma  a^n b^n, onde o número de 'a's é igual ao número de 'b's. 

#### Exemplos de Sentenças Válidas
1. `ab`
2. `aabb`
3. `aaabbb`

### Referências
_Menezes, P. B. (2008). *Linguagens Formais e Autômatos*. 1ª ed., Bookman. ISBN-10: 8577802663, ISBN-13: 978-8577802661._