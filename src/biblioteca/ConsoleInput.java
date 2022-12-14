package biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
	
	public static BufferedReader reader;
	
	public static void init() {
		
		reader = new BufferedReader(new InputStreamReader(System.in));
		
	}

	public static void close() {
		
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String input_string() {
		
		String entrada = "";
		
		try {
			System.out.print(">> ");
			entrada = reader.readLine();
			return entrada;
			
		}catch(Exception ex){
			System.out.println("¡Hubo un error!, intente nuevamente");
			return entrada;
		}
	}
	
	public static int input_integer() {
		
		int entrada = -1;
		
		try {
			System.out.print(">> ");
			String test = reader.readLine();
			entrada = Integer.parseInt(test);
			return entrada;
			
		}catch(NumberFormatException ex){
			System.out.println("¡Entrada invalida!");
			return entrada;
		}catch(Exception ex){
			System.out.println("¡Hubo un error!, intente nuevamente");
			return entrada;
		}
	}

	public static boolean validar_seleccion(int min, int max, int seleccion) {
		
		boolean validado = true;
		
		if(seleccion < min) {
			validado = false;
		}
		if(seleccion > max) {
			validado = false;
		}
		
		if(!validado) {		
			System.out.println("¡Selección invalida!");
		}
		
		return validado;
		
	}
	
	public static int restricted_integer_input(int min, int max) {
		
		int seleccion = -1;
		boolean validacion = false;
		
		while(!validacion) {
			seleccion = input_integer();
			validacion = validar_seleccion(min,max,seleccion);			
		}
		return seleccion;
	}
	
}
