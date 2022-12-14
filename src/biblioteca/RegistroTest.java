package biblioteca;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class RegistroTest{
	
	Registro registros;
		
	@Before
	public void Before() {
		registros= new Registro();
	}
	
	@Test
	public void registrar_libro_test() {
		/*
			>>Prueba para registro de libros
			La secuencia es la siguiente:
			-se ingresa titulo: "titulo de test"
			-se ingresa autor: "autor de test"
			-se intenta guardar (el sistema no lo permite porque existen campos importantes sin rellenar)
			-se se intenta acceder a una opcion inexistente (el sistema no lo permite)
			-se intenta introducir una opcion no valida (el sistema no lo permite)
			-se ingresa editorial: "editorial de test"
			-se ingresa codigo isbn: "123456789",
			-se ingresa estado: "Disponible",
			-se intenta guardar (el sistema lo permite)
			Se comprueban que los cambios sean los correctos.
		*/
		InputStream sysInBackup = System.in;
		String userInput = String.join(System.lineSeparator(),
				"1", "titulo de test", 
				"2", "autor de test",
				"10",
				"20",
				"a",
				"3","editorial de test",
				"4","123456789",
				"8", "Disponible",
				"10")+System.lineSeparator();
	    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
	    System.setIn(bais);
	    
	    int initial_size=registros.getRegistros().size();
	    
		registros.registrar_libro();
		
		int final_size=registros.getRegistros().size();
		
		//obtenemos el ultimo registro guardado
		String[] ultimo_registro = registros.getRegistros().get(registros.getRegistros().size() - 1);
		
		assertEquals("titulo de test",ultimo_registro[0]);// se comprueba que el titulo sea el correcto
		assertEquals("autor de test",ultimo_registro[1]);// se comprueba que el autor sea el correcto
		assertEquals("editorial de test",ultimo_registro[2]);// se comprueba que la editorial sea la correcta
		assertEquals("123456789",ultimo_registro[3]);// se comprueba que el codigo isbn sea el correcto
		assertEquals("Disponible",ultimo_registro[7]);// se comprueba que el estado sea el correcto
		assertEquals(initial_size+1,final_size);// se comprueba que la cantidad de registros sea el correcto
		
		System.setIn(sysInBackup);
	}	
	
	
	@Test
	public void eliminar_libro_test() {	
		/*
			>>Prueba para la eliminacion de libros
			La secuencia es la siguiente:
			-se escoge un libro: libro en la posicion "2"
			Se comprueba que se haya eliminado el libro.
		*/
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		String userInput = "2"+System.lineSeparator();
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		
		int initial_size=registros.getRegistros().size();
		String[] registro_inicial_1 = registros.getRegistros().get(0);
		String[] registro_inicial_2 = registros.getRegistros().get(1);;
		String[] registro_inicial_3 = registros.getRegistros().get(2);;
		
		registros.eliminar_libro();
		
		int final_size=registros.getRegistros().size();
		String[] registro_final_1 = registros.getRegistros().get(0);
		String[] registro_final_2 = registros.getRegistros().get(1);
		
		assertArrayEquals(registro_inicial_1,registro_final_1);// Comprueba que no se hayan modificado otros libros
		assertNotEquals(String.join(" ",registro_inicial_2),String.join(" ",registro_final_2));// Comprueba que el libro en la posicion 2 no exista
		assertArrayEquals(registro_inicial_3,registro_final_2);// comprueba que el libro siguiente al 2 haya ocupado el lugar del libro eliminado
		assertEquals(initial_size,final_size+1);// comprueba que la cantidad de libros se haya reducido a 1
		
		System.setIn(sysInBackup);
	}
	
	@Test
	public void editar_libro_test() {
		/*
			>>Prueba para la edicion de libros
			La secuencia es la siguiente:
			-se selecciona el libro en la posicion "3"
			-se edita el titulo: "titulo editado"
			-se edita la descripcion: "nueva descripcion"
			-se intenta guardar (el sistema lo permite)
			Se comprueba que se hayan realizado los cambios.
		*/
		InputStream sysInBackup = System.in;
		String userInput = String.join(System.lineSeparator(),
				"3",
				"1", "titulo editado",
				"9", "nueva descripcion",
				"10")+System.lineSeparator();
	    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
	    System.setIn(bais);
	    
	    String[] registro_inicial = registros.getRegistros().get(2);
	    
		registros.editar_libro();
		
		
		String[] registro_editado = registros.getRegistros().get(2);
		
		assertEquals("titulo editado",registro_editado[0]);// Se comprueba que el titulo sea el editado
		assertEquals("nueva descripcion",registro_editado[8]);// Se comprueba que la descripcion sea la editada
		assertEquals(registro_inicial[1],registro_editado[1]);// Se comprueba la integridad de los otros datos
		assertEquals(registro_inicial[5],registro_editado[5]);// Se comprueba la integridad de los otros datos
		assertNotEquals(registro_inicial[9],registro_editado[9]);// Se comprueba la fecha de edicion haya cambiado
		
		System.setIn(sysInBackup);
		
	}	
	
	@Test
	public void cambio_estado_test() {
		/*
			>>Prueba para el cambio de estado
			La secuencia es la siguiente:
			-se selecciona el libro en la posicion "2"
			-se escoge el cambio de estado: "3"=Extraviado
			-se escoge la opcion 0 para salir
			Se comprueba que se haya realizado el cambio de estado.
		*/
		InputStream sysInBackup = System.in;
		String userInput = String.join(System.lineSeparator(),
				"2",
				"3",
				"0")+System.lineSeparator();
	    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
	    System.setIn(bais);
	    
	    String estado_inicial = registros.getRegistros().get(1)[7];
	    
		registros.cambiar_estado();
		
		
		String estado_editado = registros.getRegistros().get(1)[7];
		
		assertNotEquals(estado_inicial,estado_editado);//Se comprueba que el estado haya cambiado
		assertEquals("Extraviado",estado_editado);//Se comprueba que el nuevo estado sea el correcto
		
		System.setIn(sysInBackup);
		
	}
	
	@Test
	public void buscar_libro_test() {
		/*
			>>Prueba para la busqueda de libros
			La secuencia es la siguiente:
			-se escoge busqueda por titulo "1"
			-se ingresa la palabra "Viaje"
			-se escoge "1" para seleccionar el primer resultado y ver detalles
			-se escoge 0 para salir
			-se escoge busqueda por autor "2"
			-se ingresa el nombre "Paula Hawkins"
			-se escoge "1" para seleccionar el primer resultado y ver detalles
			-se escoge 0 para salir
			-se escoge busqueda por ISBN "2"
			-se ingresa el codigo "9789569994005"
			-se escoge "1" para seleccionar el primer resultado y ver detalles
			-se escoge 0 para salir
			-se escoge nuevamente 0 para salir de la busqueda
			Se comprueba que las busquedas coincidan con lo disponible en registros.
		*/
		InputStream sysInBackup = System.in;
		String userInput = String.join(System.lineSeparator(),
				"1",
				"Viaje",
				"1", "0",
				"2",
				"Paula Hawkins",
				"1","0",
				"3",
				"9789569994005",
				"1","0",
				"0")+System.lineSeparator();
	    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
	    
	    System.setIn(bais);
	    
	    registros.buscar_libro();	
	    
	    String expected = registros.getRegistros().get(0)[0];// titulo del libro 1 disponible en registros "El Viaje de la Humanidad"
	    String actual = registros.busqueda_titulo.get(0)[0];// resultado de la busqueda por titulo
	    assertEquals(expected,actual);	     //se comprueba que sean iguales
	    
	    expected = registros.getRegistros().get(1)[0];// titulo del libro 2 disponible en registros "La Chica del Tren", autora : "Paula Hawkins"
	    actual = registros.busqueda_autor.get(0)[0];// resultado de la busqueda por autor
	    assertEquals(expected,actual);	     //se comprueba que sean iguales
	    
	    expected = registros.getRegistros().get(3)[0];// titulo del libro 4 disponible en registros "Lamentables Datos Animales", ISBN : "9789569994005"
	    actual = registros.busqueda_isbn.get(0)[0];// resultado de la busqueda por ISBN
	    assertEquals(expected,actual);	     //se comprueba que sean iguales
	    
	    System.setIn(sysInBackup);
	}
	

}
