
package sistemadeEncuestasSocioeconomicas;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Principal {

	public static void main(String[] args) {
		final int MIN_CANT_ENCUESTADOS = 1; final int MAX_CANT_ENCUESTADOS = 60;
		final int ATRIBUTOS_ENCUESTADO = 6;
		final int MIN_DNI = 10000000; final int MAX_DNI = 99999999;
		final int MIN_SUELDO = 0; final int MAX_SUELDO = 500000;
		final int MIN_EDAD = 18; final int MAX_EDAD = 99;

		Scanner s = new Scanner(System.in);
		String[][] registroDeEncuestados = new String[MAX_CANT_ENCUESTADOS][ATRIBUTOS_ENCUESTADO];
		int cantidadEncuestados = 0;

		int cantidadAIngresar = ingresarCuantasPersonas(s);
		cantidadEncuestados = ingresarPersona(s, registroDeEncuestados, cantidadEncuestados, cantidadAIngresar);
		mostrarRegistro(registroDeEncuestados, cantidadEncuestados);
		s.close();
	}

	public static int ingresarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadEncuestados, int cantidadAIngresar){
		int indice = 0;
		do{
			System.out.print("Ingrese DNI del encuestado"); 
			registroDeEncuestados[indice][0] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro
			s.nextLine();

			System.out.print("Ingrese el nombre completo del encuestado");
			registroDeEncuestados[indice][1] = s.nextLine();//ToDo => Hacer mas seguro
			
			System.out.print("Ingrese el sexo del encuestado, siendo 1 masculino, 2 femenino y 3 otro"); 
			registroDeEncuestados[indice][2] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro
			s.nextLine();

			System.out.print("Ingrese la edad del encuestado (entre 18 y 99)");
			registroDeEncuestados[indice][3] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro
			s.nextLine();

			System.out.print("Ingrese si el encuestado trabaja(1) o no(2)"); 
			registroDeEncuestados[indice][4] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro
			s.nextLine();
			
			System.out.print("Ingrese el sueldo del encuestado");
			registroDeEncuestados[indice][5] = Integer.toString(s.nextInt());//ToDo => Hacer mas seguro
			s.nextLine();

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
				if(cantidadAIngresar <= 0){
					System.err.println("ERROR: Ingrese al menos 1 persona");
					noAnda = true;
				}

			}catch(InputMismatchException e){
				System.err.println("ERROR: Ingrese un numero");
				noAnda = true;
			}
		}while(noAnda);
		return cantidadAIngresar;
	}

	public static void mostrarRegistro(String [][] registroDeEncuestados, int cantidadEncuestados){
		int indice = 0;

		do{
			System.out.println("DNI: " + registroDeEncuestados[indice][0]);//ToDo => Arreglar Numeros Magicos
			System.out.println("Nombre: " + registroDeEncuestados[indice][1]);//ToDo => Arreglar Numeros Magicos
			System.out.println("Sexo: " + registroDeEncuestados[indice][2]);//ToDo => Arreglar Numeros Magicos
			System.out.println("Edad: " + registroDeEncuestados[indice][3]);//ToDo => Arreglar Numeros Magicos
			System.out.println("Trabaja: " + registroDeEncuestados[indice][4]);//ToDo => Arreglar Numeros Magicos
			System.out.println("Sueldo: " + registroDeEncuestados[indice][5]);//ToDo => Arreglar Numeros Magicos
			indice++;
		}while(indice < cantidadEncuestados);
	}
}
