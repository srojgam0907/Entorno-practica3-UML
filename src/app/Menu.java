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
		consola.escribirLinea("\n====== AGENDA (Consola) ======");
		consola.escribirLinea("1) Añadir contacto");
		consola.escribirLinea("2) Listar contactos");
		consola.escribirLinea("3) Buscar contacto");
		consola.escribirLinea("4) Borrar contacto por ID");
		consola.escribirLinea("5) Añadir teléfono a contacto");
		consola.escribirLinea("0) Salir");
		consola.escribirLinea("=============================="); 
	}
	
	//Lee la opcion elegida por el usuario
	public int leerOpcion() {
		int opcion;

	    opcion = consola.leerEnteroRango("Escoja su opción: ", 0, 5);

	    return opcion;
	}
}
