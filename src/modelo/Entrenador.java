package modelo;

public class Entrenador {
	
	private String nombre;
	private int partidosGanados;
	private double salario;
	
	public Entrenador(String nombre, int partidosGanados, double salario) {
		super();
		this.nombre = nombre;
		this.partidosGanados = partidosGanados;
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Entrenador [Nombre = " + nombre + ", Partidos Ganados = " + 
				partidosGanados + ", Salario = " + String.format("%.2f", salario) + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public int getPartidosGanados() {
		return partidosGanados;
	}

	public double getSalario() {
		return salario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}
