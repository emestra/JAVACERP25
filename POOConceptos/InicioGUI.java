package POOConceptos;

import java.awt.event.*;
import javax.swing.*;

/**
 * @file InicioGUI.java
 * @brief Clase para el menú principal de la aplicación.
 * @date 29/03/2023
 * @version 1.0
 */
public class InicioGUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenuItem adminMenuItem;
    private JMenuItem userMenuItem;
    private ConceptosList listaConceptos;
    private FileHandler fileHandler;
    
    /**
     * Constructor para la clase InicioGUI.
     * 
     * @param listaConceptos La lista de conceptos.
     * @param fileHandler El manejador de archivos de conceptos.
     */
    public InicioGUI(ConceptosList listaConceptos, FileHandler fileHandler) {
        super("Menú Principal");
        this.listaConceptos = listaConceptos;
        this.fileHandler = fileHandler;
        
        // Crear la barra de menú y agregarla a la ventana
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Crear los elementos del menú y agregarlos directamente a la barra de menú
        adminMenuItem = new JMenuItem("Administración");
        adminMenuItem.addActionListener(this);
        menuBar.add(adminMenuItem);
        
        userMenuItem = new JMenuItem("Jugar");
        userMenuItem.addActionListener(this);
        menuBar.add(userMenuItem);
        
        setSize(800, 600);
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
        if (e.getSource() == adminMenuItem) {
            AdminGUI adminGUI = new AdminGUI(listaConceptos, fileHandler, this); // Pasar 'this' como argumento
            adminGUI.setVisible(true);
        } else if (e.getSource() == userMenuItem) {
            UserGUI preguntaGUI = new UserGUI(listaConceptos, this);
            preguntaGUI.setVisible(true);
        }
        
        dispose();
    }
}
