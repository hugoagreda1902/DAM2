package carrefourMonitores;

public class HiloCajera extends Thread {

	
	Bean b;
	
	
	
	public HiloCajera(Bean b) {
		this.b = b;
	}
	public void run() {
		
		while(b.numClientesAtendidos<3) {

			System.out.println("Atiendo a un cliente");
		}
		
	}
	
}
