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

import modelo.Equipo;
import modelo.Estadio;

public class AccesoEstadio {

    //insertar, consultar, actualizar, eliminar
    
	public static int insertarEstadio(String nombre, String ubicacion,
	        int capacidad, int codigoEquipo) throws ODBRuntimeException {
	    ODB odb = null;
	    try {
	        odb = ODBFactory.open("data\\futbol.db");
	        OID oidEquipo = OIDFactory.buildObjectOID(codigoEquipo);
	        Equipo equipo = (Equipo) odb.getObjectFromId(oidEquipo);
	        Estadio estadio = new Estadio(nombre, ubicacion, capacidad, equipo);
	        OID oid = odb.store(estadio);
	        return Integer.parseInt(oid.toString());
	    } finally {
	        if (odb != null) odb.close();
	    }
	}
    
    public static int eliminarEstadio(int codigo) throws ODBRuntimeException {
        ODB odb = null;
        OID oid = null;
        try {
            odb = ODBFactory.open("data\\futbol.db");
            oid = OIDFactory.buildObjectOID(codigo);
            Estadio estadio = (Estadio) odb.getObjectFromId(oid);
            odb.delete(estadio);
            return Integer.parseInt(oid.toString());
        } finally {
            if(odb != null) {
                odb.close();
            }
        }
    }

    public static List <Estadio> consultarEstadios() throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open("data\\futbol.db");
            Objects<Estadio> coleccionEstadios = odb.getObjects(Estadio.class);
            List<Estadio> listaEstadios = new ArrayList<Estadio>();
            
                while (coleccionEstadios.hasNext()) {
                    listaEstadios.add(coleccionEstadios.next());
                }
                return listaEstadios;
        }finally {
            if(odb!=null) {
                odb.close();
            }
        }
    }
    
    public static Map<Integer, Estadio> consultarEstadiosConOID() throws ODBRuntimeException {
	    ODB odb = null;
	    try {
	        odb = ODBFactory.open("data\\futbol.db");
	        Objects<Estadio> coleccionEstadios = odb.getObjects(Estadio.class);
	        Map<Integer, Estadio> mapa = new LinkedHashMap<Integer, Estadio>();
	        while (coleccionEstadios.hasNext()) {
	        	Estadio estadio = coleccionEstadios.next();
	            int oid = Integer.parseInt(odb.getObjectId(estadio).toString());
	            mapa.put(oid, estadio);
	        }
	        return mapa;
	    } finally {
	        if (odb != null) odb.close();
	    }
	}
    
    public static int actualizarEstadio(int codigoEstadio, int codigoEquipo, Estadio nuevoEstadio) throws ODBRuntimeException {
        ODB odb = null;
        OID oidEstadio = null;
        OID oidEquipo = null;
        try {
            odb = ODBFactory.open("data\\futbol.db");
            oidEstadio = OIDFactory.buildObjectOID(codigoEstadio);
            Estadio estadio = (Estadio) odb.getObjectFromId(oidEstadio);
            estadio.setNombre(nuevoEstadio.getNombre());
            estadio.setUbicacion(nuevoEstadio.getUbicacion());
            estadio.setCapacidad(nuevoEstadio.getCapacidad());
            oidEquipo = OIDFactory.buildObjectOID(codigoEquipo);
            Equipo equipo = (Equipo) odb.getObjectFromId(oidEquipo);
            estadio.setEquipo(equipo);
            odb.store(estadio);
            return Integer.parseInt(oidEstadio.toString());
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
    
    public static Estadio consultarEstadioPorEquipo(String equipo) throws ODBRuntimeException {
		ODB odb = null;
		Estadio estadio = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			ICriterion criterio = Where.equal("equipo.nombre", equipo);
			IQuery consulta = new CriteriaQuery(Estadio.class, criterio);
			Objects<Estadio> coleccionEstadios = odb.getObjects(consulta);			
			if (coleccionEstadios.hasNext()) {
				estadio = coleccionEstadios.next();
			}
			return estadio;
		}finally {
			if(odb!=null) {
				odb.close();
			}
		}
	}
}