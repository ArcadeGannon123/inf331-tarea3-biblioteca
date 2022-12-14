package biblioteca;


public class Main {
	
	public static void main(String[] args) {
		
		int seleccion = -1;
		ConsoleInput.init();
		System.out.println("=====================================");
		System.out.println("Sistema de registro de libros v1.0.0");
		System.out.println("=====================================");
		
		Registro registros = new Registro();		
		
		while(seleccion != 0) {
			System.out.println("Seleccione una operación:");
			System.out.println("1.-Buscar un libro");
			System.out.println("2.-Registrar un libro");
			System.out.println("3.-Editar un libro");
			System.out.println("4.-Cambiar estado de un libro");
			System.out.println("5.-Eliminar un libro:");
			System.out.println("6.-Libros registrados");
			System.out.println("0.-Salir del programa");	
			
			seleccion = ConsoleInput.restricted_integer_input(0, 6);
			
			switch(seleccion){
				case 1:	
					registros.buscar_libro();
					break;
				case 2:
					registros.registrar_libro();
					break;
				case 3:
					registros.editar_libro();
					break;
				case 4:
					registros.cambiar_estado();
					break;
				case 5:
					registros.eliminar_libro();
					break;
				case 6:
					System.out.println("=====================================");
					System.out.println("Lista de libros disponibles:");
					
					registros.lista_registros();
					break;
				default:
					break;
			}
		}
		System.out.println("=====================================");
		System.out.println("¡Programa terminado!");
		System.out.println("=====================================");
		ConsoleInput.close();
	}

}
