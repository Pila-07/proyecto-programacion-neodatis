package acceso;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import modelo.Entrenador;
import modelo.Estadio;

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

	public static void eliminarEntrenador(int codigo) throws ODBRuntimeException {
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

	public static void actualizarEntrenador(Entrenador nuevoEntrenador, int codigo) throws ODBRuntimeException {
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
	
	public static int getOID(Entrenador entrenador) throws ODBRuntimeException {
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			int oid = Integer.parseInt(odb.getObjectId(entrenador).toString());
			return oid;
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
	public static String consultarEstadioPorEquipo(int id) throws ODBRuntimeException {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			ICriterion criterio = Where.equal("equipo.oid", id);
			IQuery consulta = new CriteriaQuery(Estadio.class, criterio);
			Objects<Estadio> coleccionEstadios = odb.getObjects(consulta);			
			if (!coleccionEstadios.hasNext()) {
				return null;
			}
			else {
				Estadio estadio = coleccionEstadios.next();
				oid = odb.getObjectId(estadio);
			}
			return oid.toString();
		}finally {
			if(odb!=null) {
				odb.close();
			}
		}
	}

}
