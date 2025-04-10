
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
		
		mostrarMenuYElegirOpcion(s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_SUELDO, MAX_SUELDO, MIN_EDAD, MAX_EDAD);
		
		s.close();
	}	

	//Funciones Principales

	public static void mostrarMenuYElegirOpcion(Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){
		elegirOpcion(s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_SUELDO, MAX_SUELDO, MIN_EDAD, MAX_EDAD);
	}

	public static int ingresarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){
		int indice = cantidadDeEncuestados;
		System.out.print("Ingrese DNI del encuestado"); 
		int DNI = ingresarEntero(s, MIN_DNI, MAX_DNI, false);
		if(validarDNI(DNI, registroDeEncuestados, cantidadDeEncuestados)) {
			return cantidadDeEncuestados;
		}
		registroDeEncuestados[indice][0] = Integer.toString(DNI);
		
		s.nextLine();
		
		System.out.print("Ingrese el nombre completo del encuestado");
		registroDeEncuestados[indice][1] = s.nextLine();

		System.out.print("Ingrese el sexo del encuestado, siendo 1 masculino, 2 femenino y 3 otro"); 
		registroDeEncuestados[indice][2] = Integer.toString(ingresarEntero(s, 1, 3, false));

		System.out.println("Ingrese la edad del encuestado");
		registroDeEncuestados[indice][3] = Integer.toString(ingresarEntero(s, MIN_EDAD, MAX_EDAD, true));

		System.out.print("Ingrese si el encuestado trabaja(1) o si no (2)"); 
		registroDeEncuestados[indice][4] = Integer.toString(ingresarEntero(s, 1, 2, false));

		System.out.print("Ingrese el sueldo del encuestado");
		registroDeEncuestados[indice][5] = Integer.toString(ingresarEntero(s, MIN_SUELDO, MAX_SUELDO, true));

		return ++cantidadDeEncuestados;
	};

	public static void consultarPersona(Scanner s, final int MIN_DNI, final int MAX_DNI, int cantidadDeEncuestados, String[][] registroDeEncuestados){
		/*
		ingreso a quien quiero buscar => seria un ingresar entero
		compruebo que el dato ingresado sea valido
		busco
			verifico que este dentro del rango de gente ingresada(si indice > cantidadDeEncuestados ya se que no va)
			realizo la busqueda
				caso 1 no lo encuentro imprimo eso
				caso 2 lo encuentro imprimo a quien se buscaba
		*/
		boolean vacio = verificarSiVacio(registroDeEncuestados, true);
		if(vacio) return;
		
		System.out.println("Ingrese el DNI de la persona que busca");
		final int DNI = ingresarEntero(s, MIN_DNI, MAX_DNI, false);
		
		int posicionEnMatriz = buscarEnMatriz(DNI, cantidadDeEncuestados, registroDeEncuestados);

		if(posicionEnMatriz == -1){
			System.out.println("No se encontro a nadie con ese DNI en la base de datos");
			return;
		}

		imprimirDatosPersona(registroDeEncuestados, posicionEnMatriz);
	}

	public static void modificarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){
		System.out.println("Ingrese DNI de la persona a modificar");
		final int DNI = ingresarEntero(s, MIN_DNI, MAX_DNI, false);

		int posicionDePersonaBuscada = buscarEnMatriz(DNI, cantidadDeEncuestados, registroDeEncuestados);

		manejarCambios(s, posicionDePersonaBuscada, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_EDAD, MAX_EDAD, MIN_SUELDO, MAX_SUELDO);
	}
	
	public static void eliminarPersona(Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI) {
		System.out.println("Ingrese el DNI de la persona que quiera borrar del sistema");
		int DNI = ingresarEntero(s, MIN_DNI, MAX_DNI, false);
		
		int indiceDePersonaABorrar = buscarEnMatriz(DNI, cantidadDeEncuestados, registroDeEncuestados);
		
		if(registroDeEncuestados[indiceDePersonaABorrar + 1][0] != null) {
			for(int indice = indiceDePersonaABorrar + 1; indice < cantidadDeEncuestados; indice++) {
				registroDeEncuestados[indice - 1] = registroDeEncuestados[indice];
			}
		}else{
			for(int indice = 0; indice < 6; indice++) {
				registroDeEncuestados[indiceDePersonaABorrar][indice] = null;
			}
		}
	}
	
	public static int generarAccion(int opcion, Scanner s, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD) {
		switch(opcion){
		case 1:
			cantidadDeEncuestados = ingresarPersona(s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_SUELDO, MAX_SUELDO, MIN_EDAD, MAX_EDAD);
			break;
		case 2:
			consultarPersona(s, MIN_DNI, MAX_DNI, cantidadDeEncuestados, registroDeEncuestados);//MEJORAR
			break;
		case 3:
			modificarPersona(s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_SUELDO, MAX_SUELDO, MIN_EDAD, MAX_EDAD);
			break;
		case 4:
			eliminarPersona(s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI);
			break;
		case 5:
			listarPersonas(registroDeEncuestados, cantidadDeEncuestados);
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
		return cantidadDeEncuestados;
	}

	public static boolean validarDNI(int DNI, String[][] registroDeEncuestados, int cantidadDeEncuestados) {
		if(verificarSiVacio(registroDeEncuestados, false)) {
			return false;
		}
		for(int indice = 0; indice < cantidadDeEncuestados; indice++) {
			if(DNI == Integer.valueOf(registroDeEncuestados[indice][0])) {
				System.err.println("ERROR: DNI duplicado");
				return true;
			}
		}
		return false;
	}
	
	public static void listarPersonas(String[][] registroDeEncuestados, int cantidadDeEncuestados) {
		for(int personasIndex = 0; personasIndex < cantidadDeEncuestados; personasIndex++) {
			for(int atributosIndex = 0; atributosIndex < 5; atributosIndex++) {
				System.out.println(registroDeEncuestados[personasIndex][atributosIndex]);
			}
		}
	}

	public static void mostrarEstadisticas(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		calcularPorcentajeDeGenero(cantidadDeEncuestados, registroDeEncuestados);
		calcularPorcentajeDeGeneroQueTrabaja(cantidadDeEncuestados, registroDeEncuestados);
		calcularSueldoPromedio(cantidadDeEncuestados, registroDeEncuestados);
		calcularSueldoPromedioPorGenero(cantidadDeEncuestados, registroDeEncuestados);
		calcularEdadPromedio(cantidadDeEncuestados, registroDeEncuestados);
	}
	//Funciones Auxiliares
	
	public static void calcularEdadPromedio(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int sumaEdades = 0;
		
		for (int i = 0; i < cantidadDeEncuestados; i++) {
			int edad = Integer.parseInt(registroDeEncuestados[i][3]);
			sumaEdades += edad;
		}
		
		if (cantidadDeEncuestados > 0) {
			double promedio = (double) sumaEdades / cantidadDeEncuestados;
			System.out.printf("Edad promedio general: %.2f años\n", promedio);
		} else {
			System.out.println("No hay personas cargadas para calcular edad promedio.");
		}
	}

	
	public static void calcularSueldoPromedioPorGenero(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int[] sumaSueldos = new int[3];
		int[] cantidadPorGenero = new int[3];
		
		for (int i = 0; i < cantidadDeEncuestados; i++) {
			int genero = Integer.parseInt(registroDeEncuestados[i][2]) - 1;
			int sueldo = Integer.parseInt(registroDeEncuestados[i][5]);
			
			if (genero >= 0 && genero < 3) {
				sumaSueldos[genero] += sueldo;
				cantidadPorGenero[genero]++;
			}
		}
		
		String[] etiquetas = {"Masculino", "Femenino", "Otro"};
		for (int i = 0; i < 3; i++) {
			if (cantidadPorGenero[i] > 0) {
				double promedio = (double) sumaSueldos[i] / cantidadPorGenero[i];
				System.out.printf("Sueldo promedio de %s: $%.2f\n", etiquetas[i], promedio);
			} else {
				System.out.printf("No hay datos de sueldos para el género %s.\n", etiquetas[i]);
			}
		}
	}

	public static void calcularSueldoPromedio(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int sumaSueldos = 0;
		int cantidadValidos = 0;
		
		for (int i = 0; i < cantidadDeEncuestados; i++) {
			int sueldo = Integer.parseInt(registroDeEncuestados[i][5]);
			sumaSueldos += sueldo;
			cantidadValidos++;
		}
		
		if (cantidadValidos > 0) {
			double promedio = (double) sumaSueldos / cantidadValidos;
			System.out.printf("Sueldo promedio general: $%.2f\n", promedio);
		} else {
			System.out.println("No hay datos de sueldos para calcular el promedio.");
		}
	}
	

	
	public static void calcularPorcentajeDeGeneroQueTrabaja(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int[] cantidadDePersonasQueTrabajan = new int[4];
		cantidadDePersonasQueTrabajan = contarCuantosTrabajan(cantidadDeEncuestados, registroDeEncuestados);
		
		float[] porcentajeDePersonasQueTrabajanDeCadaGenero = new float[3];
		porcentajeDePersonasQueTrabajanDeCadaGenero = calcularPorcentajeDePersonasQueTrabajan(cantidadDePersonasQueTrabajan);
		
		imprimirPorcentajeDePersonasQueTrabajanDeCadaGenero(porcentajeDePersonasQueTrabajanDeCadaGenero);
	}

	public static void imprimirPorcentajeDePersonasQueTrabajanDeCadaGenero(float[] porcentajeDePersonasQueTrabajanDeCadaGenero) {
		for(int indice = 0; indice < 2; indice++) {
			switch(indice) {
				case 1:
					System.out.println("El porcentaje de hombres" + porcentajeDePersonasQueTrabajanDeCadaGenero[indice]);
					break;
				case 2:
					System.out.println("El porcentaje de mujeres" + porcentajeDePersonasQueTrabajanDeCadaGenero[indice]);
					break;
				case 3:
					System.out.println("El porcentaje de otros" + porcentajeDePersonasQueTrabajanDeCadaGenero[indice]);
					break;
			}
		}
	}

	public static float[] calcularPorcentajeDePersonasQueTrabajan(int[] cantidadDePersonasQueTrabajan) {
		float porcentajeDeHombresQueTrabajan = (cantidadDePersonasQueTrabajan[0]/100)*cantidadDePersonasQueTrabajan[3];
		float porcentajeDeMujeresQueTrabajan = (cantidadDePersonasQueTrabajan[1]/100)*cantidadDePersonasQueTrabajan[3];
		float porcentajeDeOtrosQueTrabajan = (cantidadDePersonasQueTrabajan[2]/100)*cantidadDePersonasQueTrabajan[3];
		float[] porcentajesDeGenerosQueTrabajan = new float []{porcentajeDeHombresQueTrabajan, porcentajeDeMujeresQueTrabajan, porcentajeDeOtrosQueTrabajan};
		return porcentajesDeGenerosQueTrabajan;
	}
	
	public static int[] contarCuantosTrabajan(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int contadorDeHombresQueTrabajan = 0;
		int contadorDeMujeresQueTrabajan = 0;
		int contadorDeOtrosQueTrabajan = 0;
		int cantidadQueTrabajan = 0;

		for(int indice = 0; indice < cantidadDeEncuestados; indice++) {
			if(registroDeEncuestados[indice][2] == "1" && registroDeEncuestados[indice][4] == "1") {
				contadorDeHombresQueTrabajan++;
				cantidadQueTrabajan++;
			}else if(registroDeEncuestados[indice][2] == "2" && registroDeEncuestados[indice][4] == "1") {
				contadorDeMujeresQueTrabajan++;
				cantidadQueTrabajan++;
			}else if(registroDeEncuestados[indice][2] == "3" && registroDeEncuestados[indice][4] == "1") {
				contadorDeOtrosQueTrabajan++;
				cantidadQueTrabajan++;
			}
		}
		int[] cantidadDePersonasQueTrabajan = new int[] {contadorDeHombresQueTrabajan, contadorDeMujeresQueTrabajan, contadorDeOtrosQueTrabajan, cantidadQueTrabajan};
		return cantidadDePersonasQueTrabajan;
	}
	
	public static void calcularPorcentajeDeGenero(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int[] cantidadDeGeneros = new int[3];	
		float[] porcentajesDeGeneros = new float [3];
		cantidadDeGeneros = contarDistintosGeneros(cantidadDeEncuestados, registroDeEncuestados)	;
		porcentajesDeGeneros  = calcularPorcentaje(cantidadDeEncuestados, cantidadDeGeneros);
		imprimirPorcentajes(porcentajesDeGeneros);
	}

	public static void imprimirPorcentajes(float[] porcentajesDeGeneros) {
		imprimirPorcentajeDePersonasQueTrabajanDeCadaGenero(porcentajesDeGeneros);
	}

	public static float[] calcularPorcentaje(int cantidadDeEncuestados, int[] cantidadDeGeneros) {
		float porcentajeDeHombres = (cantidadDeGeneros[1]/100)*cantidadDeEncuestados;
		float porcentajeDeMujeres = (cantidadDeGeneros[2]/100)*cantidadDeEncuestados;
		float porcentajeDeOtros = (cantidadDeGeneros[3]/100)*cantidadDeEncuestados;
		float[] porcentajesDeGeneros = new float []{porcentajeDeHombres, porcentajeDeMujeres, porcentajeDeOtros};
		return porcentajesDeGeneros;
	}
	
	public static int[] contarDistintosGeneros(int cantidadDeEncuestados, String[][] registroDeEncuestados) {
		int contadorDeHombres = 0;
		int contadorDeMujeres = 0;
		int contadorDeOtro = 0;

		for(int indice = 0; indice < cantidadDeEncuestados; indice++) {
			if(registroDeEncuestados[indice][2] == "1") {
				contadorDeHombres++;
			}else if(registroDeEncuestados[indice][2] == "2") {
				contadorDeMujeres++;
			}else if(registroDeEncuestados[indice][2] == "3") {
				contadorDeOtro++;
			}
		}
		int[] cantidadPorGenero = new int[] {contadorDeHombres, contadorDeMujeres, contadorDeOtro};
		return cantidadPorGenero;
	}
	
	public static boolean verificarSiVacio(String[][] registroDeEncuestados, boolean usarSysOut) {
		
		if(registroDeEncuestados[0][0] == null) {
			if(usarSysOut) {
				System.out.println("La base de datos esta vacia, ingrese informacion e intente otra vez");
			}
			return true;
		}else {
			if(usarSysOut) {
				System.out.println("La base de datos no esta vacia");
			}
			return false;
		}
	}

	public static void manejarCambios(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados, int cantidadDeEncuestados, final int MIN_DNI, final int MAX_DNI, final int MIN_SUELDO, final int MAX_SUELDO, final int MIN_EDAD, final int MAX_EDAD){
		int opcion = 0;
		imprimirOpcionesDeEdicion();
		opcion = ingresarEntero(s, 1, 7, true);
		
		switch(opcion){
			case 1: 
				cambiarDNI(s, posicionDePersonaBuscada, registroDeEncuestados, MIN_DNI, MAX_DNI);
				break;
			case 2: 
				cambiarNombre(s, posicionDePersonaBuscada, registroDeEncuestados);
				break;
			case 3: 
				cambiarSexo(s, posicionDePersonaBuscada, registroDeEncuestados);
				break;
			case 4: 
				cambiarEdad(s, posicionDePersonaBuscada, registroDeEncuestados, MIN_EDAD, MAX_EDAD);
				break;
			case 5: 
				cambiarTrabaja(s, posicionDePersonaBuscada, registroDeEncuestados);
				break;
			case 6: 
				cambiarSueldo(s, posicionDePersonaBuscada, registroDeEncuestados, MIN_SUELDO, MAX_SUELDO);
				break;
			case 7: 
				return;
			default: System.out.println("ERROR: Ingrese una opcion valida");
		}
	}

	public static void imprimirOpcionesDeEdicion(){
		System.out.println("Ingrese que dato quiere editar: ");
		System.out.println("1)DNI");
		System.out.println("2)Nombre");
		System.out.println("3)Sexo");
		System.out.println("4)Edad");
		System.out.println("5)Trabaja");
		System.out.println("6)Sueldo");
		System.out.println("7)Salir");
	}

	public static void cambiarDNI(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados, final int MIN_DNI, final int MAX_DNI){
		System.out.print("Ingrese el DNI nuevo del encuestado"); 
		registroDeEncuestados[posicionDePersonaBuscada][0] = Integer.toString(ingresarEntero(s, MIN_DNI, MAX_DNI, true));
	}

	public static void cambiarNombre(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados){
		System.out.print("Ingrese el nombre completo del encuestado");
		registroDeEncuestados[posicionDePersonaBuscada][1] = s.nextLine();
	}

	public static void cambiarSexo(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados){
		System.out.print("Ingrese el sexo del encuestado, siendo 1 masculino, 2 femenino y 3 otro"); 
		registroDeEncuestados[posicionDePersonaBuscada][2] = Integer.toString(ingresarEntero(s, 1, 3, false));
	}

	public static void cambiarEdad(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados, final int MIN_EDAD, final int MAX_EDAD){
		System.out.print("Ingrese la edad del encuestado");
		registroDeEncuestados[posicionDePersonaBuscada][3] = Integer.toString(ingresarEntero(s, MIN_EDAD, MAX_EDAD, true));
	}

	public static void cambiarTrabaja(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados){
		System.out.print("Ingrese si el encuestado trabaja(1) o si no (2)"); 
		registroDeEncuestados[posicionDePersonaBuscada][4] = Integer.toString(ingresarEntero(s, 1, 2, false));
	}

	public static void cambiarSueldo(Scanner s, int posicionDePersonaBuscada, String[][] registroDeEncuestados, final int MIN_SUELDO, final int MAX_SUELDO){
		System.out.print("Ingrese el nuevo sueldo del encuestado");
		registroDeEncuestados[posicionDePersonaBuscada][5] = Integer.toString(ingresarEntero(s, MIN_SUELDO, MAX_SUELDO, true));
	}	
	
	public static void imprimirDatosPersona(String[][] registroDeEncuestados, int posicionEnMatriz){
		for(int indice = 0; indice < 6; indice++){
			System.out.println(registroDeEncuestados[posicionEnMatriz][indice]);
		}
	}

	public static int buscarEnMatriz(final int DNI, int cantidadDeEncuestados, String[][] registroDeEncuestados){
		for(int indice = 0; indice < cantidadDeEncuestados; indice++){
			if(DNI == Integer.parseInt(registroDeEncuestados[indice][0])){
				return indice;
			}
		}
		return -1;
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
		int opcion;
		do{
			mostrarMenu();
			opcion = ingresarEntero(s, 1, 8, true);
			cantidadDeEncuestados = generarAccion(opcion, s, registroDeEncuestados, cantidadDeEncuestados, MIN_DNI, MAX_DNI, MIN_SUELDO, MAX_SUELDO, MIN_EDAD, MAX_EDAD);
		}while(opcion != 8);
	}
		
	public static int ingresarEntero(Scanner s, final int MIN, final int MAX, boolean usarSalidaDeDatos){
		int entero = 0;
		boolean error = false;
		
		if(usarSalidaDeDatos){
			System.out.println("Ingrese un valor entero entre " + MIN + " y " + MAX);
		}

		do{
			error = false;
			try{
				entero = s.nextInt();
				if(entero < MIN || entero > MAX){
					System.err.println("ERROR: Ingrese un valor entre " + MIN + " y " + MAX);
					error = true;
				}
			}
			catch(InputMismatchException e){
				System.err.println("ERROR: Ingrese un numero de tipo entero");
				s.nextLine();
				error = true;
			
			}
			catch(Exception e){
				System.err.println("ERROR: Algo salio mal, intente otra vez");
				s.nextLine();
				error = true;

			}
		}while(error);

		return entero;
	}
}
