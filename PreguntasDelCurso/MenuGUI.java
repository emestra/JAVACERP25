package PreguntasDelCurso;

import java.awt.event.*;
import javax.swing.*;

/**
 * @file MenuGUI.java
 * @brief Clase para el menú principal de la aplicación.
 * @date 20/03/2023
 * @version 1.0
 */
public class MenuGUI extends JFrame implements ActionListener {
    
    private JButton adminButton;
    private JButton preguntasButton;
    private PreguntasList questionList;
    private FileHandler fileHandler;
    
    /**
     * Constructor para la clase MenuGUI.
     * 
     * @param questionList La lista de preguntas.
     * @param fileHandler El manejador de archivos de preguntas.
     */
    public MenuGUI(PreguntasList questionList, FileHandler fileHandler) {
        super("Menú Principal");
        this.questionList = questionList;
        this.fileHandler = fileHandler;
        
        adminButton = new JButton("Administración");
        adminButton.addActionListener(this);
        
        preguntasButton = new JButton("Jugar");
        preguntasButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.add(adminButton);
        panel.add(preguntasButton);
        
        getContentPane().add(panel);
        
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Método para manejar los eventos de los botones.
     * 
     * @param e El evento que ha ocurrido.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            new AdminGUI(questionList, fileHandler);
        } else if (e.getSource() == preguntasButton) {
            new PreguntaGUI(questionList);
        }
        
        dispose();
    }
}
