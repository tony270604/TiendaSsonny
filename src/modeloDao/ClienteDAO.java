package modeloDao;

import modelo.Cliente;
import confi.Conexion;
//import java.awt.List;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO {

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

    public DefaultTableModel buscarClientePorDNI(String dniParcial) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Número");
        modelo.addColumn("Correo");

        String sql = "SELECT * FROM cliente WHERE dni_cli LIKE ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, dniParcial + "%");

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

    public Cliente obtenerClientePorDNI(String dni) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE dni_cli = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setDni_cli(rs.getString("dni_cli"));
                cliente.setNom_cli(rs.getString("nom_cli"));
                cliente.setNum_cli(rs.getInt("num_cli"));
                cliente.setCorreo_cli(rs.getString("correo_cli"));
                // Completa con otros campos si existen
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
        }
        return cliente;
    }

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

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setDni_cli(rs.getString("dni_cli"));
                cli.setNom_cli(rs.getString("nom_cli"));
                cli.setNum_cli(Integer.parseInt(rs.getString("num_cli")));
                cli.setCorreo_cli(rs.getString("correo_cli"));
                lista.add(cli);
            }

        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return lista;
    }

}
