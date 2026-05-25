package acceso;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;

import modelo.Entrenador;

public class AccesoEntrenador {

	public static int insertarEntrenador(Entrenador entrenador) {
		// pasarle objeto como parametro
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			OID oid = odb.store(entrenador);
			return Integer.parseInt(oid.toString());
		} finally {
			if (odb != null) {
				odb.close();
			}

		}

	}

	public static List<Entrenador> consultarEntrenadores() throws ODBRuntimeException {
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			Objects<Entrenador> coleccionEntrenador = odb.getObjects(Entrenador.class);
			List<Entrenador> listaEntrenador = new ArrayList<Entrenador>();
			while (coleccionEntrenador.hasNext()) {
				listaEntrenador.add(coleccionEntrenador.next());

			}
			return listaEntrenador;
		} finally {
			if (odb != null) {
				odb.close();
			}
		}

	}

	public static void eliminarEntrenador(int codigo) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			oid = OIDFactory.buildObjectOID(codigo);
			Entrenador entrenador = (Entrenador) odb.getObjectFromId(oid);
			odb.delete(entrenador);
		}

		finally {
			if (odb != null) {
				odb.close();
			}
		}

	}

	public static void actualizarEntrenador(Entrenador nuevoEntrenador, int codigo) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			oid = OIDFactory.buildObjectOID(codigo);
			Entrenador entrenador = (Entrenador) odb.getObjectFromId(oid);
			entrenador.setNombre(nuevoEntrenador.getNombre());
			entrenador.setPartidosGanados(nuevoEntrenador.getPartidosGanados());
			entrenador.setSalario(nuevoEntrenador.getSalario());
			odb.store(entrenador);
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

}
