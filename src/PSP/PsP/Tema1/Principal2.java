package PSP.PsP.Tema1;

import java.util.ArrayList;

public class Principal2 {

	public static void main(String[] args) {

	
		System.out.println("Ejercicio Par Impar");

			HiloEj2 h1= new HiloEj2("par");
			HiloEj2 h2= new HiloEj2("impar");
			
			h1.start();
			h2.start();


		try {
			h1.join();
			h2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("Ha terminado la carrera");
		
		
	}

}



