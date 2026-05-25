package principal;

import java.util.List;

import org.neodatis.odb.ODBRuntimeException;

import entrada.Teclado;
import modelo.Entrenador;
import modelo.Equipo;
import modelo.Estadio;

public class Principal {

	private static void menu() {
		System.out.println("(0) Salir.");
		System.out.println("(1) Insertar.");
		System.out.println("(2) Consultar.");
		System.out.println("(3) Modificar.");
		System.out.println("(4) Eliminar.");
	}

	private static void menuClases() {
		System.out.println("(0) Volver.");
		System.out.println("(1) Entrenador.");
		System.out.println("(2) Equipo.");
		System.out.println("(3) Estadio.");
	}

	public static void main(String[] args) {
		int opcion, opcionClase;
		List <Entrenador> listaEntrenadores;
		List <Equipo> listaEquipos;
		List <Estadio> listaEstadios;
		do {
			menu();
			opcion = Teclado.leerEntero("Elige una opción(0-4) ");
			System.out.println();
			try {
				switch (opcion) {
				case 0:
					System.out.println("Programa finalizado");
					break;
				case 1:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción(0-3) ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menu...\n");
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						System.err.println("La opción de menú debe estar comprendida"
								+ " entre 0 y 4. Vuelva a intentarlo.\n");
					}
					break;
				case 2:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción(0-3) ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menu...\n");
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						System.err.println("La opción de menú debe estar comprendida"
								+ " entre 0 y 3. Vuelva a intentarlo.\n");
					}
					break;
				case 3:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción(0-3) ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menu...\n");
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						System.err.println("La opción de menú debe estar comprendida"
								+ " entre 0 y 3. Vuelva a intentarlo.\n");
					}
					break;
				case 4:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción(0-3) ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menu...\n");
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						System.err.println("La opción de menú debe estar comprendida"
								+ " entre 0 y 3. Vuelva a intentarlo.\n");
					}
					break;
				default:
					System.err.println("La opción de menú debe estar comprendida"
							+ " entre 0 y 4.\n");
				}
			} catch (ODBRuntimeException odbre) {
				System.out.println("Error de NeoDatis: " + odbre.getMessage());
			}
		} while (opcion != 0);
	}
}