package app;

public class Menu {
	//TODO: Crear la clase Menu completa, con la documentación 
	//  (fijaros en la clase Consola, en el UML y en el código que ya tenéis y estamos refactorizando).
	
	private Consola consola;
	
	//Crea un Menu que usará consola para mostrarse
	public Menu(Consola consola) {
		this.consola= consola;
	}
	
	//Muestra el menu
	public void mostrar() {
		System.out.println("\n====== AGENDA (Consola) ======");
        System.out.println("1) Añadir contacto");
        System.out.println("2) Listar contactos");
        System.out.println("3) Buscar contacto");
        System.out.println("4) Borrar contacto por ID");
        System.out.println("5) Añadir teléfono a contacto");
        System.out.println("0) Salir");
        System.out.println("==============================");
	}
	
	//Lee la opcion elegida por el usuario
	public int leerOpcion() {
		int opcion;

	    opcion = consola.leerEnteroRango("Escoja su opción: ", 0, 5);

	    return opcion;
	}
}
