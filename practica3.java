package prograII;

import java.util.Scanner;

public class EcuacionCuadratica {
    private double a, b, c;

    public EcuacionCuadratica(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminante() {
        return b * b - 4 * a * c;
    }

    public double getRaiz1() {
        double disc = getDiscriminante();
        if (disc < 0) return 0;
        return (-b + Math.sqrt(disc)) / (2 * a);
    }

    public double getRaiz2() {
        double disc = getDiscriminante();
        if (disc < 0) return 0;
        return (-b - Math.sqrt(disc)) / (2 * a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese a, b, c: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        EcuacionCuadratica ec = new EcuacionCuadratica(a, b, c);
        double disc = ec.getDiscriminante();

        if (disc > 0) {
            System.out.println("La ecuación tiene dos raíces: " + ec.getRaiz1() + " y " + ec.getRaiz2());
        } else if (disc == 0) {
            System.out.println("La ecuación tiene una raíz: " + ec.getRaiz1());
        } else {
            System.out.println("La ecuación no tiene raíces reales");
        }
        sc.close();
    }
}