import java.time.LocalDate;

public class Casos {
    public static void main(String[] args) {
        Docente d1 = new Docente("Ana Alba", "1234567", "Ingeniería de Software");
        Docente d2 = new Docente("Luis Rojas", "2345678", "Bases de Datos");
        Estudiante e1 = new Estudiante("Carlos Flores", "7654321", "E-001");

        // Caso 1: doble inscripción
        System.out.println("--- Caso 1: doble inscripción ---");
        Materia poo = new Materia("INF-121", 6);
        e1.inscribirse(poo);
        e1.inscribirse(poo);
        System.out.println("Inscripciones del estudiante: " + e1.getInscripciones().size());
        System.out.println("Inscripciones en la materia: " + poo.getInscripciones().size());

        // Caso 2: dos docentes en una materia
        System.out.println("--- Caso 2: dos docentes en un curso ---");
        Materia bd = new Materia("INF-131", 5);
        d1.dictar(bd);
        d2.dictar(bd);
        System.out.println("Docente de la materia: " + bd.getDocente().getNombre());
        System.out.println("Materias de " + d1.getNombre() + ": " + d1.getMaterias().size());
        System.out.println("Materias de " + d2.getNombre() + ": " + d2.getMaterias().size());

        // Caso 3: nota sin registrar
        System.out.println("--- Caso 3: nota sin registrar ---");
        Materia web = new Materia("INF-122", 6);
        Estudiante e2 = new Estudiante("Maria Salas", "7654322", "E-002");
        Inscripcion i = e2.inscribirse(web);
        System.out.println("calcularNota() sin registrar: " + i.calcularNota());
        System.out.print(d1.generarActa(web));

        // Caso 4: docente en dos carreras
        System.out.println("--- Caso 4: docente en dos carreras ---");
        Universidad umsa = new Universidad("UMSA");
        Carrera informatica = umsa.crearCarrera("Informática");
        Carrera estadistica = umsa.crearCarrera("Estadística");
        informatica.agregarDocente(d1);
        estadistica.agregarDocente(d1);
        System.out.println("Docentes en Informática: " + informatica.getDocentes().size());
        System.out.println("Docentes en Estadística: " + estadistica.getDocentes().size());

        // Caso 5: composición no estricta
        System.out.println("--- Caso 5: composición no estricta ---");
        Horario suelto = new Horario(LocalDate.of(2026, 7, 16), "Aula 102");
        System.out.println("Horario creado fuera de Materia: " + suelto.getAula());
        System.out.println("Horarios de poo: " + poo.getHorarios().size());
    }
}
