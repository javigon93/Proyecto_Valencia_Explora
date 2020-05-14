package DatosBDA;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import MODELO.Actividades;
import MODELO.DetallePacks;

/**
 *
 * @author juanf
 */
public class Detallepacks_DAO {

	private Connection conexion;

	public Detallepacks_DAO(Connection conexion) {
		this.conexion = conexion;
	}

	
	public Set<DetallePacks> consultarDetallePacks() throws SQLException {
		Set<DetallePacks> listaPacks = new HashSet<>();
		if (conexion != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String consulta = "SELECT * FROM DetallePacks";
				ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				while (rs.next()) {
					DetallePacks dp = new DetallePacks();
					dp.setIdPack(rs.getInt("idPack"));
					dp.setNumLinea(rs.getInt("numLinea"));
					Actividades actividades = new Actividades();
					actividades.setIdActividad(rs.getInt("idActividad"));
					dp.setActividad(actividades);
					dp.setPrecioActividad(rs.getDouble("precioActividad"));
					dp.setNumeroPlazas(rs.getInt("numeroPlazas"));
					dp.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
					dp.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
					dp.setDuracion(rs.getTime("duracion").toLocalTime());
					listaPacks.add(dp);
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			}
		}
		return listaPacks;
	}

	
	public DetallePacks buscarPack(int idPack) throws SQLException {
		DetallePacks dp = new DetallePacks();
		if (conexion != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String consulta = "SELECT * FROM DetallePacks WHERE idPack = ?";
				ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setInt(1, idPack);
				rs = ps.executeQuery();
				while (rs.next()) {
					dp.setIdPack(rs.getInt("idPack"));
					dp.setNumLinea(rs.getInt("numLinea"));
					Actividades actividades = new Actividades();
					actividades.setIdActividad(rs.getInt("idActividad"));
					dp.setActividad(actividades);
					dp.setPrecioActividad(rs.getDouble("precioActividad"));
					dp.setNumeroPlazas(rs.getInt("numeroPlazas"));
					dp.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
					dp.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
					dp.setDuracion(rs.getTime("duracion").toLocalTime());
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			}
		}
		return dp;
	}
//        PreparedStatement ps = null;
//        Los métodos setter ( setShort, setString, etc.) para el ajuste en valores de parámetro debe especificar los 
//        tipos que son compatibles con el tipo SQL 
	
	public boolean insertarDetallePack(DetallePacks dp) throws SQLException {
		boolean insertado = false;
		if (conexion != null) {
			PreparedStatement ps = null;
			try {
				String consulta = "INSERT INTO DetallePacks (idPack, numLinea, idActividad, precioActividad, numeroPlazas, fechaInicio, fechaFinal, duracion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				ps = conexion.prepareStatement(consulta);
				ps.setInt(1, dp.getIdPack());
				ps.setInt(2, dp.getNumLinea());
				ps.setInt(3, dp.getActividad().getIdActividad());
				ps.setDouble(4, dp.getPrecioActividad());
				ps.setInt(5, dp.getNumeroPlazas());
				ps.setDate(6, Date.valueOf(dp.getFechaInicio()));
				ps.setDate(7, Date.valueOf(dp.getFechaFinal()));
				ps.setTime(8, Time.valueOf(dp.getDuracion()));
				ps.executeUpdate();
				insertado = true;
			} finally {
				if (ps != null) {
					ps.close();
				}
			}
		}
		return insertado;
	}

	
	public boolean modificarDetallePacks(DetallePacks dp) throws SQLException {
		boolean modificado = false;
		if (conexion != null) {
			PreparedStatement ps = null;
			try {
				String consulta = "UPDATE DetallePacks SET idActividad = ?, precioActividad = ?, numeroPlazas = ?, fechaInicio = ?, fechaFinal = ?, duracion = ?  WHERE IdPack = ? AND numLinea = ?";
				ps = conexion.prepareStatement(consulta);
				ps.setInt(1, dp.getActividad().getIdActividad());
				ps.setDouble(2, dp.getPrecioActividad());
				ps.setInt(3, dp.getNumeroPlazas());
				ps.setDate(4, Date.valueOf(dp.getFechaInicio()));
				ps.setDate(5, Date.valueOf(dp.getFechaFinal()));
				ps.setTime(6, Time.valueOf(dp.getDuracion()));
				ps.setInt(7, dp.getIdPack());
				ps.setInt(8, dp.getNumLinea());
				ps.executeUpdate();

				modificado = true;
			} finally {
				if (ps != null) {
					ps.close();
				}
			}
		}
		return modificado;
	}

	
	public boolean eliminarDetallePack(int idPack, Integer numLinea) throws SQLException {
		boolean eliminado = false;

		if (conexion != null) {
			PreparedStatement ps = null;
			try {
				ps = conexion.prepareStatement("DELETE FROM DetallePacks WHERE idPack = ? AND numLinea = ?");
				ps.setInt(1, idPack);
				ps.setInt(2, numLinea);
				ps.executeUpdate();
				eliminado = true;
			} finally {
				if (ps != null) {
					ps.close();
				}
			}
		}
		return eliminado;
	}
}