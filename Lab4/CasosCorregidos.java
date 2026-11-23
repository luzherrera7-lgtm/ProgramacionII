import java.time.LocalDate;

public class CasosCorregidos {
    public static void main(String[] args) {
        Docente d1 = new Docente("Ana Alba", "1234567", "Ingeniería de Software");
        Docente d2 = new Docente("Luis Rojas", "2345678", "Bases de Datos");
        Estudiante e1 = new Estudiante("Carlos Flores", "7654321", "E-001");

        System.out.println("--- Caso 1: doble inscripción ---");
        Materia poo = new Materia("INF-121", 6);
        e1.inscribirse(poo);
        try {
            e1.inscribirse(poo);
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("Inscripciones del estudiante: " + e1.getInscripciones().size());

        System.out.println("--- Caso 2: dos docentes en un curso ---");
        Materia bd = new Materia("INF-131", 5);
        d1.dictar(bd);
        try {
            d2.dictar(bd);
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("Materias de " + d2.getNombre() + ": " + d2.getMaterias().size());

        System.out.println("--- Caso 3: nota sin registrar ---");
        Materia web = new Materia("INF-122", 6);
        Estudiante e2 = new Estudiante("Maria Salas", "7654322", "E-002");
        Inscripcion i = e2.inscribirse(web);
        try {
            i.calcularNota();
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.print(d1.generarActa(web));

        System.out.println("--- Caso 4: docente en dos carreras ---");
        Universidad umsa = new Universidad("UMSA");
        Carrera informatica = umsa.crearCarrera("Informática");
        Carrera estadistica = umsa.crearCarrera("Estadística");
        informatica.agregarDocente(d1);
        try {
            estadistica.agregarDocente(d1);
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("Docentes en Estadística: " + estadistica.getDocentes().size());

        System.out.println("--- Caso 5: composición no estricta ---");
        Materia.Horario h = poo.agregarHorario(LocalDate.of(2026, 7, 16), "Aula 102");
        System.out.println("Horario creado desde Materia: " + h.getAula());
        System.out.println("Horarios de poo: " + poo.getHorarios().size());
    }
}
