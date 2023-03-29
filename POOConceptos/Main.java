package POOConceptos;

import java.io.IOException;

/**
 * @file Main.java
 * @brief Clase principal del programa que permite crear, modificar y eliminar conceptos.
 * @date 29/03/2023
 * @version 1.0
 * @author Domingo Pérez
 */

/**
 * @class Main
 * @brief Clase principal que permite interactuar con la aplicación de conceptos.
 */
public class Main {
    
    /**
     * @brief Método principal que inicia la ejecución del programa.
     * @param args Parámetros de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        
        // Crear instancia de FileHandler para cargar los conceptos desde conceptos.data
        FileHandler fileHandler = new FileHandler("POOConceptos\\conceptos.data");
        
        // Crear instancia de ConceptosList para manejar los conceptos en memoria
        ConceptosList listaConceptos = new ConceptosList();

        /*// Limpio el archivo
        try {
            fileHandler.limpiarArchivo();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        /* 
        // Cargar la primera pregunta
        String questionText1 = "1|¿Cuál es el concepto fundamental de la Programación Orientada a Objetos?|Objetos|Variables, Métodos, Clases, Interfaces|1";
        Pregunta question1 = Pregunta.fromString(questionText1);
        listaConceptos.addQuestion(question1);*/        
        
        // Cargar las preguntas desde preguntas.txt
        try {
            listaConceptos = fileHandler.leerConceptos();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Número de preguntas cargadas: " + listaConceptos.size());

         // Inicia la ventana de inicio de la aplicación
        InicioGUI inicioGUI = new InicioGUI(listaConceptos, fileHandler);
        inicioGUI.setVisible(true);
    }
}
