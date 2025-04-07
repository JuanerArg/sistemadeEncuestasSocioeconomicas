
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

	public static int ingresarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadEncuestados, int cantidadAIngresar){
		int indice = 0;
		do{
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

			indice++;
		}while(indice < cantidadAIngresar);
		cantidadEncuestados += cantidadAIngresar;
		return cantidadEncuestados;
	};

	public static int ingresarCuantasPersonas(Scanner s){
		int cantidadAIngresar = 0;
		boolean noAnda = false;
		do{
			try{
				System.out.print("Ingrese cuantas personas va a encuestar: ");
				cantidadAIngresar = s.nextInt();
				if(cantidadAIngresar < MIN_CANT_ENCUESTADOS || cantidadAIngresar > MAX_CANT_ENCUESTADOS){
					System.err.println("ERROR: Ingrese un numero de encuestados dentro de los parametros 1-60");
					noAnda = true;
				}
			}catch(InputMismatchException e){
				System.err.println("ERROR: Ingrese un numero");
				noAnda = true;
			}
		}while(noAnda);
		return cantidadAIngresar;
	}
}
