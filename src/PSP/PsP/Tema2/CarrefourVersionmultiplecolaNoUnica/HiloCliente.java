package carrefourMonitores;

public class HiloCliente extends Thread{
	
	Bean b1;
	Bean b2;
	
	
	
	public HiloCliente(Bean b1,Bean b2) {
		super();
		this.b1 = b1;
		this.b2 = b2;

	}



	



	public void run() {
	
		//Entrar de 1 en 1
		//Hacer el gasto entre 1 y 100
			int total=(int)(Math.random()*100)+1;
			System.out.println("Entra cliente");
			boolean elijo1=false;
			synchronized (b1) {
				synchronized (b2) {
					if (b1.clientesEsperando<=b2.clientesEsperando)
						elijo1=true;
				    else elijo1=false;
				}
			}
			if (elijo1)
				synchronized (b1) {
					//ELEGIR COLA PARA BLOQUEARTE
					System.out.println("Soy el cliente "+Thread.currentThread().getId()+" me bloqueo en caja1");
					b1.clientesEsperando++;
					try {
						b1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Soy el cliente "+Thread.currentThread().getId()+" voy a pagar "+total);
					b1.total+=total;
					System.out.println("Soy el cliente "+Thread.currentThread().getId()+" me voy");
					b1.numClientesAtendidos++;
					b1.clientesEsperando--;
				}
			else {
				synchronized (b2) {
					//ELEGIR COLA PARA BLOQUEARTE
					b2.clientesEsperando++;
					System.out.println("Soy el cliente "+Thread.currentThread().getId()+" me bloqueo en caja 2");
					try {
						b2.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Soy el cliente "+Thread.currentThread().getId()+" voy a pagar "+total);
					b2.total+=total;
					System.out.println("Soy el cliente "+Thread.currentThread().getId()+" me voy");
					b2.numClientesAtendidos++;
					b2.clientesEsperando--;
				}

			}
		
	}

}
