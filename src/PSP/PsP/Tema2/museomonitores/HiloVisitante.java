package monitores;

public class HiloVisitante extends Thread {
	Bean b;

	public HiloVisitante(Bean b) {
		super();
		this.b = b;
	}

	public void run() {
		System.out.println("Llego al museo." + Thread.currentThread().getId());

		synchronized (b) {

			if (b.numVisitantes < b.AFORO) {
				b.numVisitantes++;
				System.out.println(b.numVisitantes);
			}
			else {
				System.out.println("ME BLOQUEO." + Thread.currentThread().getId());
				try {
					b.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
			System.out.println("Visito el museo." + Thread.currentThread().getId());
			synchronized (b) {
				b.numVisitantes--;
				if (b.numVisitantes < b.AFORO) {
					System.out.println(+Thread.currentThread().getId() + " Desbloqueo un hilo");
					b.notify();
				}
			}
			System.out.println("Salgo del museo." + Thread.currentThread().getId());

		
	}

}
