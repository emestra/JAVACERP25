package PreguntasDelCursoMVC;
import PreguntasDelCurso.PreguntasList;
import PreguntasDelCurso.Pregunta;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @file AdminGUI.java
 * @brief Clase para representar la interfaz gráfica de administración de preguntas.
 * @date 20/03/2023
 * @version 1.0
 */
public class AdminGUI extends JFrame {

    private JLabel idLabel;
    private JLabel preguntaLabel;
    private JTextField preguntaField;
    private JLabel opcionesLabel;
    private JTextField opcionesField;
    private JLabel correctaLabel;
    private JTextField correctaField;
    private JButton confirmarButton;
    private JButton atrasButton;

    // Atributos no componentes
    private PreguntasList modelo;
    //private FileHandler fileHandler;
    private int currentQuestionIndex;
    // Agregar atributo InicioGUI llamado parentWindow
    private InicioGUI parentWindow;

    /**
 * Constructor de la clase AdminGUI.
 * 
 * @param modelo la lista de preguntas existente.
 * @param parentWindow el objeto InicioGUI que se utilizará como ventana padre.
 */
    public AdminGUI(PreguntasList modelo, InicioGUI parentWindow) {
        this.modelo = modelo;
        //this.fileHandler = fileHandler;
        this.currentQuestionIndex = 0;
        this.parentWindow = parentWindow; // Asignar el objeto recibido al atributo parentWindow

        initComponents();
    }

    /**
     * Inicializa y configura los componentes de la interfaz gráfica.
     */
    private void initComponents() {
        // Configuración de la ventana
        setTitle("Panel de Administración de Preguntas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel para mostrar la pregunta
        JPanel preguntaPanel = new JPanel();
        preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
        preguntaPanel.setPreferredSize(new Dimension(600, 50)); // especificar una altura fija

        // Configuración de los componentes
        idLabel = new JLabel("ID de pregunta: " + (modelo.size()+1));
        idLabel.setBounds(20, 20, 200, 20);
        preguntaPanel.add(idLabel, BorderLayout.CENTER);

        preguntaLabel = new JLabel("PREGUNTA:");
        preguntaLabel.setBounds(20, 50, 80, 20);
        preguntaPanel.add(preguntaLabel, BorderLayout.CENTER);

        preguntaField = new JTextField();
        preguntaField.setBounds(100, 50, 300, 20);
        preguntaPanel.add(preguntaField, BorderLayout.CENTER);

        opcionesLabel = new JLabel("Opciones:");
        opcionesLabel.setBounds(20, 80, 80, 20);
        preguntaPanel.add(opcionesLabel, BorderLayout.CENTER);

        opcionesField = new JTextField();
        opcionesField.setBounds(100, 80, 300, 20);
        preguntaPanel.add(opcionesField, BorderLayout.CENTER);

        correctaLabel = new JLabel("Respuesta correcta:");
        correctaLabel.setBounds(20, 110, 140, 20);
        preguntaPanel.add(correctaLabel, BorderLayout.CENTER);

        correctaField = new JTextField();
        correctaField.setBounds(160, 110, 240, 20);
        preguntaPanel.add(correctaField, BorderLayout.CENTER);

        add(preguntaPanel, BorderLayout.CENTER);

        // Panel para mostrar los botones de acción
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(260, 170, 100, 30);

        atrasButton = new JButton("Atrás");
        atrasButton.setBounds(20, 170, 100, 30);

        actionPanel.add(confirmarButton);
        actionPanel.add(atrasButton);

        add(actionPanel, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);

        // Ajustar tamaño al contenido
        //pack();

        // No permitir redimensionar
        //setResizable(false);
    }

    

    /**
     * Actualiza el texto del ID de la pregunta en la vista.
     */
    public void actualizarIdLabel() {
        idLabel.setText("ID de pregunta: " + (currentQuestionIndex + 1));
    }

    public InicioGUI getParentWindow() {
        return parentWindow;
    }

    public JButton getConfirmarButton() {
        return confirmarButton;
    }

    public JButton getAtrasButton() {
        return atrasButton;
    }

    public JTextField getPreguntaField() {
        return preguntaField;
    }

    public JTextField getOpcionesField() {
        return opcionesField;
    }

    public JTextField getCorrectaField() {
        return correctaField;
    }

    /**
     * Devuelve el objeto JLabel del ID de la pregunta.
     * 
     * @return el objeto JLabel del ID de la pregunta.
     */
    public JLabel getIdLabel() {
        return idLabel;
    }

    /**
     * Devuelve el índice de la pregunta actual.
     * 
     * @return el índice de la pregunta actual.
     */
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }



    /**
     * Muestra un mensaje de error en la interfaz gráfica.
     * @param mensaje El mensaje de error a mostrar.
     */
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de confirmación en la vista.
     * 
     * @param mensaje el mensaje a mostrar.
     */
    public void mostrarMensajeConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Actualiza el índice de la pregunta actual en la vista.
     * 
     * @param index el nuevo índice de la pregunta actual.
     */
    public void setCurrentQuestionIndex(int index) {
        this.currentQuestionIndex = index;
    }

    /**
     * Actualiza el campo de texto de la pregunta con la pregunta actual del modelo.
     */
    public void actualizarPreguntaField() {
        Pregunta preguntaActual = modelo.getQuestion(currentQuestionIndex);
        preguntaField.setText(preguntaActual.getPregunta());
    }

    /**
     * Actualiza el campo de texto de opciones con las opciones de la pregunta actual del modelo.
     */
    public void actualizarOpcionesField() {
        Pregunta preguntaActual = modelo.getQuestion(currentQuestionIndex);
        opcionesField.setText(String.join(",", preguntaActual.getOpciones()));
    }

    /**
     * Actualiza el campo de texto de la respuesta correcta con la respuesta correcta de la pregunta actual del modelo.
     */
    public void actualizarCorrectaField() {
        Pregunta preguntaActual = modelo.getQuestion(currentQuestionIndex);
        correctaField.setText(preguntaActual.getCorrecta());
    }
    

}
