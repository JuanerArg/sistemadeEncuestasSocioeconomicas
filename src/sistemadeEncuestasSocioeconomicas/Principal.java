
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
		System.out.println("1) Agregar persona");
		System.out.println("2) Consultar persona");
		System.out.println("3) Modificar persona");
		System.out.println("4) Eliminar persona");
		System.out.println("5) Listar todas las personas");
		System.out.println("6) Ver estadisticas");
		System.out.println("7) Buscar personas con mayor o menor sueldo");
		System.out.println("8) Salir");
	}

	public static void elegirOpcion(Scanner s){
		ingresarEntero(s, 1, 8);
		do{
			switch(opcion){
				case 1:
					ingresarPersona();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case default:
					System.err.println("ERROR: Ingrese un valor valido")
					break
			}
		}while(opcion != 8)
	}

	public static int ingresarEntero(Scanner s){
		int entero = 0;
		boolean error = false;
		
		do{
			try{
				entero = s.nextInt();

			}
			catch(InputMismatchException e){
				System.err.out("ERROR: Ingrese un numero de tipo entero")
				error = true;
			
			}
			catch(Exeption e){
				System.err.out("ERROR: Algo salio mal, intente otra vez");
				error = true

			}
		}while(error);
		
		return entero;
	}
}
