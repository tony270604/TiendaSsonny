
package modeloDao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import confi.Conexion;
import java.sql.SQLException;
import java.util.List;
import modelo.Boleta;
import modelo.DetalleBoleta;

public class BoletaDAO {
    
    // Inserta una boleta y devuelve el número generado (num_bol)
    public int insertarBoleta(Boleta boleta) throws SQLException {
        String sql = "INSERT INTO boleta (fec_bol, total_bol, dni_cli, cod_ven) VALUES (?, ?, ?, ?)";
        int numBoleta = -1;

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);  // inicio transacción

            ps.setDate(1, new java.sql.Date(boleta.getFec_bol().getTime()));
            ps.setBigDecimal(2, boleta.getTotal_bol());
            ps.setString(3, boleta.getDni_cli());
            ps.setString(4, boleta.getCod_ven());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        numBoleta = rs.getInt(1);
                    }
                }
            }

            if (numBoleta == -1) {
                conn.rollback();
                throw new SQLException("No se pudo obtener número de boleta generado");
            }

            conn.commit();

            return numBoleta;
        }
    }
    
    // Inserta la lista de detalles para una boleta
    public void insertarDetalles(int numBoleta, List<DetalleBoleta> listaDetalles) throws SQLException {
        String sql = "INSERT INTO detalleboleta (num_bol, cod_pro, can) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (DetalleBoleta detalle : listaDetalles) {
                ps.setInt(1, numBoleta);
                ps.setString(2, detalle.getCod_pro());
                ps.setInt(3, detalle.getCan());
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();
        }
    }

    // Método para insertar la boleta y detalles en una transacción conjunta
    public void guardarBoletaConDetalles(Boleta boleta, List<DetalleBoleta> listaDetalles) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexion.getConexion();
            conn.setAutoCommit(false);

            // Insertar boleta
            String sqlBoleta = "INSERT INTO boleta (fec_bol, total_bol, dni_cli, cod_ven) VALUES (?, ?, ?, ?)";
            PreparedStatement psBoleta = conn.prepareStatement(sqlBoleta, Statement.RETURN_GENERATED_KEYS);
            psBoleta.setDate(1, new java.sql.Date(boleta.getFec_bol().getTime()));
            psBoleta.setBigDecimal(2, boleta.getTotal_bol());
            psBoleta.setString(3, boleta.getDni_cli());
            psBoleta.setString(4, boleta.getCod_ven());
            psBoleta.executeUpdate();

            ResultSet rs = psBoleta.getGeneratedKeys();
            int numBoleta = -1;
            if (rs.next()) {
                numBoleta = rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el número de boleta");
            }

            // Insertar detalles
            String sqlDetalle = "INSERT INTO detalleboleta (num_bol, cod_pro, can) VALUES (?, ?, ?)";
            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
            for (DetalleBoleta detalle : listaDetalles) {
                psDetalle.setInt(1, numBoleta);
                psDetalle.setString(2, detalle.getCod_pro());
                psDetalle.setInt(3, detalle.getCan());
                psDetalle.addBatch();
            }
            psDetalle.executeBatch();

            conn.commit();

            // Asignar el número generado al objeto Boleta si quieres
            boleta.setNum_bol(numBoleta);

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
