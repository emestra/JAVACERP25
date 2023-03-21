package PreguntasDelCurso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @file AdminGUI.java
 * @brief Clase para representar la interfaz gráfica de administración de preguntas.
 * @date 20/03/2023
 * @version 1.0
 */
public class AdminGUI extends JFrame implements ActionListener {

    private PreguntasList questionList;
    private FileHandler fileHandler;
    private int currentQuestionIndex;

    private JLabel idLabel;
    private JLabel preguntaLabel;
    private JTextField preguntaField;
    private JLabel opcionesLabel;
    private JTextField opcionesField;
    private JLabel correctaLabel;
    private JTextField correctaField;
    private JButton siguienteButton;
    private JButton confirmarButton;
    private JButton atrasButton;

    /**
     * Constructor de la clase AdminGUI.
     * @param questionList la lista de preguntas existente.
     * @param fileHandler el manejador de archivos a utilizar.
     */
    public AdminGUI(PreguntasList questionList, FileHandler fileHandler) {
        this.questionList = questionList;
        this.fileHandler = fileHandler;
        this.currentQuestionIndex = 0;

        // Configuración de la ventana
        setTitle("Panel de Administración de Preguntas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configuración de los componentes
        idLabel = new JLabel("ID de pregunta: 1");
        idLabel.setBounds(20, 20, 200, 20);

        preguntaLabel = new JLabel("Pregunta:");
        preguntaLabel.setBounds(20, 50, 80, 20);

        preguntaField = new JTextField();
        preguntaField.setBounds(100, 50, 300, 20);

        opcionesLabel = new JLabel("Opciones:");
        opcionesLabel.setBounds(20, 80, 80, 20);

        opcionesField = new JTextField();
        opcionesField.setBounds(100, 80, 300, 20);

        correctaLabel = new JLabel("Respuesta correcta:");
        correctaLabel.setBounds(20, 110, 140, 20);

        correctaField = new JTextField();
        correctaField.setBounds(160, 110, 240, 20);

        siguienteButton = new JButton("Siguiente");
        siguienteButton.setBounds(150, 170, 100, 30);
        siguienteButton.addActionListener(this);

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(260, 170, 100, 30);
        confirmarButton.addActionListener(this);

        atrasButton = new JButton("Atrás");
        atrasButton.setBounds(20, 170, 100, 30);
        atrasButton.addActionListener(this);

        // Agregar los componentes a la ventana
        add(idLabel);
        add(preguntaLabel);
        add(preguntaField);
        add(opcionesLabel);
        add(opcionesField);
        add(correctaLabel);
        add(correctaField);
        add(siguienteButton);
        add(confirmarButton);
        add(atrasButton);
    }

    /**
     * Acción a realizar cuando se presiona un botón en la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == siguienteButton) {
            // Actualizar el índice de la pregunta actual y el ID de pregunta
            currentQuestionIndex++;
            idLabel.setText("ID de pregunta: " + (questionList.size() + 1));

            // Limpiar los campos de pregunta, opciones y respuesta correcta
            preguntaField.setText("");
            opcionesField.setText("");
            correctaField.setText("");
        } else if (e.getSource() == confirmarButton) {
            // Obtener los valores de los campos de texto
            String pregunta = preguntaField.getText();
            String opcionesString = opcionesField.getText();
            String correcta = correctaField.getText();

            // Separar las opciones en un arreglo
            String[] opciones = opcionesString.split(",");

            // Crear la nueva pregunta
            Pregunta nuevaPregunta = new Pregunta(questionList.size() + 1, pregunta, correcta, opciones);

            // Agregar la pregunta a la lista y escribir la lista en el archivo
            questionList.addQuestion(nuevaPregunta);
            try {
                fileHandler.writeQuestions(questionList);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al escribir en el archivo: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Mostrar mensaje de confirmación
            JOptionPane.showMessageDialog(this, "Pregunta agregada correctamente.", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);

            // Actualizar el índice de la pregunta actual y el ID de pregunta
            currentQuestionIndex++;
            idLabel.setText("ID de pregunta: " + (questionList.size() + 1));

            // Limpiar los campos de pregunta, opciones y respuesta correcta
            preguntaField.setText("");
            opcionesField.setText("");
            correctaField.setText("");
        } else if (e.getSource() == atrasButton) {
            // Cerrar la ventana actual y volver a la ventana anterior
            dispose();
            //new MenuGUI(questionList, fileHandler);
        }
    }
}
