package galletas;


public class HiloMonstruo extends Thread{

	BeanAlmacen ba;
	int galletasMonstruo;
	
	
	public HiloMonstruo(BeanAlmacen ba) {
		super();
		this.ba = ba;
		galletasMonstruo=(int)(Math.random()*10)+1;
	}



	public void run() {
		
		
			while(ba.contador<10) {
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				synchronized (ba) {	
				if (ba.numGalletas==0)
					try {
						ba.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				else { 
				
				if (ba.numGalletas<=galletasMonstruo) { 
						System.out.println(Thread.currentThread().getId()+" numG "+ba.numGalletas+" quiero comer "+galletasMonstruo+ " me como lo que haya");
						ba.numGalletas=0;
				  		ba.notify();
				}
			  
			  
			  else if (ba.numGalletas>galletasMonstruo) { //{ synchronized (ba) {
				      System.out.println(Thread.currentThread().getId()+" numG "+ba.numGalletas+" como "+galletasMonstruo);
					  ba.numGalletas-=galletasMonstruo;
			  		 }
			else
				try {
					ba.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
				
			  		//}
			}		  
			  
			}
			while (ba.numGalletas>0) {
				synchronized (ba) {
					System.out.println("Comida extra");
					if(ba.numGalletas>galletasMonstruo)
						ba.numGalletas-=galletasMonstruo;
					else ba.numGalletas=0;
				}
			}
			
			
			System.out.println("Salgo del almacen "+Thread.currentThread().getId()+" galletas "+ba.numGalletas);
		}
		
		
	
	
	
}
