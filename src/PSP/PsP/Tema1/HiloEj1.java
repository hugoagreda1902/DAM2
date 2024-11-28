
public class HiloEj1 extends Thread{

	String piloto;
	
	
	public HiloEj1(String piloto) {
		super();
		this.piloto = piloto;
	}


	public void run() {
		for (int i=0;i<10;i++) {
		System.out.println(this.piloto+" vuelta "+(i+1));
		int tiempo=(int) (Math.random()*4);
		try {
			Thread.sleep((1000*tiempo));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		System.out.println(this.piloto+" ha terminado el circuito");
	}
	
}
