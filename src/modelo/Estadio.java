package modelo;

public class Estadio {
	
	private String nombre;
	private String ubicacion;
	private int capacidad;
	private Equipo equipo;
	
	public Estadio(String nombre, String ubicacion, int capacidad, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Estadio [Nombre = " + nombre + ", Ubicación = " + ubicacion + 
				", Capacidad = " + capacidad + ", Equipo = " + equipo.toString() + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}
