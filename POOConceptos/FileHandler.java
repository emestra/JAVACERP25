package POOConceptos;

/**
 * @file FileHandler.java
 * @brief Clase para manejar archivos de preguntas
 * @date 29/03/2023
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
    public ConceptosList leerConceptos() throws IOException, ClassNotFoundException {
        ConceptosList listaConceptos = new ConceptosList();
        
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
        listaConceptos = (ConceptosList) objectInputStream.readObject();
        
        objectInputStream.close();
        fileInputStream.close();
        
        return listaConceptos;
    }
    
    /**
     * Escribe las preguntas en el archivo.
     * 
     * @param listaConceptos la lista de preguntas a escribir.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void escribirConceptos(ConceptosList listaConceptos) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
        objectOutputStream.writeObject(listaConceptos);
        
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
