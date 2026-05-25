package principal;
 
import java.util.List;
 
import org.neodatis.odb.ODBRuntimeException;
 
import acceso.AccesoEntrenador;
import acceso.AccesoEquipo;
import acceso.AccesoEstadio;
import entrada.Teclado;
import modelo.Entrenador;
import modelo.Equipo;
import modelo.Estadio;
 
public class Principal {
 
	private static void menu() {
		System.out.println("=== GESTIÓN DE FÚTBOL - NeoDatis ===");
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
		List<Entrenador> listaEntrenadores;
		List<Equipo> listaEquipos;
		List<Estadio> listaEstadios;
		Entrenador entrenador;
		Equipo equipo;
		Estadio estadio;
		String nombre, ubicacion;
		int partidosGanados, anhoFundacion, titulosGanados, capacidad, codigo, codigoEntrenador, codigoEquipo;
		double salario;
 
		do {
			menu();
			opcion = Teclado.leerEntero("Elige una opción (0-4): ");
			System.out.println();
			try {
				switch (opcion) {
 
				// ========================
				// SALIR
				// ========================
				case 0:
					System.out.println("Programa finalizado.");
					break;
 
				// ========================
				// INSERTAR
				// ========================
				case 1:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción (0-3): ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menú...\n");
						break;
 
					case 1: // Insertar Entrenador
						nombre = Teclado.leerCadena("Nombre del entrenador: ");
						partidosGanados = Teclado.leerEntero("Partidos ganados: ");
						salario = Teclado.leerDouble("Salario: ");
						entrenador = new Entrenador(nombre, partidosGanados, salario);
						codigo = AccesoEntrenador.insertarEntrenador(entrenador);
						System.out.println("\nEntrenador insertado con OID: " + codigo + "\n");
						break;
 
					case 2: // Insertar Equipo
						nombre = Teclado.leerCadena("Nombre del equipo: ");
						anhoFundacion = Teclado.leerEntero("Año de fundación: ");
						titulosGanados = Teclado.leerEntero("Títulos ganados: ");
						System.out.println("\nEntrenadores disponibles:");
						listaEntrenadores = AccesoEntrenador.consultarEntrenadores();
						for (Entrenador en : listaEntrenadores) {
							System.out.println("  OID " + AccesoEntrenador.getOID(en) + " -> " + en.toString());
						}
						codigoEntrenador = Teclado.leerEntero("OID del entrenador para este equipo: ");
						entrenador = AccesoEntrenador.consultarEntrenadorPorOID(codigoEntrenador);
						equipo = new Equipo(nombre, anhoFundacion, titulosGanados, entrenador);
						codigo = AccesoEquipo.insertarEquipo(equipo);
						System.out.println("\nEquipo insertado con OID: " + codigo + "\n");
						break;
 
					case 3: // Insertar Estadio
						nombre = Teclado.leerCadena("Nombre del estadio: ");
						ubicacion = Teclado.leerCadena("Ubicación: ");
						capacidad = Teclado.leerEntero("Capacidad (aforo): ");
						System.out.println("\nEquipos disponibles:");
						listaEquipos = AccesoEquipo.consultarEquipos();
						for (Equipo eq : listaEquipos) {
							System.out.println("  OID " + AccesoEquipo.getOID(eq) + " -> " + eq.getNombre());
						}
						codigoEquipo = Teclado.leerEntero("OID del equipo para este estadio: ");
						equipo = AccesoEquipo.consultarEquipoPorOID(codigoEquipo);
						estadio = new Estadio(nombre, ubicacion, capacidad, equipo);
						codigo = AccesoEstadio.insertarEstadio(estadio);
						System.out.println("\nEstadio insertado con OID: " + codigo + "\n");
						break;
 
					default:
						System.err.println("Opción no válida.\n");
					}
					break;
				case 2:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción (0-3): ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menú...\n");
						break;
 
					case 1: // Consultar Entrenadores
						listaEntrenadores = AccesoEntrenador.consultarEntrenadores();
						if (listaEntrenadores.isEmpty()) {
							System.out.println("\nNo hay entrenadores registrados.\n");
						} else {
							System.out.println("\n--- ENTRENADORES ---");
							for (Entrenador en : listaEntrenadores) {
								System.out.println(en.toString());
							}
							System.out.println();
						}
						break;
 
					case 2: // Consultar Equipos
						listaEquipos = AccesoEquipo.consultarEquipos();
						if (listaEquipos.isEmpty()) {
							System.out.println("\nNo hay equipos registrados.\n");
						} else {
							System.out.println("\n--- EQUIPOS ---");
							for (Equipo eq : listaEquipos) {
								System.out.println(eq.toString());
							}
							System.out.println();
						}
						break;
 
					case 3: // Consultar Estadios
						listaEstadios = AccesoEstadio.consultarEstadio();
						if (listaEstadios.isEmpty()) {
							System.out.println("\nNo hay estadios registrados.\n");
						} else {
							System.out.println("\n--- ESTADIOS ---");
							for (Estadio es : listaEstadios) {
								System.out.println(es.toString());
							}
							System.out.println();
						}
						break;
 
					default:
						System.err.println("Opción no válida.\n");
					}
					break;
				case 3:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción (0-3): ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menú...\n");
						break;
 
					case 1: // Modificar Entrenador
						listaEntrenadores = AccesoEntrenador.consultarEntrenadores();
						System.out.println("\nEntrenadores disponibles:");
						for (Entrenador en : listaEntrenadores) {
							System.out.println("  " + en.toString());
						}
						codigo = Teclado.leerEntero("OID del entrenador a modificar: ");
						nombre = Teclado.leerCadena("Nuevo nombre: ");
						partidosGanados = Teclado.leerEntero("Nuevos partidos ganados: ");
						salario = Teclado.leerDouble("Nuevo salario: ");
						entrenador = new Entrenador(nombre, partidosGanados, salario);
						AccesoEntrenador.actualizarEntrenador(entrenador, codigo);
						System.out.println("\nEntrenador actualizado correctamente.\n");
						break;
 
					case 2: // Modificar Equipo
						listaEquipos = AccesoEquipo.consultarEquipos();
						System.out.println("\nEquipos disponibles:");
						for (Equipo eq : listaEquipos) {
							System.out.println("  " + eq.toString());
						}
						codigo = Teclado.leerEntero("OID del equipo a modificar: ");
						nombre = Teclado.leerCadena("Nuevo nombre del equipo: ");
						anhoFundacion = Teclado.leerEntero("Nuevo año de fundación: ");
						titulosGanados = Teclado.leerEntero("Nuevos títulos ganados: ");
						listaEntrenadores = AccesoEntrenador.consultarEntrenadores();
						System.out.println("\nEntrenadores disponibles:");
						for (Entrenador en : listaEntrenadores) {
							System.out.println("  " + en.toString());
						}
						codigoEntrenador = Teclado.leerEntero("OID del nuevo entrenador: ");
						equipo = new Equipo(nombre, anhoFundacion, titulosGanados, null);
						AccesoEquipo.actualizarEquipo(codigo, equipo, codigoEntrenador);
						System.out.println("\nEquipo actualizado correctamente.\n");
						break;
 
					case 3: // Modificar Estadio
						listaEstadios = AccesoEstadio.consultarEstadio();
						System.out.println("\nEstadios disponibles:");
						for (Estadio es : listaEstadios) {
							System.out.println("  " + es.toString());
						}
						codigo = Teclado.leerEntero("OID del estadio a modificar: ");
						nombre = Teclado.leerCadena("Nuevo nombre del estadio: ");
						ubicacion = Teclado.leerCadena("Nueva ubicación: ");
						capacidad = Teclado.leerEntero("Nueva capacidad: ");
						listaEquipos = AccesoEquipo.consultarEquipos();
						System.out.println("\nEquipos disponibles:");
						for (Equipo eq : listaEquipos) {
							System.out.println("  " + eq.toString());
						}
						codigoEquipo = Teclado.leerEntero("OID del nuevo equipo: ");
						estadio = new Estadio(nombre, ubicacion, capacidad, null);
						AccesoEstadio.actualizarEstadio(codigo, codigoEquipo, estadio);
						System.out.println("\nEstadio actualizado correctamente.\n");
						break;
 
					default:
						System.err.println("Opción no válida.\n");
					}
					break;
				case 4:
					menuClases();
					opcionClase = Teclado.leerEntero("Elige una opción (0-3): ");
					switch (opcionClase) {
					case 0:
						System.out.println("\nVolviendo al menú...\n");
						break;
 
					case 1: // Eliminar Entrenador
						listaEntrenadores = AccesoEntrenador.consultarEntrenadores();
						System.out.println("\nEntrenadores disponibles:");
						for (Entrenador en : listaEntrenadores) {
							System.out.println("  " + en.toString());
						}
						codigo = Teclado.leerEntero("OID del entrenador a eliminar: ");
						AccesoEntrenador.eliminarEntrenador(codigo);
						System.out.println("\nEntrenador eliminado correctamente.\n");
						break;
 
					case 2: // Eliminar Equipo
						listaEquipos = AccesoEquipo.consultarEquipos();
						System.out.println("\nEquipos disponibles:");
						for (Equipo eq : listaEquipos) {
							System.out.println("  " + eq.toString());
						}
						codigo = Teclado.leerEntero("OID del equipo a eliminar: ");
						AccesoEquipo.eliminarEquipo(codigo);
						System.out.println("\nEquipo eliminado correctamente.\n");
						break;
 
					case 3: // Eliminar Estadio
						listaEstadios = AccesoEstadio.consultarEstadio();
						System.out.println("\nEstadios disponibles:");
						for (Estadio es : listaEstadios) {
							System.out.println("  " + es.toString());
						}
						codigo = Teclado.leerEntero("OID del estadio a eliminar: ");
						AccesoEstadio.eliminarEstadio(codigo);
						System.out.println("\nEstadio eliminado correctamente.\n");
						break;
 
					default:
						System.err.println("Opción no válida.\n");
					}
					break;
 
				default:
					System.err.println("La opción de menú debe estar comprendida entre 0 y 4.\n");
				}
			} catch (ODBRuntimeException odbre) {
				System.out.println("Error de NeoDatis: " + odbre.getMessage());
			}
		} while (opcion != 0);
	}
}