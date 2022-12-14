package biblioteca;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Registro {
	
	private ArrayList<String[]> registros = new ArrayList<String[]>();
	public ArrayList<String[]> busqueda_titulo;
	public ArrayList<String[]> busqueda_autor;
	public ArrayList<String[]> busqueda_isbn;
	
	public Registro() {		
		
		leer_registros();
	}
	
	
	private void leer_registros() {
		
		String[] line= {
			"El Viaje de la Humanidad;Oded Galor;Paidós;9789569987915;376;Sociología Y Antropología;3,5,14,(21,54);disponible;EL ENSAYO MÁS IMPORTANTE DEL AÑO. Un lanzamiento internacional en más de 30 países. Durante mucho tiempo se ha creído que la prosperidad del ser humano había aumentado gradualmente en el transcurso de la historia.;2022/12/14 12:11:58",
			"La Chica del Tren;Paula Hawkins;Booket;9789569963681;496;Ficción Moderna Y Contemporánea;3,5,14,(21,54);Disponible;27 millones de lectores en todo el mundo ¿Estabas en el tren de las 8.04? ¿Viste algo sospechoso? Rachel, sí Rachel toma siempre el tren de las 8.04 h. Cada mañana lo mismo: el mismo paisaje, las mismas casas…;2022/12/14 12:11:58",
			"Heartless;Marissa Meyer;V&R Eds;9789877472547;592;Novelas De Amor;3,5,14,(21,54);Disponible;Mucho antes de convertirse en el terror del Pais de las Maravillas, la Reina de Corazones era una chica que tan solo queria enamorarse.;2022/12/14 12:11:58",
			"Lamentables Datos Animales;Brooke Barker;Dc Comics;9789569994005;216;Niños De 14 Años;3,5,14,(21,54);Prestado;Un encantador y extravagante compendio de las más desafortunadas verdades del reino animal, con más de cincuenta ilustraciones hechas a mano.¿Alguna vez se han preguntado qué piensan las efímeras de que su esperanza de vida quepa en un día?;2022/12/14 12:11:58",
			"La Teoría de los Archipiélagos;Alice Kellen;Planeta;9789564082615;288;Novelas De Amor;3,5,14,(21,54);Extraviado;La teoría de los archipiélagos viene a decir que todos somos islas, llegamos solos a este mundo y nos vamos exactamente igual, pero necesitamos tener otras islas alrededor para sentirnos felices en medio de ese mar que une tanto como separa.;2022/12/14 12:11:58",
			"Si el zapato te queda;Julie Murphy;Planeta;9789566159797;384;Novelas De Amor;3,5,14,(21,54);Prestado;Si el zapato no te queda, quizá es hora de diseñar el tuyo. A Cindy le fascinan los zapatos. Un moño bien colocado o un tacón de buena altura son su forma de expresión.;2022/12/14 12:11:58"	
		};
		
		for (int i = 0;i<line.length;i++) {
			String[] libro = line[i].split(";");
			registros.add(libro);
		}		
	}
	
	private String fecha_actual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    String fecha_edicion = dtf.format(now);
	    return fecha_edicion;
	}

	private void rellenar_campos(int index) {
		String titulo = "";
		String autor = "";
		String editorial = "";
		String isbn = "";
		String n_paginas = "";
		String genero = "";
		String ubicacion = "";
		String estado = "";
		String descripcion = "";
		
		if (index > 0) {
			String[] libro = registros.get(index);
			
			titulo = libro[0];
			autor = libro[1];
			editorial = libro[2];
			isbn = libro[3];
			n_paginas = libro[4];
			genero = libro[5];
			ubicacion = libro[6];
			estado = libro[7];
			descripcion = libro[8];
		}
		
		
		int seleccion = -1;		
		
		while(seleccion != 0) {
			System.out.println("Por favor rellene los siguientes campos (*: campo obligatorio):");
			System.out.println("1.-Título*:");
			System.out.println(">> "+titulo);
			System.out.println("2.-Autor*:");
			System.out.println(">> "+autor);
			System.out.println("3.-Editorial*:");
			System.out.println(">> "+editorial);
			System.out.println("4.-Codigo ISBN*:");
			System.out.println(">> "+isbn);
			System.out.println("5.-Número de páginas:");
			System.out.println(">> "+n_paginas);
			System.out.println("6.-Género:");
			System.out.println(">> "+genero);
			System.out.println("7.-Ubicación:");
			System.out.println(">> "+ubicacion);
			System.out.println("8.-Estado:");
			System.out.println(">> "+estado);
			System.out.println("9.-Descripción:");
			System.out.println(">> "+descripcion);
			System.out.println("10.-Guardar | 0.-Cancelar");
			System.out.println("Seleccione el campo a rellenar: ");
			
			seleccion = ConsoleInput.restricted_integer_input(0,10);
			
			switch(seleccion){
				case 1:
					System.out.println("Ingrese el título");
					titulo = ConsoleInput.input_string();
					break;
				case 2:
					System.out.println("Ingrese el autor");
					autor = ConsoleInput.input_string();
					break;
				case 3:
					System.out.println("Ingrese la editorial");
					editorial = ConsoleInput.input_string();
					break;
					
				case 4:
					System.out.println("Ingrese el codigo ISBN");
					isbn = ConsoleInput.input_string();
					break;
				case 5:
					System.out.println("Ingrese el número de páginas");
					n_paginas = Integer.toString(ConsoleInput.input_integer());
					break;
				case 6:
					System.out.println("Ingrese el género");
					genero = ConsoleInput.input_string();
					break;
				case 7:
					System.out.println("Ingrese la ubicación");
					ubicacion = ConsoleInput.input_string();
					break;
				case 8:
					System.out.println("Ingrese el estado");
					estado = ConsoleInput.input_string();	
					break;
				case 9:
					System.out.println("Ingrese una descripción");
					descripcion = ConsoleInput.input_string();	
					break;
				case 10:					
					
					String[] libro = {titulo,autor,editorial,isbn,n_paginas,genero,ubicacion,estado,descripcion,fecha_actual()};
					
					boolean savestate = guardar(index,libro);	
					
					if (savestate) {
						seleccion = 0;
					}
					break;
				default:
					break;
			}
			
			
		}
	}
	
	private boolean guardar(int index,String[] LIBRO) {
	
		if (LIBRO[0] != "" && LIBRO[1] != "" && LIBRO[2] != "" && LIBRO[3] != "") {
			
			if(index > 0) {
				registros.set(index,LIBRO);	
			}else {
				registros.add(LIBRO);				
			}
			
			System.out.println("=====================================");
			System.out.println("¡Libro guardado exitosamente!");
			System.out.println("=====================================");
			return true;
		}else {			
			System.out.println("=====================================");
			System.out.println("¡Asegúrese de completar los campos obligatorios!");
			System.out.println("=====================================");
			return false;
		}
	}

	public void lista_registros() {

		for (int i = 0; i< registros.size(); i++) {
			String[] _libro = registros.get(i);
					
			System.out.println((i+1)+".-"+_libro[0]);
			
		}
		System.out.println("=====================================");
	}
	
	public void registrar_libro() {	
		ConsoleInput.init();
		System.out.println("=====================================");
		System.out.println("Interfaz de registro de libros");
		System.out.println("=====================================");
		rellenar_campos(-1);
		
	}
	
	public void editar_libro() {
		ConsoleInput.init();
		System.out.println("=====================================");
		System.out.println("Interfaz de edición de libros");
		System.out.println("=====================================");

		System.out.println("Seleccione un libro a editar:");
		
		lista_registros();
		System.out.println("0.-Cancelar");
		System.out.println("=====================================");
		int seleccion;
		
		seleccion = ConsoleInput.restricted_integer_input(0, registros.size());
		
		if (seleccion != 0) {
			rellenar_campos(seleccion-1);
		}
		
	}
	
	public void cambiar_estado() {
		ConsoleInput.init();
		System.out.println("=====================================");
		System.out.println("Interfaz de cambio de estado");
		System.out.println("=====================================");
		int seleccion=-1;		
		
		while(seleccion != 0) {
			System.out.println("Seleccione un libro");			
			lista_registros();
			System.out.println("0.-Cancelar");
			System.out.println("=====================================");
			
			seleccion = ConsoleInput.restricted_integer_input(0, registros.size());
			if(seleccion != 0) {
				int seleccion2=-1;
				System.out.println("=====================================");
				System.out.println("Cambiar estado a:");
				System.out.println("1.-Disponible");
				System.out.println("2.-Prestado");
				System.out.println("3.-Extraviado");
				System.out.println("0.-Cancelar");
				seleccion2 = ConsoleInput.restricted_integer_input(0, 3);
				if(seleccion2 != 0) {
					String estado;
					switch(seleccion2) {
						case 1:
							estado = "Disponible";
						case 2:
							estado = "Prestado";
						default:
							estado = "Extraviado";
					}
					registros.get(seleccion-1)[7] = estado;
					System.out.println("=====================================");
					System.out.println("¡Realizado cambio de estado!");
					System.out.println("=====================================");
				}	
			}
		}
		
	}
	
	public void eliminar_libro() {
		ConsoleInput.init();
		
		System.out.println("=====================================");
		System.out.println("Interfaz de eliminación de libros");
		System.out.println("=====================================");
		
		System.out.println("Seleccione un libro para eliminar:");
		
		lista_registros();
		System.out.println("0.-Cancelar");
		System.out.println("=====================================");
		int seleccion;
		
		seleccion = ConsoleInput.restricted_integer_input(0, registros.size());
		
		if (seleccion != 0) {
			registros.remove(seleccion-1);
		}	
	}
	
	public void buscar_libro() {
		ConsoleInput.init();
		System.out.println("=====================================");
		System.out.println("Interfaz de búsqueda de libros");
		System.out.println("=====================================");
		

		int seleccion=-1;
		
		
		while (seleccion != 0) {
			System.out.println("Buscar libro por:");
			System.out.println("1.-Título");
			System.out.println("2.-Autor");
			System.out.println("3.-Código ISBN");
			System.out.println("0.-Cancelar");
			System.out.println("Seleccione un modo de búsqueda:");
			seleccion = ConsoleInput.restricted_integer_input(0, 3);
			if(seleccion!= 0) {
				ArrayList<String[]> resultados = busqueda_por(seleccion);
				guardar_resultados_para_test(seleccion,resultados);
				if(resultados.size() != 0) {
					detalles_busqueda(resultados);			
				}else {
					System.out.println("=====================================");
					System.out.println("¡Sin resultados!");
					System.out.println("=====================================");
				}
			}
		}
		
	}
	
	public ArrayList<String[]> busqueda_por(int tipo) {
		
		ArrayList<String[]> resultados = new ArrayList<String[]>();
		
		int index;
		
		switch(tipo) {
			case 1:
				System.out.println("ingrese título");
				index=0;
				break;
			case 2:
				System.out.println("ingrese Autor");
				index=1;
				break;
			default:
				System.out.println("ingrese código ISBN");
				index=3;
				break;
		}
		
		
		
		String busqueda;
		busqueda = ConsoleInput.input_string();
		
		for (int i=0;i<registros.size();i++) {
			String[] libro = registros.get(i);
			if (libro[index].contains(busqueda)) {
				resultados.add(libro);
			}
		}		
		
		return resultados;
		
	}
	
	private void guardar_resultados_para_test(int tipo,ArrayList<String[]> busqueda) {
		switch(tipo) {
			case 1:
				busqueda_titulo = busqueda;
				break;
			case 2:
				busqueda_autor = busqueda;
				break;
			case 3:
				busqueda_isbn = busqueda;
				break;
		}
	}
	
	private void detalles_busqueda(ArrayList<String[]> resultados) {
		
		System.out.println("=====================================");
		System.out.println("Resultados:");
		System.out.println("=====================================");
		
		for (int i = 0; i< resultados.size(); i++) {
			String[] _libro = resultados.get(i);
					
			System.out.println((i+1)+".-"+_libro[0]);
			
		}

		
		int seleccion=-1;	
		
		while(seleccion != 0) {

			System.out.println("=====================================");
			System.out.println("Seleccione un libro para ver detalles (0.-Cancelar):");
			seleccion = ConsoleInput.restricted_integer_input(0, resultados.size());
			
			if (seleccion != 0) {
				detalles_libro(resultados.get(seleccion-1));
			}
		}

		System.out.println("=====================================");
		
	}
		
	private void detalles_libro(String[] libro) {
		System.out.println("Título:");
		System.out.println(">> "+libro[0]);
		System.out.println("Autor:");
		System.out.println(">> "+libro[1]);
		System.out.println("Editorial:");
		System.out.println(">> "+libro[2]);
		System.out.println("Codigo ISBN:");
		System.out.println(">> "+libro[3]);
		System.out.println("Número de páginas:");
		System.out.println(">> "+libro[4]);
		System.out.println("Género:");
		System.out.println(">> "+libro[5]);
		System.out.println("Ubicación:");
		System.out.println(">> "+libro[6]);
		System.out.println("Estado:");
		System.out.println(">> "+libro[7]);
		System.out.println("Descripción:");
		System.out.println(">> "+libro[8]);
		System.out.println("Fecha edición:");
		System.out.println(">> "+libro[9]);
	}

	public ArrayList<String[]> getRegistros(){
		return registros;
	}
}
