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

import entrada.Teclado;
import modelo.Equipo;
import modelo.Estadio;

public class AccesoEstadio {

    //insertar, consultar, actualizar, eliminar
    
    public static int insertarEstadio(Estadio estadio) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open("data\\futbol.db");
            OID oid = odb.store(estadio);
            return Integer.parseInt(oid.toString());
        } finally {
            if (odb != null) {
                odb.close();
            }
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
    public static List <Estadio> consultarEstadio() throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open("data\\personal.db");
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
    
    public static int actualizarEstadio(int codigoEstadio, int codigoEquipo, Estadio nuevoEstadio) throws ODBRuntimeException {
        ODB odb = null;
        OID oidEstadio = null;
        OID oidEquipo = null;
        try {
            odb = ODBFactory.open("data\\personal.db");
            int codigo = Teclado.leerEntero("¿OID? ");
            oidEstadio = OIDFactory.buildObjectOID(codigo);
            Estadio estadio = (Estadio) odb.getObjectFromId(oidEstadio);
            estadio.setNombre(nuevoEstadio.getNombre());
            estadio.setUbicacion(nuevoEstadio.getUbicacion());
            estadio.setCapacidad(nuevoEstadio.getCapacidad());
            estadio.setEquipo(nuevoEstadio.getEquipo());
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
    
    public static String consultarEstadioPorEquipo(String equipo) throws ODBRuntimeException {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\futbol.db");
			ICriterion criterio = Where.equal("estadio.nombre", equipo);
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
