package SaludoAleman;

public class SaludoPrincipal {

/*En alemania los alumnos muestran absoluto silencio y respeto hasta que 
 * el profesor saluda a la clase "Guten Morgen die Klasse!".A continuación
 * los alumnos saludarán al profesor diciendo sus respectivos nombres
 * "Morgen Ich bin NombreDelAlumno".Representa el comportamiento con 4 hilos alumno
 * y uno profesor.	
 */
	
	public static void main(String[] args) {

		AlumnosBloqueados a = new AlumnosBloqueados(0);
		
		ThreadAlumno t1 = new ThreadAlumno(a);
		ThreadAlumno t2 = new ThreadAlumno(a);
		ThreadAlumno t3 = new ThreadAlumno(a);
		ThreadAlumno t4 = new ThreadAlumno(a);
		
		ThreadProfesor tp = new ThreadProfesor(a);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		tp.start();
		
	}

}
