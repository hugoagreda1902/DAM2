
public class HiloEj2 extends Thread{

String nombreHilo;




	public HiloEj2(String nombreHilo) {
	super();
	this.nombreHilo = nombreHilo;
}

	public void run() {
		
		for (int i=0;i<100;i++) 
			if(nombreHilo.equals("par")) {
				if(i%2==0)
		           System.out.println(nombreHilo+" numero "+i);
			}	
			else 	if(i%2!=0)
				   System.out.println(nombreHilo+" numero "+i);
		
	}
	
}
