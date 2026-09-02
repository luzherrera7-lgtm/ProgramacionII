package Ejercicio01;


/**
* Clase Cronometro.
*
* @author Natalia Tambo
* @version 1.0 01/09/2026
*/
public class Cronometro {
   /* Hora de inicio en milisegundos */
   private long inicia;
  
   /* Hora de finalizacion en milisegundos */
   private long finaliza;
  
   /* Construye un objeto cronómetro e inicializa inicia con la hora actual */
   public Cronometro() {
       this.inicia = System.currentTimeMillis();
   }
  
   /* Retorna el valor de inicia */
   public long getInicia() {
       return this.inicia;
   }
  
   /* Retorna el valor de finaliza */
   public long getFinaliza() {
       return this.finaliza;
   }
  
   /* Restablece inicia a la hora actual */
   public void inicia() {
       this.inicia = System.currentTimeMillis();
   }
  
   /* Establece finaliza a la hora actual */
   public void detener() {
       this.finaliza = System.currentTimeMillis();
   }
  
   /* Retorna el tiempo transcurrido del cronómetro en milisegundos */
   public long lapsoDeTiempo() {
       return this.finaliza - this.inicia;
   }
}


TestCronometro.java
package Ejercicio01;


/**
* TestCronometro.java
* Clase principal. Cuyo objetivo es probar la ejecución de la clase Cronometro.
*
* @author Natalia Tambo
* @version 1.0 01/09/2026
*/
public class TestCronometro {
   /** Método Principal */
   public static void main(String[] args) {
       // Crea un cronómetro
       Cronometro cronometro = new Cronometro();
      
       // Arreglo de 100.000 números
       int[] numeros = new int[100000];
       for(int i = 0; i < numeros.length; i++) {
           numeros[i] = (int)(Math.random() * 100000);
       }
      
       cronometro.inicia();
      
       // Ordenación por selección
       for (int i = 0; i < numeros.length - 1; i++) {
           int min = i;
           for (int j = i + 1; j < numeros.length; j++) {
               if (numeros[j] < numeros[min]) {
                   min = j;
               }
           }