package PSP.PsP.Tema1;

import java.util.ArrayList;

public class Principal1 {

	public static void main(String[] args) {

	
		System.out.println("Comienza el GP");

			HiloEj1 h1= new HiloEj1("Hamilton");
			HiloEj1 h2= new HiloEj1("Verstapen");
			HiloEj1 h3= new HiloEj1("Pérez");
			HiloEj1 h4= new HiloEj1("Alonso");
			HiloEj1 h5= new HiloEj1("Sainz");
			HiloEj1 h6= new HiloEj1("Stroll");
			HiloEj1 h7= new HiloEj1("Ocón");
			
			h1.start();
			h2.start();
			h3.start();
			h4.start();
			h5.start();
			h6.start();
			h7.start();

		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
			h5.join();
			h6.join();
			h7.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ha terminado la carrera");
		
		
	}

}



