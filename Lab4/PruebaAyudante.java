public class PruebaAyudante {
    public static void main(String[] args) {
        Docente docente = new Docente("Ana Alba", "1234567", "Ingeniería de Software");
        Ayudante a1 = new Ayudante("Luis Mamani", "7654323", "E-003", docente);
        Estudiante e = a1;   // un Ayudante también es un Estudiante
        System.out.println(a1);
        System.out.println("Es Estudiante: " + (a1 instanceof Estudiante));
        System.out.println("Es Persona: " + (a1 instanceof Persona));
        System.out.println("Docente con quien colabora: " + a1.getDocente().getNombre());
    }
}
