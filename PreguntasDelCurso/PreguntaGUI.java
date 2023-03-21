package PreguntasDelCurso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @file PreguntaGUI.java
 * @brief Clase que define la interfaz gráfica de usuario para mostrar preguntas y opciones de respuesta.
 * @date 2023-03-22
 * @version 1.0
 */

/**
 * @class PreguntaGUI
 * @brief Clase que define la interfaz gráfica de usuario para mostrar preguntas y opciones de respuesta.
 */
public class PreguntaGUI extends JFrame {
    /**
     * Etiqueta para mostrar la pregunta actual.
     */
    private JLabel questionLabel;

    /**
     * Botones para seleccionar las opciones de respuesta.
     */
    private JRadioButton[] answerButtons;

    /**
     * Botón para confirmar la respuesta seleccionada.
     */
    private JButton confirmButton;

    /**
     * Botón para mostrar una nueva pregunta.
     */
    private JButton nextButton;

    /**
     * Constructor que inicializa la interfaz gráfica.
     *
     * @param questionList la lista de preguntas a mostrar
     */
    public PreguntaGUI(PreguntasList questionList) {
        // Configurar la ventana
        setTitle("Juego de preguntas para Programación 3 - CeRP del Suroeste - Prof. Domingo Pérez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Panel para mostrar la pregunta
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setPreferredSize(new Dimension(600, 50)); // especificar una altura fija

        questionLabel = new JLabel("¿Pregunta?");
        questionPanel.add(questionLabel, BorderLayout.CENTER);

        add(questionPanel, BorderLayout.CENTER);

        // Crear el panel de respuestas
        JPanel answerPanel = new JPanel();
        answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // agregar márgenes
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));

        answerButtons = new JRadioButton[5];
        ButtonGroup buttonGroup = new ButtonGroup();

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new JRadioButton("Opción " + (i + 1));
            answerButtons[i].setActionCommand(Integer.toString(i));
            buttonGroup.add(answerButtons[i]);
            answerPanel.add(answerButtons[i]);
        }

        add(answerPanel, BorderLayout.SOUTH);

        // Panel para mostrar los botones de acción
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        //Primer pregunta
        Pregunta currentQuestion = questionList.getRandomQuestion();
        String correctAnswer = currentQuestion.getCorrecta();
        questionLabel.setText(currentQuestion.getPregunta());
        int correctButtonIndex = Randomizer.getRandomIndex(0, answerButtons.length - 1);
        //int correctButtonIndex = (int) (Math.random() * (answerButtons.length));
        answerButtons[correctButtonIndex].setText(correctAnswer);
        int optionIndex = 0;
        for (int i = 0; i < answerButtons.length; i++) {
            if (i == correctButtonIndex) {
                continue;
            }
            String option = currentQuestion.getOpciones()[optionIndex];
            /*if (option.equals(correctAnswer)) {
                optionIndex++;
            }*/
            answerButtons[i].setText(option);  

            optionIndex++;
        }

        confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAnswer = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
                Pregunta currentQuestion = questionList.getRandomQuestion();
                String correctAnswer = currentQuestion.getCorrecta();
                if (answerButtons[selectedAnswer].getText().equals(correctAnswer)) {
                    JOptionPane.showMessageDialog(PreguntaGUI.this, "¡Correcto!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PreguntaGUI.this, "¡Incorrecto!", "Resultado", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        actionPanel.add(confirmButton);

        nextButton = new JButton("Siguiente");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pregunta currentQuestion = questionList.getRandomQuestion();
                String correctAnswer = currentQuestion.getCorrecta();
                questionLabel.setText(currentQuestion.getPregunta());
                int correctButtonIndex = Randomizer.getRandomIndex(0, answerButtons.length - 1);
                //int correctButtonIndex = (int) (Math.random() * (answerButtons.length));
                answerButtons[correctButtonIndex].setText(correctAnswer);
                int optionIndex = 0;
                for (int i = 0; i < answerButtons.length; i++) {
                    if (i == correctButtonIndex) {
                        continue;
                    }
                    String option = currentQuestion.getOpciones()[optionIndex];
                    if (option.equals(correctAnswer)) {
                        optionIndex++;
                    }
                    answerButtons[i].setText(option);
                    optionIndex++;
                }
                buttonGroup.clearSelection();
            }
        });
        actionPanel.add(nextButton);
    
        add(actionPanel, BorderLayout.SOUTH);

        pack(); // Ajustar tamaño al contenido
        setResizable(false); // No permitir redimensionar
    }
}