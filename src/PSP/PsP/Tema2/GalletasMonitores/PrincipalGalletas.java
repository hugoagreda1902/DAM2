package galletas;

public class PrincipalGalletas {
/*En un almacén hay inicialmente 10 galletas:
Existen dos tipos de hilos, los monstruos de las galletas y el almacenero.
Creamos 3 monstruos y un almacenero.
Tanto los monstruos como el almacenero entran en el almacén cuando
no hay nadie, es decir, entran de uno en uno.
Un monstruo come un número de galletas aleatorio, entre 1 y 10 (pero
para cada monstruo, el número es fijo). Si el número es mayor que las
galletas que quedan se comerá todas las que estén disponibles.
El almacenero entra al almacén y si no quedan galletas repone otras 10.
Para ver el proceso, el almacenero y los monstruos, irán imprimiendo
mensajes de lo que van haciendo.
El programa termina cuando el almacenero repone 10 veces las galletas y
se han comido todas.*/
	
	
	public static void main(String[] args) {
	
		BeanAlmacen ba = new BeanAlmacen();
		
		HiloMonstruo hm1 = new HiloMonstruo(ba);
		HiloMonstruo hm2 = new HiloMonstruo(ba);
		HiloMonstruo hm3 = new HiloMonstruo(ba);
		
		HiloAlmacenero ha = new HiloAlmacenero(ba);
//		HiloAlmacenero ha2 = new HiloAlmacenero(ba);

		hm1.start();
		hm2.start();
		hm3.start();
		
		ha.start();
//		ha2.start();

	}

}
