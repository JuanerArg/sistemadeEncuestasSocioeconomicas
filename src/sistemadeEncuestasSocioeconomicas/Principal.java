
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
		int cantidadDeEncuestados = 0;

		cantidadEncuestados = ingresarPersona(s, registroDeEncuestados, cantidadEncuestados, cantidadAIngresar);
		
		s.close();
	}

	//Funciones Principales

	public static void mostrarMenuYElegirOpcion(Scanner s, String[][] registroDeEncuestados, int cantidaDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){
		mostrarMenu();
		elegirOpcion();
	}

	public static int ingresarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){

		System.out.print("Ingrese DNI del encuestado"); 
		registroDeEncuestados[indice][0] = Integer.toString(ingresarEntero(s, MIN_DNI, MAX_DNI ));
		
		System.out.print("Ingrese el nombre completo del encuestado");
		registroDeEncuestados[indice][1] = s.nextLine();

		System.out.print("Ingrese el sexo del encuestado, siendo 1 masculino, 2 femenino y 3 otro"); 
		registroDeEncuestados[indice][2] = Integer.toString(ingresarEntero(s, 1, 3));

		System.out.print("Ingrese la edad del encuestado");
		registroDeEncuestados[indice][3] = Integer.toString(ingresarEntero(s, MIN_EDAD, MAX_EDAD));

		System.out.print("Ingrese si el encuestado trabaja(1) o si no (2)"); 
		registroDeEncuestados[indice][4] = Integer.toString(ingresarEntero(s, 1, 2));

		System.out.print("Ingrese el sueldo del encuestado");
		registroDeEncuestados[indice][5] = Integer.toString(ingresarEntero(s, MIN_SUELDO, MAX_SUELDO));

		cantidadEncuestados++;
		return cantidadEncuestados;
	};

	public static void consultarPersona(Scanner s, final int MIN_DNI, final int MAX_DNI, int cantidadDeEncuestados, String[][] registroDeEncuestados,){
		/*
		ingreso a quien quiero buscar => seria un ingresar entero
		compruebo que el dato ingresado sea valido
		busco
			verifico que este dentro del rango de gente ingresada(si indice > cantidadDeEncuestados ya se que no va)
			realizo la busqueda
				caso 1 no lo encuentro imprimo eso
				caso 2 lo encuentro imprimo a quien se buscaba
		*/

		System.out.println("Ingrese el DNI de la persona que busca");
		int DNI = ingresarEntero(s, MIN_DNI, MAX_DNI, false);

		int posicionEnMatriz = buscarEnMatriz(DNI, cantidadDeEncuestados, registroDeEncuestados);

		if(posicionEnMatriz == 0){
			System.out.println("No se encontro a nadie con ese DNI en la base de datos")
		}

		imprimirDatosPersona(registroDeEncuestados, posicionEnMatriz);
	}

	//Funciones Auxiliares
	
	public static void imprimirDatosPersona(String[][] registroDeEncuestados, int posicionEnMatriz){
		for(int indice = 0; indice < 4; indice++){
			System.out.println(registroDeEncuestados[posicionEnMatriz][indice]);
		}
	}

	public static int buscarEnMatriz(final int DNI, int cantidadDeEncuestados, String[][] registroDeEncuestados){
		for(int indice = 0, indice < cantidadDeEncuestados; indice++){
			if(DNI == Integer.parseInt(registroDeEncuestados[indice][0])){
				return indice;
			}
			return 0;
		}
	}

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

	public static void elegirOpcion(Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){
		opcion = ingresarEntero(s, 1, 8);
		do{
			switch(opcion){
				case 1:
					cantidadDeEncuestados = ingresarPersona(s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_SUELDO, MAX_SUELDO, MIN_EDAD, MAX_EDAD);
					break;
				case 2:
					consultarPersona(s, MIN_DNI, MAX_DNI, cantidadDeEncuestados, registroDeEncuestados);
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
				default:
					System.err.println("ERROR: Ingrese un valor valido");
					break;
			}
		}while(opcion != 8);
	}

	public static int ingresarEntero(Scanner s, final int MIN, final int MAX, boolean usarSalidaDeDatos){
		int entero = 0;
		boolean error = false;
		
		if(usarSalidaDeDatos){
			System.out.println("Ingrese un valor entero entre " + MIN + " y " + MAX);
		}

		do{
			try{
				entero = s.nextInt();
				if(entero < MIN || entero > MAX){
					System.err.println("ERROR: Ingrese un valor entre " + MIN + " y " + MIN);
					error = true;
				}
			}
			catch(InputMismatchException e){
				System.err.println("ERROR: Ingrese un numero de tipo entero")
				error = true;
			
			}
			catch(Exeption e){
				System.err.println("ERROR: Algo salio mal, intente otra vez");
				error = true

			}
		}while(error);

		return entero;
	}
}
