package main;

/**
 * Validador de expressões regulares que usa uma tabela de transcrição para representar o autômato finito
 * determinístico que reconhece a expressão regular
 */
public class RegularExpressionValidator {
    StringBuilder result = new StringBuilder();
    StringBuilder currentWord = new StringBuilder();
    private boolean invalidSymbol = false;
    private final int[] finalStates;
    private int symbolState;
    private final int[][] transitionTable;
    private final char[] expressionInputChars;
    private final char[] arithmeticOperators;
    private final char[] alphabet;

    public RegularExpressionValidator(String expressionInput, int[][] transitionTable,
                                      int[] finalStates, char[] arithmeticOperators, char[] alphabet) {
        expressionInputChars = expressionInput.replace("\n", " ").toCharArray();
        this.transitionTable = transitionTable;
        this.finalStates = finalStates;
        this.arithmeticOperators = arithmeticOperators;
        this.alphabet = alphabet;
    }

    /** Este método classifica em uma das seguintes categorias:
     1 - Caractere é uma letra ou número
     2 - Caractere é um operador aritmético presente no array de operadores
     3 - Caractere é um espaço em branco
     4 - Caractere não é reconhecido como nenhum dos anteriores
     */
    private int classifyCharacter(char ch, char[] arithmeticOperators) {
        for (char c : alphabet){
            if (c == ch) {
                return 1;
            }
        }
        for (char c : arithmeticOperators){
            if (c == ch){
                return 2;
            }
        }
        if (ch > 0 && ch < 33){ // espaço ou quebra de linha
            return 3;
        }
        return 4; // valor para qualquer outro caractere não reconhecido pelo alfabeto
    }

    /** Esse método passa por cada caractere da expressão de entrada e classifica o caractere em um dos seguintes estados:
     _símbolo válido
     _operador aritmético
     _espaço em branco
     _símbolo inválido
     Ele define se a palavra formada pelos caracteres anteriores é uma sentença válida de acordo com a tabela de
     transcrição e estados finais válidos. Se a palavra for válida, adiciona a sentença à saída do método. Se a palavra
     for inválida, adiciona uma mensagem de erro à saída do método.
     */

    public String processExpressions() {
        for (char ch : expressionInputChars) { // passa por toda a expressão
            int symbolState = classifyCharacter(ch, arithmeticOperators); // classifica o caractere atual

            switch (symbolState) {
                case 4 -> {// caractere inválido
                    invalidSymbol = true;
                    currentWord.append(ch);
                }
                case 2 -> { // operador aritmético
                    if (invalidSymbol) { // Se true, símbolo inválido já foi encontrado e deve ser reportado.
                        result.append("ERRO: símbolo(s) inválido(s):\t\t").append(currentWord).append('\n');
                        currentWord.setLength(0); // Limpa
                        result.append("operador aritmético:\t\t").append(ch).append('\n');
                        invalidSymbol = false; // Marca que não há mais símbolos inválidos
                        break;
                    }
                    if (currentWord.length() > 0) { // Se não for vazia, verifica se é válido ou não
                        if (checkSentence(currentWord.toString(), transitionTable, finalStates)) {
                            result.append("sentença válida:\t\t").append(currentWord).append('\n');
                        } else {
                            result.append("ERRO: sentença inválida:\t\t").append(currentWord).append('\n');
                        }
                    }
                    result.append("operador aritmético:\t\t").append(ch).append('\n');
                    currentWord.setLength(0);
                }
                case 3 -> { // espaço
                    if (invalidSymbol) {
                        result.append("ERRO: símbolo(s) inválido(s):\t\t")
                                .append(currentWord).append('\n');
                        currentWord.setLength(0); // Limpa
                        invalidSymbol = false;
                        break;
                    }
                    if (currentWord.length() > 0) {
                        if (checkSentence(currentWord.toString(), transitionTable, finalStates)) {
                            result.append("sentença válida:\t\t").append(currentWord).append('\n');
                        } else {
                            result.append("ERRO: sentença inválida:\t\t").append(currentWord).append('\n');
                        }
                    }
                    currentWord.setLength(0);
                }
                default -> // símbolo válido
                        currentWord.append(ch);
            }
        }
        if (currentWord.length() > 0) {
            if (invalidSymbol) {
                result.append("ERRO: símbolo(s) inválido(s):\t\t").append(currentWord).append('\n');
            } else if (checkSentence(currentWord.toString(), transitionTable, finalStates)) {
                result.append("sentença válida:\t\t").append(currentWord).append('\n');
            } else {
                result.append("ERRO: sentença inválida:\t\t").append(currentWord).append('\n');
            }
        } else if (invalidSymbol) {
            result.append("ERRO: símbolo(s) inválido(s):\t\t").append(currentWord).append('\n');
        }

        return result.toString();
    }

    /** Verifica se a sentença é reconhecida pelo autômato finito determinístico definido pela tabela de transcrição
     * e lista de estados finais.
     @param text a sentença a ser verificada
     @param transitionTable a tabela de transcrição que define o autômato finito
     @param finalStates a lista de estados finais do autômato
     @return true se a sentença é reconhecida pelo autômato e false caso contrário
     */
    private boolean checkSentence(String text, int[][] transitionTable, int[] finalStates) {
        char[] sentence = text.toCharArray();
        int currentState = 0;
        int letter;

        for (char ch : sentence) { // loop para cada caractere da sentença
            switch (ch) {
                case 'a' -> letter = 0; // define o valor da letra de acordo com o caractere atual
                case 'b' -> letter = 1;
                case 'c' -> letter = 2;
                case 'd' -> letter = 3;
                default -> { // se a letra não estiver na lista de caracteres esperados, retorna false
                    return false;
                }
            }

            // se a transição atual leva a um estado inválido, retorna false
            if (transitionTable[currentState][letter] == 12) {
                return false;
            }
            currentState = transitionTable[currentState][letter]; // atualiza o estado atual com o resultado da transição atual
        }

        // verifica se o estado atual está na lista de estados finais
        for (int x : finalStates) {
            if (currentState == x) {
                return true; // se sim, retorna true
            }
        }
        return false; // se não estiver em nenhum dos estados finais, retorna false
    }
}
