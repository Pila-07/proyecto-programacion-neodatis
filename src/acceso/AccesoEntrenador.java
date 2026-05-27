package acceso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class AccesoEntrenador {

	public static int insertarEntrenador(Entrenador entrenador) {
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
	
	public static Map<Integer, Entrenador> consultarEntrenadoresConOID() throws ODBRuntimeException {
	    ODB odb = null;
	    try {
	        odb = ODBFactory.open("data\\futbol.db");
	        Objects<Entrenador> col = odb.getObjects(Entrenador.class);
	        Map<Integer, Entrenador> mapa = new LinkedHashMap<Integer, Entrenador>();
	        while (col.hasNext()) {
	            Entrenador e = col.next();
	            int oid = Integer.parseInt(odb.getObjectId(e).toString());
	            mapa.put(oid, e);
	        }
	        return mapa;
	    } finally {
	        if (odb != null) {
	        	odb.close();
	        }
	    }
	}

	public static List <Entrenador> consultarEntrenadorPorSalario(Double salario) throws ODBRuntimeException {
		ODB odb = null;
		List <Entrenador> listaEntrenadores = new ArrayList <Entrenador>();
		try {
			odb = ODBFactory.open("data\\futbol.db");
			ICriterion criterio = Where.ge("salario", salario);
			IQuery consulta = new CriteriaQuery(Entrenador.class, criterio);
			Objects<Entrenador> coleccionEntrenadores = odb.getObjects(consulta);			
			while (coleccionEntrenadores.hasNext()) {
				listaEntrenadores.add(coleccionEntrenadores.next());
			}
			return listaEntrenadores;
		}finally {
			if(odb != null) {
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
	
	public static boolean estaVacia() throws ODBRuntimeException {
	    ODB odb = null;
	    try {
	        odb = ODBFactory.open("data\\futbol.db");
	        Objects<Entrenador> col = odb.getObjects(Entrenador.class);
	        return !col.hasNext();
	    } finally {
	        if (odb != null) odb.close();
	    }
	}

}
