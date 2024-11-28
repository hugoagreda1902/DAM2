package galletas;

public class HiloAlmacenero extends Thread{

	BeanAlmacen ba;
	
	public HiloAlmacenero(BeanAlmacen ba) {
		super();
		this.ba = ba;
	}

	public void run() {
		
			while(ba.contador<10) {
//			try {
//					Thread.sleep(2000);
//			} catch (InterruptedException e) {
//					e.printStackTrace();
//			}
			synchronized (ba) {
			 if(ba.numGalletas==0) {
				 	System.out.println("Repongo galletas "+ba.contador);
					 ba.numGalletas=10;
					 ba.contador++;

					 ba.notifyAll();
					 
			 } else
				try {
					ba.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			}
			
	}

}
