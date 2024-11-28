package pastelero;

import java.util.concurrent.Semaphore;

public class HiloDependiente extends Thread{

	
	Almacen a;
	Semaphore mutex;
	Semaphore esperaTarta;
	Semaphore limiteTartas;
	
	




	public HiloDependiente(Almacen a, Semaphore mutex, Semaphore esperaTarta, Semaphore limiteTartas) {
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
					System.out.println("D:"+Thread.currentThread().getId()+" Desbloqueo pastelero");
					limiteTartas.release();
				}
			    if(a.numTartas==0) {
					 System.out.println("D:"+Thread.currentThread().getId()+" No hay tartas");
					 mutex.release();
					 esperaTarta.acquire();
					}
				 	else {
				 		a.numTartas--;
				 		System.out.println("D:"+Thread.currentThread().getId()+" Tarta servida quedan"+a.numTartas);
				 		mutex.release();
				 }
				 
				 
			     
				 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}

		
	}
	
	
}
