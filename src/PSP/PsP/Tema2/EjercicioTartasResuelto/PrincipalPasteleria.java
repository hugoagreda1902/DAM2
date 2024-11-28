package pastelero;

import java.util.concurrent.Semaphore;

public class PrincipalPasteleria {

	public static void main(String[] args) {

		Almacen a=new Almacen();
		Semaphore mutex=new Semaphore(1);
		Semaphore esperaTarta= new Semaphore(0);
		Semaphore limiteTartas= new Semaphore(0);
		HiloPastelero hp=new HiloPastelero(a,mutex,esperaTarta,limiteTartas);
		HiloPastelero hp2=new HiloPastelero(a,mutex,esperaTarta,limiteTartas);
		HiloPastelero hp3=new HiloPastelero(a,mutex,esperaTarta,limiteTartas);
		HiloDependiente hd= new HiloDependiente(a,mutex,esperaTarta,limiteTartas);
		HiloDependiente hd2= new HiloDependiente(a,mutex,esperaTarta,limiteTartas);

		hp.start();
		hp2.start();
		hp3.start();
		hd.start();
		hd2.start();
		

	}

}
