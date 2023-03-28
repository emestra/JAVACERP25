package PreguntasDelCursoMVC;
import PreguntasDelCurso.PreguntasList;

/**
 * @file FileHandler.java
 * @brief Clase para manejar archivos de preguntas
 * @date 20/03/2023
 * @version 1.0
 * @author Domingo Pérez
 */

 import java.io.*;
 
 /**
  * @class FileHandler
  * @brief Clase para manejar archivos de preguntas
  */
public class FileHandler {
    
    private String filename;
    
    /**
     * Constructor de la clase FileHandler.
     * 
     * @param filename nombre del archivo a manejar.
     */
    public FileHandler(String filename) {
        this.filename = filename;
    }
    
    /**
     * Lee las preguntas desde el archivo.
     * 
     * @return una lista de preguntas.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws ClassNotFoundException si la clase de algún objeto serializado no puede ser encontrada.
     */
    public PreguntasList readQuestions() throws IOException, ClassNotFoundException {
        PreguntasList questionList = new PreguntasList();
        
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
        questionList = (PreguntasList) objectInputStream.readObject();
        
        objectInputStream.close();
        fileInputStream.close();
        
        return questionList;
    }
    
    /**
     * Escribe las preguntas en el archivo.
     * 
     * @param questionList la lista de preguntas a escribir.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void writeQuestions(PreguntasList questionList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
        objectOutputStream.writeObject(questionList);
        
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Limpia el contenido del archivo especificado.
     * 
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void limpiarArchivo() throws IOException {
        FileWriter fileWriter = new FileWriter(filename, false);
        fileWriter.write("");
        fileWriter.close();
    }

}
