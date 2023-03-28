package PreguntasDelCursoMVC;
import PreguntasDelCurso.PreguntasList;
import PreguntasDelCurso.Pregunta;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Clase para representar el controlador de la interfaz gráfica de administración de preguntas.
 */
public class AdminControlador implements ActionListener {

    private PreguntasList modelo;
    private FileHandler fileHandler;
    private AdminGUI vista;

    public AdminControlador(PreguntasList modelo, FileHandler fileHandler, AdminGUI vista) {
        this.modelo = modelo;
        this.fileHandler = fileHandler;
        this.vista = vista;

        vista.getConfirmarButton().addActionListener(this);
        vista.getAtrasButton().addActionListener(this);

        // Cargar la primera pregunta en la vista
        this.vista.actualizarPreguntaField();
        this.vista.actualizarOpcionesField();
        this.vista.actualizarCorrectaField();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getConfirmarButton()) {
            if (vista.getPreguntaField().getText().isEmpty() || vista.getOpcionesField().getText().isEmpty() || vista.getCorrectaField().getText().isEmpty()) {
                vista.mostrarMensajeError("Debe completar todos los campos.");
            } else {
                String pregunta = vista.getPreguntaField().getText();
                String opcionesString = vista.getOpcionesField().getText();
                String correcta = vista.getCorrectaField().getText();

                String[] opciones = opcionesString.split(",");
                Pregunta nuevaPregunta = new Pregunta(modelo.size() + 1, pregunta, correcta, opciones);

                modelo.addQuestion(nuevaPregunta);
                try {
                    fileHandler.writeQuestions(modelo);
                } catch (IOException ex) {
                    vista.mostrarMensajeError("Ocurrió un error al escribir en el archivo: " + ex.getMessage());
                }

                vista.mostrarMensajeConfirmacion("Pregunta agregada correctamente.");

                vista.setCurrentQuestionIndex(vista.getCurrentQuestionIndex() + 1);
                vista.getIdLabel().setText("ID de pregunta: " + (modelo.size() + 1));

                vista.getPreguntaField().setText("");
                vista.getOpcionesField().setText("");
                vista.getCorrectaField().setText("");
            }
        } else if (e.getSource() == vista.getAtrasButton()) {
            vista.getParentWindow().setVisible(true);
            vista.dispose();
        }
    }
}
