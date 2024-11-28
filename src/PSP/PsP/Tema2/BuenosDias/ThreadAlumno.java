package SaludoAleman;

public class ThreadAlumno extends Thread {

	
	AlumnosBloqueados a;
	
	
	
	
	public ThreadAlumno(AlumnosBloqueados a) {
		this.a = a;
	}




	public void run() {
		
		synchronized (a) {
			a.numAlumnos++;
				System.out.println("me bloqueo"+this.getName());
			  try {
				a.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		System.out.println("Buenos dias profesor soy "+this.getName());
		}
		
	}
	
}
