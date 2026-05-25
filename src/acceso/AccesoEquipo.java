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
import modelo.Equipo;

public class AccesoEquipo {
	
	public static int insertarEquipo(Equipo equipo) throws ODBRuntimeException{
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			OID oid = odb.store(equipo);
			return Integer.parseInt(oid.toString());
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
	public static List <Equipo> consultarEquipos() throws ODBRuntimeException{
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			Objects<Equipo> coleccionEquipos = odb.getObjects(Equipo.class);
			List<Equipo> listaEquipos = new ArrayList<Equipo>();
	        while (coleccionEquipos.hasNext()) {
	            listaEquipos.add(coleccionEquipos.next());
	        }
			return listaEquipos;
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
	public static int eliminarEquipo(int codigo) throws ODBRuntimeException{
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			oid = OIDFactory.buildObjectOID(codigo);
			Equipo equipo = (Equipo) odb.getObjectFromId(oid);
			odb.delete(equipo);
			return Integer.parseInt(oid.toString());
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
	public static int actualizarEquipo(int codigoEquipo, Equipo nuevoEquipo, 
			int codigoEntrenador) throws ODBRuntimeException{
		ODB odb = null;
		OID oidEquipo = null;
		OID oidEntrenador = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			oidEquipo = OIDFactory.buildObjectOID(codigoEquipo);
			Equipo equipo = (Equipo) odb.getObjectFromId(oidEquipo);
			equipo.setNombre(nuevoEquipo.getNombre());
			equipo.setAnhoFundacion(nuevoEquipo.getAnhoFundacion());
			equipo.setTitulosGanados(nuevoEquipo.getTitulosGanados());
			oidEntrenador = OIDFactory.buildObjectOID(codigoEntrenador);
			Entrenador entrenador = (Entrenador) odb.getObjectFromId(oidEntrenador);
			equipo.setEntrenador(entrenador);
			odb.store(equipo);
			return Integer.parseInt(oidEquipo.toString());
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

}
