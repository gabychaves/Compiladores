package main;

import javax.swing.*;

public class RegularLanguageRecognizer extends javax.swing.JFrame {
    private final char[] arithmeticOperator = {'/', '*', '-', '+'};
    private final char[] alphabet = {'a', 'b', 'c', 'd'};
    private final int[] finalStates = {6, 10};
    private final int[][] finiteAutomatonTransitionTable = {
            {8, 1, 12, 6, 12},   // e0
            {12, 12, 2, 12, 12},  // e1
            {12, 3, 12, 12, 12},  // e2
            {12, 12, 4, 12, 12},  // e3
            {12, 1, 12, 6, 12},   // e4
            {12, 9, 12, 12, 12},  // e5
            {12, 12, 12, 7, 12},  // e6
            {12, 12, 12, 6, 12},  // e7
            {0, 9, 12, 12, 12},   // e8
            {12, 12, 10, 12, 12}, // e9
            {12, 11, 12, 7, 12},  // e10
            {12, 12, 5, 12, 12},  // e11
            {12, 12, 12, 12, 12}  // e12
            // última linha indica estado de erro
            // última coluna indica estado de erro
    };

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException |
                 IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegularLanguageRecognizer.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RegularLanguageRecognizer().setVisible(true));
    }

    public RegularLanguageRecognizer() {
        initComponents();
    }


    //----------------SCREEN--------------------//
    private javax.swing.JTextArea jTextAreaIn;
    private javax.swing.JTextArea jTextAreaOut;
    private void initComponents() {
        JButton jButtonClear = new JButton();
        JButton btnAnalyze = new JButton();
        JScrollPane jScrollPane3 = new JScrollPane();
        jTextAreaIn = new javax.swing.JTextArea();
        JScrollPane jScrollPane4 = new JScrollPane();
        jTextAreaOut = new javax.swing.JTextArea();
        setTitle("regular language recognizer");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonClear.setText("clear");
        jButtonClear.addActionListener(this::jButtonClearActionPerformed);

        btnAnalyze.setText("analyze");
        btnAnalyze.addActionListener(this::jButtonAnalyzeActionPerformed);

        jTextAreaIn.setColumns(20);
        jTextAreaIn.setRows(5);
        jScrollPane3.setViewportView(jTextAreaIn);

        jTextAreaOut.setColumns(20);
        jTextAreaOut.setRows(5);
        jScrollPane4.setViewportView(jTextAreaOut);
        jTextAreaOut.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4)
                                        .addComponent(jScrollPane3)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 560, Short.MAX_VALUE)
                                                .addComponent(btnAnalyze, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonClear)
                                        .addComponent(btnAnalyze))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void jButtonAnalyzeActionPerformed(java.awt.event.ActionEvent evt) {
        RegularExpressionValidator rev = new RegularExpressionValidator(jTextAreaIn.getText(), finiteAutomatonTransitionTable, finalStates, arithmeticOperator, alphabet);
        jTextAreaOut.setText(rev.processExpressions());
    }

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {
        jTextAreaIn.setText("");
        jTextAreaOut.setText("");
    }
}
