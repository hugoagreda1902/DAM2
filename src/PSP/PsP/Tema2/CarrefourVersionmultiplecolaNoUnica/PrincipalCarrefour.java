package carrefourMonitores;

public final class PrincipalCarrefour {

	public static void main(String[] args) {
	//Crear un programa que simule el comportamiento de un supermercado.
	//Existirán 10 hilos cliente que al supermercado de un en uno,gastarán 
	// en la compra una cantidad
	// de dinero de manera aleatoria entre 1 y 100€ y 
	// tendrán que esperar a que les
    // cobre la cajera que atiende.
	// La cajera tendra que contar el dinero que lleva.
	// La cola será tipo mercadona de tipo variable
		
	Bean b1 = new Bean();
	Bean b2 = new Bean();
	HiloCliente hc1 = new HiloCliente(b1,b2);
	HiloCliente hc2 = new HiloCliente(b1,b2);
	HiloCliente hc3 = new HiloCliente(b1,b2);
	HiloCliente hc4 = new HiloCliente(b1,b2);
	HiloCliente hc5 = new HiloCliente(b1,b2);
		
	HiloCajera cajera = new HiloCajera(b1,b2);
	HiloCajera cajera2 = new HiloCajera(b2,b1);
	
	hc1.start();
	hc2.start();
	hc3.start();
	hc4.start();
	hc5.start();
	cajera.start();
	cajera2.start();
	
	try {
		hc1.join();
		hc2.join();
		hc3.join();
		cajera.join();
		cajera2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    
	System.out.println("El total de la caja:" +(b1.total+b2.total));

	}

}
