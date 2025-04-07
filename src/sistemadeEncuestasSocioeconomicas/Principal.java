
package sistemadeEncuestasSocioeconomicas;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Principal {

	public static void main(String[] args) {
		final int MIN_CANT_ENCUESTADOS = 1; final int MAX_CANT_ENCUESTADOS = 60;
		final int ATRIBUTOS_ENCUESTADO = 6;
		final int MIN_DNI = 10000000; final int MAX_DNI = 99999999;
		final int MIN_SUELDO = 0; final int MAX_SUELDO = 500000;
		
		Scanner s = new Scanner(System.in);
		String[][] registroDeEncuestados = new String[MAX_CANT_ENCUESTADOS][ATRIBUTOS_ENCUESTADO];
		int cantidadEncuestados = 0;

		int cantidadAIngresar = ingresarCuantasPersonas(s);
		cantidadEncuestados = ingresarPersona(s, registroDeEncuestados, cantidadEncuestados, cantidadAIngresar);
		
		s.close();
	}

	//Funciones Principales

	public static void mostrarMenuYElegirOpcion(){
		mostrarMenu();
		elegirOpcion();
	}

	public static int ingresarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadEncuestados){

		System.out.print("Ingrese DNI del encuestado"); 
		registroDeEncuestados[indice][0] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro

		System.out.print("Ingrese el nombre completo del encuestado");
		registroDeEncuestados[indice][1] = s.nextLine();//ToDo => Hacer mas seguro

		System.out.print("Ingrese el sexo del encuestado, siendo 1 masculino, 2 femenino y 3 otro"); 
		registroDeEncuestados[indice][2] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro

		System.out.print("Ingrese el nombre completo del encuestado");
		registroDeEncuestados[indice][3] = s.nextLine();//ToDo => Hacer mas seguro

		System.out.print("Ingrese DNI del encuestado"); 
		registroDeEncuestados[indice][4] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro

		System.out.print("Ingrese el nombre completo del encuestado");
		registroDeEncuestados[indice][5] = s.nextLine();//ToDo => Hacer mas seguro


		cantidadEncuestados++;
		return cantidadEncuestados;
	};

	//Funciones Auxiliares

	public static void mostrarMenu(){
		System.out.println("1)Agregar ");
		System.out.println("2)");
		System.out.println("3)");
		System.out.println("4)");
		System.out.println("5)");
		System.out.println("6)");
		System.out.println("7)");
		System.out.println("8)");
	}


}
