package prograII;

import java.util.Scanner;

public class Estadistica {
        private double[] numeros;

    public Estadistica(double[] datos) {
        this.numeros = datos;
    }

    public double promedio() {
        double suma = 0;
        for (double n : numeros) suma += n;
        return suma / numeros.length;
    }

    public double desviacion() {
        double prom = promedio();
        double suma = 0;
        for (double n : numeros) {
            suma += Math.pow(n - prom, 2);
        }
        return Math.sqrt(suma / (numeros.length - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] datos = new double[10];
        System.out.print("Ingrese 10 números: ");
        for (int i = 0; i < 10; i++) {
            datos[i] = sc.nextDouble();
        }

        Estadistica est = new Estadistica(datos);
        System.out.println("El promedio es " + est.promedio());
        System.out.println("La desviación estándar es " + est.desviacion());
        sc.close();
    }
}