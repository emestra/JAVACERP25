package POOConceptos;

import java.io.Serializable;

/**
 * @file ConceptosList.java
 * @brief Clase que representa una lista de preguntas para ser utilizada en un juego de preguntas y respuestas.
 */

 import java.util.ArrayList;

 /**
  * @class ConceptosList
  * @brief Clase que representa una lista de preguntas para ser utilizada en un juego de preguntas y respuestas.
  */
 public class ConceptosList implements Serializable  {
     private ArrayList<Pregunta> questionList; 
 
     /**
      * @brief Constructor para la clase ConceptosList.
      */
     public ConceptosList() {
         questionList = new ArrayList<>();
     }
 
     /**
      * @brief Método para agregar una pregunta a la lista.
      * @param q La pregunta a agregar a la lista.
      */
     public void addQuestion(Pregunta q) {
         questionList.add(q);
     }
 
     /**
      * @brief Método para eliminar una pregunta de la lista.
      * @param q La pregunta a eliminar de la lista.
      */
     public void removeQuestion(Pregunta q) {
         questionList.remove(q);
     }
 
     /**
      * @brief Método para obtener una pregunta de la lista.
      * @param index El índice de la pregunta a obtener.
      * @return La pregunta en el índice especificado.
      */
     public Pregunta getQuestion(int index) {
         return questionList.get(index);
     }
 
     /**
      * @brief Método para obtener el número de preguntas en la lista.
      * @return El número de preguntas en la lista.
      */
     public int size() {
         return questionList.size();
     }
 
     /**
      * @brief Método para obtener una pregunta aleatoria de la lista.
      * @return Una pregunta aleatoria de la lista.
      */
     public Pregunta getRandomQuestion() {
         int randomIndex = Randomizer.getRandomIndex(0, questionList.size() - 1);
         return questionList.get(randomIndex);
     }
 
     /**
      * @brief Método para verificar si una pregunta ya está en la lista.
      * @param q La pregunta a verificar.
      * @return Verdadero si la pregunta está en la lista, falso en caso contrario.
      */
     public boolean containsQuestion(Pregunta q) {
         return questionList.contains(q);
     }
 }
 