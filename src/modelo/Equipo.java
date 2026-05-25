package modelo;

public class Equipo {
	
	private String nombre;
	private int anhoFundacion;
	private int titulosGanados;
	private Entrenador entrenador;
	
	public Equipo(String nombre, int anhoFundacion, int titulosGanados, 
			Entrenador entrenador) {
		super();
		this.nombre = nombre;
		this.anhoFundacion = anhoFundacion;
		this.titulosGanados = titulosGanados;
		this.entrenador = entrenador;
	}

	@Override
	public String toString() {
		return "Equipo [Nombre = " + nombre + ", Año Fundación = " + anhoFundacion
				+ ", Titulos Ganados = " + titulosGanados + ", Entrenador = " 
				+ entrenador.toString() + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public int getAnhoFundacion() {
		return anhoFundacion;
	}

	public int getTitulosGanados() {
		return titulosGanados;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAnhoFundacion(int anhoFundacion) {
		this.anhoFundacion = anhoFundacion;
	}

	public void setTitulosGanados(int titulosGanados) {
		this.titulosGanados = titulosGanados;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

}
