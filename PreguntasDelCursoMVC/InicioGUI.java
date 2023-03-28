package PreguntasDelCursoMVC;
import PreguntasDelCurso.PreguntasList;

import java.awt.event.*;

import javax.swing.*;

/**
 * @file InicioGUI.java
 * @brief Clase para el menú principal de la aplicación.
 * @date 20/03/2023
 * @version 1.0
 */
public class InicioGUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenuItem adminMenuItem;
    private JMenuItem preguntasMenuItem;
    //private JButton adminButton;
    //private JButton preguntasButton;
    private PreguntasList modelo;
    private FileHandler fileHandler;
    
    /**
     * Constructor para la clase MenuGUI.
     * 
     * @param modelo La lista de preguntas.
     * @param fileHandler El manejador de archivos de preguntas.
     */
    public InicioGUI(PreguntasList modelo, FileHandler fileHandler) {
        super("Menú Principal");
        this.modelo = modelo;
        this.fileHandler = fileHandler;
        
        // Crear la barra de menú y agregarla a la ventana
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Crear los elementos del menú y agregarlos directamente a la barra de menú
        adminMenuItem = new JMenuItem("Administración");
        adminMenuItem.addActionListener(this);
        menuBar.add(adminMenuItem);
        
        preguntasMenuItem = new JMenuItem("Jugar");
        preguntasMenuItem.addActionListener(this);
        menuBar.add(preguntasMenuItem);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        /* 
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
        setVisible(true);*/
    }
    
    /**
     * Método para manejar los eventos de los botones.
     * 
     * @param e El evento que ha ocurrido.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminMenuItem) {
            // Crear instancia de AdminControlador y mostrar vista AdminGUI
            AdminGUI vistaAdmin = new AdminGUI(modelo, InicioGUI.this);
            AdminControlador controladorAdmin = new AdminControlador(modelo, fileHandler, vistaAdmin);
            // Asociar el controlador como listener de los componentes de la vista
            vistaAdmin.getConfirmarButton().addActionListener(controladorAdmin);
            vistaAdmin.getAtrasButton().addActionListener(controladorAdmin);
            
            /* 
            AdminGUI adminGUI = new AdminGUI(modelo, this); // Pasar 'this' como argumento
            adminGUI.setVisible(true);*/
        } else if (e.getSource() == preguntasMenuItem) {
            PreguntaGUI preguntaGUI = new PreguntaGUI(modelo, this);
            preguntaGUI.setVisible(true);
        }
        
        dispose();
    }
}
