package PreguntasDelCurso;

import java.io.IOException;

/**
 * @file Main.java
 * @brief Clase principal del programa que permite crear, modificar y eliminar preguntas.
 * @date 20/03/2023
 * @version 1.0
 * @author Domingo Pérez
 */

/**
 * @class Main
 * @brief Clase principal que permite interactuar con la aplicación de preguntas.
 */
public class Main {
    
    /**
     * @brief Método principal que inicia la ejecución del programa.
     * @param args Parámetros de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        
        // Crear instancia de FileHandler para cargar las preguntas desde preguntas.txt
        FileHandler fileHandler = new FileHandler("PreguntasDelCurso\\preguntas.data");
        
        // Crear instancia de QuestionList para manejar las preguntas en memoria
        PreguntasList questionList = new PreguntasList();

        /*
        // Cargar la primera pregunta
        String questionText1 = "1|¿Cuál es el concepto fundamental de la Programación Orientada a Objetos?|Objetos|Variables, Métodos, Clases, Interfaces|1";
        Pregunta question1 = Pregunta.fromString(questionText1);
        questionList.addQuestion(question1);

        // Cargar la segunda pregunta
        String questionText2 = "2|¿Qué es una clase en Java?|Un modelo de objetos|Un tipo de dato primitivo,Un programa que ejecuta una tarea,respuesta 3, respuesta 4|1";
        Pregunta question2 = Pregunta.fromString(questionText2);
        questionList.addQuestion(question2);

        System.out.println("Número de preguntas cargadas: " + questionList.size());
        */
        
        // Cargar las preguntas desde preguntas.txt
        try {
            questionList = fileHandler.readQuestions();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Número de preguntas cargadas: " + questionList.size());

        // Crear instancia de QuestionGUI para permitir al usuario interactuar con las preguntas
        PreguntaGUI questionGUI = new PreguntaGUI(questionList);
        
        // Mostrar la interfaz gráfica
        questionGUI.setVisible(true);
        
        /* 
        // Persistir las preguntas actualizadas en preguntas.data
        try {
            fileHandler.writeQuestions(questionList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
