package app;

import java.util.List;
import dominio.Agenda;
import dominio.Contacto;
import dominio.TipoTelefono;
import dominio.TipoVia;

//TODO: Crea todos los atributos y métodos de la clase en base al UML, la documentación y el código que ya tenéis.

/**
* Clase de la capa de aplicación que gestiona las acciones de la Agenda desde consola.
*/
public class GestorAgenda {

	private Consola consola= new Consola();
	private Agenda agenda= new Agenda();
	
  /**
   * Crea un gestor de agenda que utilizará una consola para interactuar con el usuario
   * y una agenda para realizar las operaciones del dominio.
   *
   * @param consola Consola utilizada para leer y escribir información.
   * @param agenda  Agenda sobre la que se realizan las operaciones.
   */
	public GestorAgenda(Consola consola, Agenda agenda) {
		this.consola= consola;
		this.agenda= agenda;
	}

  /**
   * Solicita al usuario los datos de un nuevo contacto, lo crea en la agenda y
   * permite definir su dirección y sus teléfonos.
   */
	public void agregarContacto(Consola consola, Agenda agenda) {
		String nombre = consola.leerTextoNoVacio("Nombre: "); 
        String apellidos = consola.leerTextoNoVacio("Apellidos: ");
        String email = consola.leerTexto("Email (opcional): "); 

        Contacto nuevoContacto = agenda.crearContacto(nombre, apellidos, email);
    	crearDireccion(nuevoContacto);
        crearTelefonos(nuevoContacto);
	}

  /**
   * Pide al usuario los datos de una dirección y la asigna al contacto indicado.
   *
   * @param contacto Contacto al que se le asignará la dirección.
   */
	private void crearDireccion(Contacto contacto) {
		 consola.escribirLinea("--- Dirección ---"); 
	     TipoVia tipoVia = elegirTipoVia();
	     int numero = consola.leerEntero("Número: ");
	     String bloque = consola.leerTexto("Bloque (opcional): ");
	     String escalera = consola.leerTexto("Escalera (opcional): ");
	     String portal = consola.leerTexto("Portal (opcional): ");
	     String letra = consola.leerTexto("Letra (opcional): "); 
	     contacto.definirDireccion(tipoVia, numero, bloque, escalera, portal, letra);
	} 

  /**
   * Pregunta cuántos teléfonos se desean añadir y solicita sus datos uno a uno,
   * agregándolos al contacto indicado.
   *
   * @param contacto Contacto al que se le añadirán los teléfonos.
   */
	private void crearTelefonos(Contacto contacto) {
		int cuantos = consola.leerEntero("¿Cuántos teléfonos quieres añadir ahora? (0..n): ");
        int i = 0;
        while (i < cuantos) {
            consola.escribirLinea("\n--- Teléfono ---");
            String numTel = consola.leerTextoNoVacio("Número de teléfono: ");
            TipoTelefono tipoTel = elegirTipoTelefono();
            contacto.agregarTelefono(numTel, tipoTel);
            i++;
        }  
	}

  /**
   * Muestra por consola todos los contactos de la agenda.
   * Si la agenda está vacía, muestra un mensaje informativo.
   */
	public void listarContactos() {
		List<Contacto> contactos= agenda.listarContactos();
		
		if(contactos.isEmpty()) {
			consola.escribirLinea("Agenda vacia. No se pueden mostrar los contactos");
		
		} else {
			consola.escribirLinea("---Lista de contactos---");
			
			for(Contacto contacto:contactos) {
				consola.escribirLinea(contacto.toString());
			}
		}
	}

  /**
   * Solicita un texto de búsqueda y muestra los contactos cuyo nombre o apellidos
   * coincidan con el texto indicado.
   */
	public void buscarContactos() { 
		String texto = consola.leerTextoNoVacio("Introduce el nombre y los apellidos de quien quiere buscar: ");

	    List<Contacto> resultados = agenda.buscarPorNombre(texto);

	    if (resultados.isEmpty()) {
	        consola.escribirLinea("No se han encontrado contactos que coincidan con el texto indicado.");
	    } else {
	        consola.escribirLinea("Contactos encontrados:");
	        for (Contacto contacto : resultados) {
	            consola.escribirLinea(contacto.toString());
	        }
	    }
	}

  /**
   * Solicita el ID de un contacto y, si existe, lo elimina de la agenda.
   * Muestra por consola si la operación ha tenido éxito o no.
   */
	public void borrarContactos() {
		int id = consola.leerEntero("Introduce el ID del contacto que quieres eliminar: ");

	    boolean eliminado = agenda.eliminarContactoPorId(id);

	    if (eliminado) {
	        consola.escribirLinea("Contacto eliminado correctamente.");
	        
	    } else {
	        consola.escribirLinea("No existe ningún contacto con el ID indicado.");
	    }
	}

  /**
   * Solicita el ID de un contacto y añade un nuevo teléfono si el contacto existe.
   * Si no existe, muestra un mensaje informativo.
   */
	public void agregarTelefono() {
		int id = consola.leerEntero("Introduce el ID del contacto: ");

	    Contacto contacto = agenda.obtenerPorId(id);

	    if (contacto != null) {
	        String numero = consola.leerTextoNoVacio("Introduce el número de teléfono: ");
	        TipoTelefono tipo = elegirTipoTelefono();

	        contacto.agregarTelefono(numero, tipo);

	        consola.escribirLinea("Teléfono añadido correctamente al contacto.");
	        
	    } else {
	        consola.escribirLinea("No existe ningún contacto con el ID indicado.");
	    }
	}

  /**
   * Muestra por consola las opciones disponibles de {@link TipoVia} y devuelve el tipo elegido.
   *
   * @return Tipo de vía seleccionado por el usuario.
   */
	private TipoVia elegirTipoVia() {
		consola.escribirLinea("Tipo de vía:");
		
        TipoVia[] valores = TipoVia.values();
        int i = 0;
        
        while (i < valores.length) {
            System.out.println((i + 1) + ") " + valores[i]);
            i++;
        }

        int opcion = consola.leerEnteroRango("Elige tipo (1-" + valores.length + "): ", 1, valores.length);
        
        return valores[opcion - 1]; 
	}

  /**
   * Muestra por consola las opciones disponibles de {@link TipoTelefono} y devuelve el tipo elegido.
   *
   * @return Tipo de teléfono seleccionado por el usuario.
   */
	private TipoTelefono elegirTipoTelefono() {
		consola.escribirLinea("Tipo de teléfono:");
		
        TipoTelefono[] valores = TipoTelefono.values();
        int i = 0;
        
        while (i < valores.length) {
            System.out.println((i + 1) + ") " + valores[i]);
            i++;
        }

        int opcion = consola.leerEnteroRango("Elige tipo (1-" + valores.length + "): ", 1, valores.length);
        
        return valores[opcion - 1]; 
	}
}