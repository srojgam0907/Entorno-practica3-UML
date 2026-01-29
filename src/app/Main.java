package app;

import dominio.Agenda;

public class Main {

    public static void main(String[] args) {
    	Consola consola = new Consola();
        Agenda agenda = new Agenda();
        GestorAgenda gestor = new GestorAgenda(consola, agenda);
        Menu menu = new Menu(consola);
        
        int opcion;

        do {
            menu.mostrar();
            opcion = menu.leerOpcion();

            switch (opcion) {
                case 1 -> gestor.agregarContacto();

                case 2 -> gestor.listarContactos();

                case 3 -> gestor.buscarContactos();

                case 4 -> gestor.borrarContactos();

                case 5 -> gestor.agregarTelefono();

                case 0 -> consola.escribirLinea("Saliendo...");
     
            }

        } while (opcion != 0);

        consola.cerrar(); 
    }

}