package modeloDao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import confi.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Boleta;
import modelo.DetalleBoleta;

public class BoletaDAO {

    // Inserta una boleta y devuelve el número generado (num_bol)
    public int insertarBoleta(Boleta boleta) throws SQLException {
        String sql = "INSERT INTO boleta (fec_bol, total_bol, dni_cli, cod_ven) VALUES (?, ?, ?, ?)";
        int numBoleta = -1;

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

    public void eliminarBoleta(int numBol) throws SQLException {
        String sqlDetalle = "SELECT cod_pro, can FROM detalleboleta WHERE num_bol = ?";
        String sqlActualizarStock = "UPDATE producto SET stock_pro = stock_pro + ? WHERE cod_pro = ?";
        String sqlEliminarDetalle = "DELETE FROM detalleboleta WHERE num_bol = ?";
        String sqlEliminarBoleta = "DELETE FROM boleta WHERE num_bol = ?";

        try (Connection conn = Conexion.getConexion()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle); PreparedStatement psActualizarStock = conn.prepareStatement(sqlActualizarStock); PreparedStatement psEliminarDetalle = conn.prepareStatement(sqlEliminarDetalle); PreparedStatement psEliminarBoleta = conn.prepareStatement(sqlEliminarBoleta)) {
                System.out.println("Iniciando eliminación boleta: " + numBol);

                // Obtener productos y cantidades de la boleta
                psDetalle.setInt(1, numBol);
                ResultSet rs = psDetalle.executeQuery();

                while (rs.next()) {
                    String codPro = rs.getString("cod_pro");
                    int cantidad = rs.getInt("can");
                    System.out.println("Actualizando stock para producto: " + codPro + " cantidad: " + cantidad);

                    psActualizarStock.setInt(1, cantidad);
                    psActualizarStock.setString(2, codPro);
                    psActualizarStock.executeUpdate();
                }

                // Eliminar detalles
                psEliminarDetalle.setInt(1, numBol);
                psEliminarDetalle.executeUpdate();

                // Eliminar boleta
                psEliminarBoleta.setInt(1, numBol);
                int filasEliminadas = psEliminarBoleta.executeUpdate();
                System.out.println("Filas boleta eliminadas: " + filasEliminadas);

                conn.commit();
                System.out.println("Eliminación exitosa y commit realizado.");

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error, rollback realizado.");
                throw e;
            }
        }
    }
    
     public List<Boleta> listarBoletas() {
        List<Boleta> listaBoletas = new ArrayList<>();
        String sql = "SELECT num_bol, fec_bol, total_bol, dni_cli, cod_ven FROM boleta ORDER BY num_bol DESC";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Boleta boleta = new Boleta();
                boleta.setNum_bol(rs.getInt("num_bol"));
                boleta.setFec_bol(rs.getDate("fec_bol"));
                boleta.setTotal_bol(rs.getBigDecimal("total_bol"));
                boleta.setDni_cli(rs.getString("dni_cli"));
                boleta.setCod_ven(rs.getString("cod_ven"));
                // Imprimir para depuración
               System.out.println("Cargando boleta: " + boleta.getNum_bol() + ", Código de vendedor: " + boleta.getCod_ven());
                listaBoletas.add(boleta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaBoletas;
    }
     
     
     
     //creo que est metodo no afecta a ningun codigo
     public List<Boleta> obtenerTodasLasBoletas() throws SQLException {
        List<Boleta> lista = new ArrayList<>();
        String sql = "SELECT * FROM boleta";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Boleta b = new Boleta();
                b.setNum_bol(rs.getInt("num_bol"));
                b.setDni_cli(rs.getString("dni_cli"));
                b.setFec_bol(rs.getDate("fec_bol"));
                b.setTotal_bol(rs.getBigDecimal("total_bol"));
                lista.add(b);
            }
        }

        return lista;
    }

}
