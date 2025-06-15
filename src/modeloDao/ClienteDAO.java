package modeloDao;

import confi.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO {

    //AGREGAR CLIENTE
    public boolean agregarCliente(String dni, String nombre, String numero, String correo) {
        String sql = "INSERT INTO cliente (dni_cli, nom_cli, num_cli, correo_cli) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, dni);
            pst.setString(2, nombre);
            pst.setString(3, numero);
            pst.setString(4, correo);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }

    //ACTUALIZAR CLIENTES
    public DefaultTableModel obtenerClientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Número");
        modelo.addColumn("Correo");

        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("dni_cli"),
                    rs.getString("nom_cli"),
                    rs.getString("num_cli"),
                    rs.getString("correo_cli")
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar clientes: " + e.getMessage());
        }

        return modelo;
    }

    //BUSACAR CLIENTES 
    public DefaultTableModel buscarClientePorDNI(String dniParcial) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Número");
        modelo.addColumn("Correo");

        String sql = "SELECT * FROM cliente WHERE dni_cli LIKE ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, dniParcial + "%"); // Empieza con...

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("dni_cli"),
                    rs.getString("nom_cli"),
                    rs.getString("num_cli"),
                    rs.getString("correo_cli")
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
        }

        return modelo;
    }

    //EDITAR CLIENTE
    public boolean editarCliente(String dni, String nombre, String numero, String correo) {
        String sql = "UPDATE cliente SET nom_cli = ?, num_cli = ?, correo_cli = ? WHERE dni_cli = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, numero);
            pst.setString(3, correo);
            pst.setString(4, dni);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al editar cliente: " + e.getMessage());
            return false;
        }
    }

    //ELIMINAR CLIENTES
    public boolean eliminarCliente(String dni) {
        String sql = "DELETE FROM cliente WHERE dni_cli = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, dni);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

}
