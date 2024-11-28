package SaludoAleman;

public class ThreadProfesor extends Thread {

	
	AlumnosBloqueados a;

	public ThreadProfesor(AlumnosBloqueados a) {
		this.a = a;
	}
	
	public void run () {
	
		while(a.activo) {
		synchronized (a) {
				if (a.numAlumnos==4) {
			System.out.println("Buenos dias alumnos");
			a.notifyAll();
			a.activo=false;
				}
			}
		}
		
	}
	
	
	
	
}
