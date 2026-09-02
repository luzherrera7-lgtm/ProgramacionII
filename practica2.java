package prograII;

import java.util.Scanner;

public class EcuacionLineal {
    private double a, b, c, d, e, f;

    public EcuacionLineal(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public boolean tieneSolucion() {
        return (a * d - b * c) != 0;
    }

    public double getX() {
        double denominador = a * d - b * c;
        return (e * d - b * f) / denominador;
    }

    public double getY() {
        double denominador = a * d - b * c;
        return (a * f - e * c) / denominador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese a, b, c, d, e, f: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();
        double e = sc.nextDouble();
        double f = sc.nextDouble();

        EcuacionLineal ec = new EcuacionLineal(a, b, c, d, e, f);
        if (!ec.tieneSolucion()) {
            System.out.println("La ecuación no tiene solución");
        } else {
            System.out.println("x = " + ec.getX() + ", y = " + ec.getY());
        }
        sc.close();
    }
}