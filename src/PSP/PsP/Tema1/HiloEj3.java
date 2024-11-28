
public class HiloEj3 extends Thread{

	int num;
	
	
	
	public HiloEj3(int num) {
		super();
		this.num = num;
	}



	public void run() {
		
		System.out.println("hilo "+Thread.currentThread().getId()+"->"+num);
		
		
	}
	
}
