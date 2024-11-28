package carrefourMonitores;

public class HiloCajera extends Thread {

	
	Bean b;
	Bean b2;
	public HiloCajera(Bean b,Bean b2) {
		this.b = b;
		this.b2 = b2;
	}
	public void run() {
		
	
		while((b.numClientesAtendidos+b2.numClientesAtendidos)<5) {
			System.out.println("Soy cajera"+Thread.currentThread().getId()+" que pase el siguiente ");
			synchronized (b) {

				try {
					int t=(int)(Math.random()*3000);
					Thread.sleep(t);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//DESBLOQUEAR MI COLA
				b.notify();
			}
				
			
		}
		
	}
	
}
