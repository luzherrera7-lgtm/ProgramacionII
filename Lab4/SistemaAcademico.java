import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


abstract class Persona {
    private final String nombre;
    private final String ci;

    protected Persona(String nombre, String ci) {
        this.nombre = nombre;
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCi() {
        return ci;
    }

    public abstract String toString();
}

class Estudiante extends Persona {
    private final String codigo;
    // Asociación: el estudiante conoce sus inscripciones
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Estudiante(String nombre, String ci, String codigo) {
        super(nombre, ci);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Inscripcion inscribirse(Materia materia) {
        // Corrección caso 1: no se permite inscribirse dos veces en la misma materia
        for (Inscripcion i : inscripciones) {
            if (i.getMateria() == materia) {
                throw new IllegalStateException("El estudiante ya está inscrito en " + materia.getCodigo());
            }
        }
        Inscripcion inscripcion = new Inscripcion(this, materia, LocalDate.now());
        inscripciones.add(inscripcion);
        materia.registrarInscripcion(inscripcion);
        return inscripcion;
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    @Override
    public String toString() {
        return "Estudiante " + getNombre() + " (código " + codigo + ")";
    }
}

class Docente extends Persona {
    private final String especialidad;
    // Asociación: el docente conoce las materias que dicta
    private final List<Materia> materias = new ArrayList<>();
    // Corrección caso 4: el docente conoce su carrera (como máximo una)
    private Carrera carrera;

    public Docente(String nombre, String ci, String especialidad) {
        super(nombre, ci);
        this.especialidad = especialidad;
    }

    public void dictar(Materia materia) {
        // Corrección caso 2: una materia tiene un solo docente
        if (materia.getDocente() != null) {
            throw new IllegalStateException("La materia " + materia.getCodigo() + " ya tiene docente: "
                    + materia.getDocente().getNombre());
        }
        materias.add(materia);
        materia.setDocente(this);
    }

    // Dependencia: Acta se crea y se devuelve, pero NO se guarda como atributo
    public Acta generarActa(Materia materia) {
        return new Acta(materia);
    }

    void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public List<Materia> getMaterias() {
        return Collections.unmodifiableList(materias);
    }

    @Override
    public String toString() {
        return "Docente " + getNombre() + " (" + especialidad + ")";
    }
}


class Ayudante extends Estudiante {
    private final Docente docente;

    public Ayudante(String nombre, String ci, String codigo, Docente docente) {
        super(nombre, ci, codigo);
        this.docente = docente;
    }

    public Docente getDocente() {
        return docente;
    }

    @Override
    public String toString() {
        return "Ayudante " + getNombre() + " (código " + getCodigo() + ") colabora con " + docente.getNombre();
    }
}


class Universidad {
    private final String nombre;
    private final List<Carrera> carreras = new ArrayList<>();

    public Universidad(String nombre) {
        this.nombre = nombre;
    }

    // La Universidad CREA a las partes (carreras: no existen fuera de la Universidad)
    public Carrera crearCarrera(String nombreCarrera) {
        Carrera d = new Carrera(nombreCarrera);
        carreras.add(d);
        return d;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carrera> getCarreras() {
        return Collections.unmodifiableList(carreras);
    }
}


class Carrera {
    private final String nombre;
    private final List<Docente> docentes = new ArrayList<>();

    Carrera(String nombre) {
        this.nombre = nombre;
    }

   
    public void agregarDocente(Docente docente) {
        if (docente.getCarrera() != null) {
            throw new IllegalStateException(docente.getNombre() + " ya pertenece a la carrera "
                    + docente.getCarrera().getNombre());
        }
        docentes.add(docente);
        docente.setCarrera(this);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Docente> getDocentes() {
        return Collections.unmodifiableList(docentes);
    }
}


class Materia {
    private final String codigo;
    private final int creditos;
    private Docente docente;
    private final List<Horario> horarios = new ArrayList<>();
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Materia(String codigo, int creditos) {
        this.codigo = codigo;
        this.creditos = creditos;
    }

    
    public static class Horario {
        private final LocalDate fecha;
        private final String aula;

        private Horario(LocalDate fecha, String aula) {
            this.fecha = fecha;
            this.aula = aula;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public String getAula() {
            return aula;
        }
    }

    public Horario agregarHorario(LocalDate fecha, String aula) {
        Horario s = new Horario(fecha, aula);
        horarios.add(s);
        return s;
    }

    void registrarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public Docente getDocente() {
        return docente;
    }

    public List<Horario> getHorarios() {
        return Collections.unmodifiableList(horarios);
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }
}


interface Calificable {
    double calcularNota();
}

class Inscripcion implements Calificable {
    private final Estudiante estudiante;
    private final Materia materia;
    private final LocalDate fecha;
    private double nota;
    private boolean notaRegistrada = false;

    Inscripcion(Estudiante estudiante, Materia materia, LocalDate fecha) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.fecha = fecha;
    }

    public void registrarNota(double nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
        }
        this.nota = nota;
        this.notaRegistrada = true;
    }

    public boolean tieneNota() {
        return notaRegistrada;
    }

    @Override
    public double calcularNota() {
        if (!notaRegistrada) {
            throw new IllegalStateException("La inscripción aún no tiene nota registrada");
        }
        return nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}


class Acta {
    private final LocalDate fecha = LocalDate.now();
    private final String contenido;

    Acta(Materia materia) {
        StringBuilder sb = new StringBuilder();
        sb.append("ACTA ").append(materia.getCodigo()).append(" - ").append(fecha).append('\n');
        for (Inscripcion i : materia.getInscripciones()) {
            sb.append("  ").append(i.getEstudiante().getNombre()).append(": ").append(i.tieneNota() ? String.valueOf(i.calcularNota()) : "sin nota").append('\n');
        }
        this.contenido = sb.toString();
    }

    @Override
    public String toString() {
        return contenido;
    }
}


public class SistemaAcademico {
    public static void main(String[] args) {
        
        Universidad umsa = new Universidad("UMSA");
        Carrera informatica = umsa.crearCarrera("Informática");

        
        Docente docente = new Docente("Ana Alba", "1234567", "Ingeniería de Software");
        informatica.agregarDocente(docente);

        
        Materia poo = new Materia("INF-121", 6);
        docente.dictar(poo);

        
        poo.agregarHorario(LocalDate.of(2026, 07, 2), "Aula 101");
        poo.agregarHorario(LocalDate.of(2026, 07, 9), "Aula 101");

        
        Estudiante e1 = new Estudiante("Carlos Flores", "7654321", "E-001");
        Estudiante e2 = new Estudiante("Maria Salas", "7654322", "E-002");
        Inscripcion i1 = e1.inscribirse(poo);
        Inscripcion i2 = e2.inscribirse(poo);
        i1.registrarNota(85);
        i2.registrarNota(92);

        
        Ayudante a1 = new Ayudante("Luis Mamani", "7654323", "E-003", docente);

        
        List<Persona> personas = Arrays.asList(docente, e1, e2, a1);
        personas.forEach(p -> System.out.println(p));

        
        Calificable c = i1;
        System.out.println("Nota de " + e1.getNombre() + ": " + c.calcularNota());

        
        Acta acta = docente.generarActa(poo);
        System.out.println(acta);
    }
}
