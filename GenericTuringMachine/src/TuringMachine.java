import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa uma Máquina de Turing.
 * -> A máquina começa no estado q0 e marca o primeiro 'a' encontrado com 'X',
 * depois se move para a direita para encontrar um 'b'. Quando encontra um 'b',
 * ela marca com 'Y' e volta para a esquerda até encontrar um 'X'.
 * -> A máquina repete esse processo até que todos os 'a's e 'b's sejam marcados.
 * -> Quando a máquina não encontra mais 'a's não marcados, ela transita para o estado q3
 * e continua até encontrar o símbolo branco (representado por ß), indicando o final da palavra.
 * -> Se a fita estiver no formato correto (com todos os 'a's e 'b's processados corretamente),
 * a máquina aceita a palavra mudando para o estado final q4. Caso contrário,
 * a máquina rejeita a palavra ao não encontrar transições válidas.
 */
public class TuringMachine {
    private final Map<String, Transition> transitionFunction;
    private String tape;
    private int headPosition;
    private String currentState;
    private final String initialState;
    private final String blankSymbol;
    private final String finalState;
    private final StringBuilder steps;

    /**
     * Construtor da Máquina de Turing.
     * @param initialState Estado inicial.
     * @param finalState Estado final.
     * @param blankSymbol Símbolo branco.
     */
    public TuringMachine(String initialState, String finalState, char blankSymbol) {
        this.transitionFunction = new HashMap<>();
        this.initialState = initialState;
        this.finalState = finalState;
        this.blankSymbol = String.valueOf(blankSymbol);
        this.currentState = initialState;
        this.headPosition = 0;
        this.tape = "";
        this.steps = new StringBuilder();
    }

    /**
     * Define a fita de entrada da máquina.
     * @param input Entrada da fita.
     */
    public void setTape(String input) {
        this.tape = input + blankSymbol.repeat(100); // Adiciona espaços em branco para a direita
        this.headPosition = 0;
        this.currentState = initialState;
        this.steps.setLength(0); // Limpa os passos anteriores
    }

    /**
     * Adiciona uma transição à função de transição da máquina.
     * @param currentState Estado atual.
     * @param readSymbol Símbolo lido.
     * @param newState Novo estado.
     * @param writeSymbol Símbolo escrito.
     * @param moveDirection Direção do movimento (L para esquerda, R para direita).
     */
    public void addTransition(String currentState, char readSymbol, String newState, char writeSymbol, char moveDirection) {
        String key = currentState + readSymbol;
        Transition transition = new Transition(newState, writeSymbol, moveDirection);
        transitionFunction.put(key, transition);
    }

    /**
     * Executa a máquina de Turing na fita de entrada definida.
     * @return true se a entrada for aceita, false caso contrário.
     */
    public boolean execute() {
        int step = 0;
        while (!currentState.equals(finalState)) {
            char currentSymbol = tape.charAt(headPosition);
            String key = currentState + currentSymbol;

            if (!transitionFunction.containsKey(key)) {
                steps.append("Passo: ").append(step).append(", Símbolo lido: ").append(currentSymbol).append(", Fita: ").append(getRelevantTape()).append("\n");
                return false; // Rejeita a entrada se não houver transição definida
            }

            Transition transition = transitionFunction.get(key);
            tape = tape.substring(0, headPosition) + transition.writeSymbol + tape.substring(headPosition + 1);

            if (transition.moveDirection == 'R') {
                headPosition++;
            } else if (transition.moveDirection == 'L') {
                headPosition--;
            }

            currentState = transition.newState;

            steps.append("Passo: ").append(step).append(", Símbolo lido: ").append(currentSymbol).append(", Fita: ").append(getRelevantTape()).append("\n");
            step++;
        }

        steps.append("Passo final: ").append(step).append(", Estado final: ").append(currentState).append(", Fita: ").append(getRelevantTape()).append("\n");
        return true; // Aceita a entrada
    }

    /**
     * Retorna os passos da execução.
     * @return Passos da execução.
     */
    public String getSteps() {
        return steps.toString();
    }

    /**
     * Obtém a parte relevante da fita (sem os símbolos em branco adicionais).
     * @return Parte relevante da fita.
     */
    private String getRelevantTape() {
        int endPosition = tape.lastIndexOf(blankSymbol) + 1;
        return tape.substring(0, endPosition).trim();
    }

    /**
     * Classe interna que representa uma transição da máquina de Turing.
     */
    private static class Transition {
        String newState;
        char writeSymbol;
        char moveDirection;

        Transition(String newState, char writeSymbol, char moveDirection) {
            this.newState = newState;
            this.writeSymbol = writeSymbol;
            this.moveDirection = moveDirection;
        }
    }
}
