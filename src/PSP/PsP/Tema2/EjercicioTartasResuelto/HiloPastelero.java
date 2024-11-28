package pastelero;

import java.util.concurrent.Semaphore;

public class HiloPastelero extends Thread {
	
	Almacen a;
	Semaphore mutex;
	Semaphore esperaTarta;
	Semaphore limiteTartas;
	
	
	
	
	


	public HiloPastelero(Almacen a, Semaphore mutex, Semaphore esperaTarta, Semaphore limiteTartas) {
		super();
		this.a = a;
		this.mutex = mutex;
		this.esperaTarta = esperaTarta;
		this.limiteTartas = limiteTartas;
	}






	public void run() {
		while(true) {
			try {
				Thread.sleep((long) (Math.random()*1000));
				mutex.acquire();
				if (a.numTartas==a.MAXTARTAS) {
					System.out.println("P:"+Thread.currentThread().getId()+" Limite de tartas alcanzado: "+a.numTartas);
					mutex.release();
					limiteTartas.acquire();
				}
				else {
					a.numTartas++;
					System.out.println("P:"+Thread.currentThread().getId()+" Hago tarta "+a.numTartas);
					if(a.numTartas==1) {
						System.out.println("P:"+Thread.currentThread().getId()+" Libero al dependiente");
						esperaTarta.release();
					}	
				mutex.release();
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
		}
		//Generar tarta
		//Avisar dependiente
	}

}
