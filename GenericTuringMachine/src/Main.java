import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe principal para iniciar a aplicação da Máquina de Turing.
 * A interface gráfica permite selecionar um arquivo de função de transição,
 * inserir uma sentença e executar a máquina de Turing.
 */
public class Main {
    private static TuringMachine turingMachine;

    public static void main(String[] args) {
        // Inicializa a máquina de Turing com estado inicial, estado final e símbolo em branco
        turingMachine = new TuringMachine("q0", "q4", 'ß');

        // Configuração da interface gráfica
        JFrame frame = new JFrame("Máquina de Turing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    /**
     * Configura os componentes da interface gráfica.
     */
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Sentença");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField sentenceText = new JTextField(20);
        sentenceText.setBounds(100, 20, 165, 25);
        panel.add(sentenceText);

        JButton selectFileButton = new JButton("Selecionar Arquivo");
        selectFileButton.setBounds(10, 60, 150, 25);
        panel.add(selectFileButton);

        JButton runButton = new JButton("Executar");
        runButton.setBounds(10, 100, 150, 25);
        panel.add(runButton);

        JLabel resultLabel = new JLabel("Resultado:");
        resultLabel.setBounds(10, 140, 300, 25);
        panel.add(resultLabel);

        JTextArea stepsArea = new JTextArea();
        stepsArea.setBounds(10, 180, 550, 150);
        stepsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stepsArea);
        scrollPane.setBounds(10, 180, 550, 150);
        panel.add(scrollPane);

        // Ação ao selecionar o arquivo de função de transição
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filepath = fileChooser.getSelectedFile().getPath();
                    loadTransitionFunction(filepath);
                }
            }
        });

        // Ação ao executar a máquina de Turing
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sentence = sentenceText.getText();
                turingMachine.setTape(sentence);
                boolean accepted = turingMachine.execute();
                resultLabel.setText("Resultado: " + (accepted ? "Aceito" : "Rejeitado"));
                stepsArea.setText(turingMachine.getSteps());
            }
        });
    }

    /**
     * Carrega a função de transição a partir de um arquivo texto.
     * @param filepath Caminho do arquivo de função de transição.
     */
    private static void loadTransitionFunction(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String currentState = parts[0];
                char readSymbol = parts[1].charAt(0);
                String newState = parts[2];
                char writeSymbol = parts[3].charAt(0);
                char moveDirection = parts[4].charAt(0);
                turingMachine.addTransition(currentState, readSymbol, newState, writeSymbol, moveDirection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
