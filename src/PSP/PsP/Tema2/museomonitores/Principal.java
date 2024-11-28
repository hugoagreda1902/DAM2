package monitores;

public class Principal {

	public static void main(String[] args) {
		
		Bean b=new Bean();
		HiloVisitante h1 = new HiloVisitante(b);
		HiloVisitante h2 = new HiloVisitante(b);
		HiloVisitante h3 = new HiloVisitante(b);
		HiloVisitante h4 = new HiloVisitante(b);
		HiloVisitante h5 = new HiloVisitante(b);
		HiloVisitante h6 = new HiloVisitante(b);
		HiloVisitante h7 = new HiloVisitante(b);
		HiloVisitante h8 = new HiloVisitante(b);
		HiloVisitante h9 = new HiloVisitante(b);
		HiloVisitante h10 = new HiloVisitante(b);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		h7.start();
		h8.start();
		h9.start();
		h10.start();

	}

}
