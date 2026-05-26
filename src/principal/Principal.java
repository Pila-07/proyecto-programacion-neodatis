package principal;
 
import java.util.List;
import java.util.Map;

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
		Map<Integer, Entrenador> mapaEntrenadores;
		Map<Integer, Equipo> mapaEquipos;
		Map<Integer, Estadio> mapaEstadios;
		Entrenador entrenador = null;
		Equipo equipo = null;
		Estadio estadio = null;
		String nombre, ubicacion;
		int partidosGanados, anhoFundacion, titulosGanados, capacidad, codigo;
		int codigoEntrenador = 0;
		int codigoEquipo = 0;
		double salario;
 
		do {
			menu();
			opcion = Teclado.leerEntero("Elige una opción (0-4): ");
			System.out.println();
			try {
				switch (opcion) {
				case 0:
					System.out.println("Programa finalizado.");
					break;
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
						salario = Teclado.leerReal("Salario: ");
						entrenador = new Entrenador(nombre, partidosGanados, salario);
						codigo = AccesoEntrenador.insertarEntrenador(entrenador);
						System.out.println("\nEntrenador insertado con OID: " + codigo + "\n");
						break;
					case 2: // Insertar Equipo
						nombre = Teclado.leerCadena("Nombre del equipo: ");
						anhoFundacion = Teclado.leerEntero("Año de fundación: ");
						titulosGanados = Teclado.leerEntero("Títulos ganados: ");
						mapaEntrenadores = AccesoEntrenador.consultarEntrenadoresConOID();
						if (!mapaEntrenadores.isEmpty()) {
							System.out.println("\nEntrenadores disponibles:");
							for (Map.Entry<Integer, Entrenador> entrada : mapaEntrenadores.entrySet()) {
							    System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							entrenador = null;
							while (entrenador == null) {
								codigoEntrenador = Teclado.leerEntero("OID del entrenador: ");
								if (mapaEntrenadores.containsKey(codigoEntrenador)) {
									entrenador = mapaEntrenadores.get(codigoEntrenador);
								} else {
									System.out.println("\nNo existe ningún "
											+ "entrenador con ese OID\n");
								}
							}
							codigo = AccesoEquipo.insertarEquipo(nombre, anhoFundacion, titulosGanados, codigoEntrenador);
							System.out.println("\nEquipo insertado con OID: " + codigo + "\n");
						} else {
							System.out.println("\nNo hay entrenadores registrados. "
									+ "Inserte un nuevo registro para continuar.\n");
						}
						break;
					case 3: // Insertar Estadio
						nombre = Teclado.leerCadena("Nombre del estadio: ");
						ubicacion = Teclado.leerCadena("Ubicación: ");
						capacidad = Teclado.leerEntero("Capacidad (aforo): ");
						mapaEquipos = AccesoEquipo.consultarEquiposConOID();
						if (!mapaEquipos.isEmpty()) {
							System.out.println("\nEquipos disponibles:");
							for (Map.Entry<Integer, Equipo> entrada : mapaEquipos.entrySet()) {
							    System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							equipo = null;
							while (equipo == null) {
								codigoEquipo = Teclado.leerEntero("OID del equipo: ");
								if (mapaEquipos.containsKey(codigoEquipo)) {
									equipo = mapaEquipos.get(codigoEquipo);
								} else {
									System.out.println("\nNo existe ningún "
											+ "equipo con ese OID\n");
								}
							}
							codigo = AccesoEstadio.insertarEstadio(nombre, ubicacion, capacidad, codigoEquipo);
							System.out.println("\nEstadio insertado con OID: " + codigo + "\n");
						} else {
							System.out.println("\nNo hay equipos registrados. "
									+ "Inserte un nuevo registro para continuar.\n");
						}
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
						listaEstadios = AccesoEstadio.consultarEstadios();
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
						mapaEntrenadores = AccesoEntrenador.consultarEntrenadoresConOID();
						if (!mapaEntrenadores.isEmpty()) {
							System.out.println("\nEntrenadores disponibles:");
							for (Map.Entry<Integer, Entrenador> entrada : mapaEntrenadores.entrySet()) {
								System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							codigo = 0;
							while (!mapaEntrenadores.containsKey(codigo)) {
								codigo = Teclado.leerEntero("OID del entrenador a modificar: ");
								if (!mapaEntrenadores.containsKey(codigo)) {
									System.out.println("\nNo existe ningún entrenador con ese OID\n");
								}
							}
							nombre = Teclado.leerCadena("Nuevo nombre: ");
							partidosGanados = Teclado.leerEntero("Nuevos partidos ganados: ");
							salario = Teclado.leerReal("Nuevo salario: ");
							entrenador = new Entrenador(nombre, partidosGanados, salario);
							AccesoEntrenador.actualizarEntrenador(entrenador, codigo);
							System.out.println("\nEntrenador actualizado correctamente.\n");
						}else {
							System.out.println("\nNo hay entrenadores registrados.\n");
						}
						break;
					case 2: // Modificar Equipo
						mapaEquipos = AccesoEquipo.consultarEquiposConOID();
						if (!mapaEquipos.isEmpty()) {
							System.out.println("\nEquipos disponibles:");
							for (Map.Entry<Integer, Equipo> entrada : mapaEquipos.entrySet()) {
								System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							codigo = 0;
							while (!mapaEquipos.containsKey(codigo)) {
								codigo = Teclado.leerEntero("OID del equipo a modificar: ");
								if (!mapaEquipos.containsKey(codigo)) {
									System.out.println("\nNo existe ningún equipo con ese OID\n");
								}
							}
							nombre = Teclado.leerCadena("Nuevo nombre del equipo: ");
							anhoFundacion = Teclado.leerEntero("Nuevo año de fundación: ");
							titulosGanados = Teclado.leerEntero("Nuevos títulos ganados: ");
							mapaEntrenadores = AccesoEntrenador.consultarEntrenadoresConOID();
							if (mapaEntrenadores.isEmpty()) {
								System.out.println("\nEntrenadores disponibles:");
								for (Map.Entry<Integer, Entrenador> entrada : mapaEntrenadores.entrySet()) {
									System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
								}
								codigoEntrenador = 0;
								while (!mapaEntrenadores.containsKey(codigoEntrenador)) {
									codigoEntrenador = Teclado.leerEntero("OID del nuevo entrenador: ");
									if (!mapaEntrenadores.containsKey(codigoEntrenador)) {
										System.out.println("\nNo existe ningún entrenador con ese OID\n");
									}
								}
								equipo = new Equipo(nombre, anhoFundacion, titulosGanados, null);
								AccesoEquipo.actualizarEquipo(codigo, equipo, codigoEntrenador);
								System.out.println("\nEquipo actualizado correctamente.\n");
							}else {
								System.out.println("\nNo hay entrenadores registrados.\n");
							}
						}else {
							System.out.println("\nNo hay equipos registrados.\n");
						}
						break;
					case 3: // Modificar Estadio
						mapaEstadios = AccesoEstadio.consultarEstadiosConOID();
						if (!mapaEstadios.isEmpty()) {
							System.out.println("\nEstadios disponibles:");
							for (Map.Entry<Integer, Estadio> entrada : mapaEstadios.entrySet()) {
								System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							codigo = 0;
							while (!mapaEstadios.containsKey(codigo)) {
								codigo = Teclado.leerEntero("OID del estadio a modificar: ");
								if (!mapaEstadios.containsKey(codigo)) {
									System.out.println("\nNo existe ningún estadio con ese OID\n");
								}
							}
							nombre = Teclado.leerCadena("Nuevo nombre del estadio: ");
							ubicacion = Teclado.leerCadena("Nueva ubicación: ");
							capacidad = Teclado.leerEntero("Nueva capacidad: ");
							mapaEquipos = AccesoEquipo.consultarEquiposConOID();
							if (!mapaEquipos.isEmpty()) {
								System.out.println("\nEquipos disponibles:");
								for (Map.Entry<Integer, Equipo> entrada : mapaEquipos.entrySet()) {
									System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
								}
								codigoEquipo = 0;
								while (!mapaEquipos.containsKey(codigoEquipo)) {
									codigoEquipo = Teclado.leerEntero("OID del nuevo equipo: ");
									if (!mapaEquipos.containsKey(codigoEquipo)) {
										System.out.println("\nNo existe ningún equipo con ese OID\n");
									}
								}
								estadio = new Estadio(nombre, ubicacion, capacidad, null);
								AccesoEstadio.actualizarEstadio(codigo, codigoEquipo, estadio);
								System.out.println("\nEstadio actualizado correctamente.\n");
							}else {
								System.out.println("\nNo hay equipos registrados.\n");
							}
						}else {
							System.out.println("\nNo hay estadios registrados.\n");
						}
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
						mapaEntrenadores = AccesoEntrenador.consultarEntrenadoresConOID();
						if (!mapaEntrenadores.isEmpty()) {
							System.out.println("\nEntrenadores disponibles:");
							for (Map.Entry<Integer, Entrenador> entrada : mapaEntrenadores.entrySet()) {
							    System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							codigo = 0;
							while (!mapaEntrenadores.containsKey(codigo)) {
								codigo = Teclado.leerEntero("OID del entrenador a eliminar: ");
								if (!mapaEntrenadores.containsKey(codigo)) {
									System.out.println("\nNo existe ningún entrenador con ese OID\n");
								}
							}
							AccesoEntrenador.eliminarEntrenador(codigo);
							System.out.println("\nEntrenador eliminado correctamente.\n");
						} else {
							System.out.println("\nNo hay entrenadores registrados.\n");
						}
						break;
					case 2: // Eliminar Equipo
						mapaEquipos = AccesoEquipo.consultarEquiposConOID();
						if (!mapaEquipos.isEmpty()) {
							System.out.println("\nEquipos disponibles:");
							for (Map.Entry<Integer, Equipo> entrada : mapaEquipos.entrySet()) {
							    System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							codigo = 0;
							while (!mapaEquipos.containsKey(codigo)) {
								codigo = Teclado.leerEntero("OID del equipo a eliminar: ");
								if (!mapaEquipos.containsKey(codigo)) {
									System.out.println("\nNo existe ningún equipo con ese OID\n");
								}
							}
							AccesoEquipo.eliminarEquipo(codigo);
							System.out.println("\nEquipo eliminado correctamente.\n");
						}else {
							System.out.println("\nNo hay equipos registrados.\n");
						}
						break;
					case 3: // Eliminar Estadio
						mapaEstadios = AccesoEstadio.consultarEstadiosConOID();
						if (!mapaEstadios.isEmpty()) {
							System.out.println("\nEstadios disponibles:");
							for (Map.Entry<Integer, Estadio> entrada : mapaEstadios.entrySet()) {
							    System.out.println("  OID " + entrada.getKey() + " -> " + entrada.getValue());
							}
							codigo = 0;
							while (!mapaEstadios.containsKey(codigo)) {
								codigo = Teclado.leerEntero("OID del estadio a eliminar: ");
								if (!mapaEstadios.containsKey(codigo)) {
									System.out.println("\nNo existe ningún estadio con ese OID\n");
								}
							}
							AccesoEstadio.eliminarEstadio(codigo);
							System.out.println("\nEstadio eliminado correctamente.\n");
						}else {
							System.out.println("\nNo hay estadios registrados.\n");
						}
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
