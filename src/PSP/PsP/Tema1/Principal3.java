package PSP.PsP.Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Principal3 {

	public static void main(String[] args) {

	
		System.out.println("Vamos a crear algunos hilos de ejecucion");
		
		ArrayList<HiloEj3> tablaHilos = new ArrayList<HiloEj3>();
		for(int i=0;i<100;i++) {
			HiloEj3 h= new HiloEj3(i);
			tablaHilos.add(h);
			
		}	
		Collections.shuffle(tablaHilos);
		for(int i=0;i<100;i++) {
			tablaHilos.get(i).start();
		}
		
		

		

//		try {
//			h1.join();
//			h2.join();
//			h3.join();
//			h4.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	//	System.out.println("Fin");
		
		
	}

}



