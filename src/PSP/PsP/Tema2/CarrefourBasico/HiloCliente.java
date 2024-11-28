package carrefourMonitores;

public class HiloCliente extends Thread{
	
	Bean b;
	
	
	
	public HiloCliente(Bean b) {
		super();
		this.b = b;
	}



	



	public void run() {
	
		//Entrar de 1 en 1
		//Hacer el gasto entre 1 y 100
		synchronized (b) {
			int total=(int)(Math.random()*100)+1;
			b.total+=total;
			System.out.println("Parcial:"+total);
			b.numClientesAtendidos++;
		}
		//Pagar
		
	}

}
