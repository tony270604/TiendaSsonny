package modeloDao;

import confi.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import modelo.Producto;

public class ProductoDao {

    Producto p;
    
    //AGREGAR PRODUCTO
    public boolean agregarProducto(Producto p, int cod_cat) throws SQLException {
        String sqlProducto = "INSERT INTO producto (cod_pro, nom_pro, stock_pro, prec_pro) VALUES (?, ?, ?, ?)";
        String sqlCategoriaProducto = "INSERT INTO categoriaproducto (cod_cat, cod_pro) VALUES (?, ?)";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement pstProducto = con.prepareStatement(sqlProducto); PreparedStatement pstCategoria = con.prepareStatement(sqlCategoriaProducto)) {

                // Inserta el producto
                pstProducto.setString(1, p.cod_pro);
                pstProducto.setString(2, p.nom_pro);
                pstProducto.setInt(3, p.stock_pro);
                pstProducto.setFloat(4, p.prec_pro);
                pstProducto.executeUpdate();

                // Inserta la relación producto-categoría
                pstCategoria.setInt(1, cod_cat);
                pstCategoria.setString(2, p.cod_pro);
                pstCategoria.executeUpdate();

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback(); // Deshace todo si algo falla
                JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
                return false;
            }
        }
    }
    
    //LISTAR PRODUCTO
    public DefaultTableModel listarProducto() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("cod_pro");
        modelo.addColumn("nom_pro");
        modelo.addColumn("stock_pro");
        modelo.addColumn("prec_pro");
        modelo.addColumn("categoria");

        String sql = "SELECT p.cod_pro, p.nom_pro, p.stock_pro, p.prec_pro, c.nom_cat "
                + "FROM Producto p "
                + "INNER JOIN CategoriaProducto cp ON p.cod_pro = cp.cod_pro "
                + "INNER JOIN Categoria c ON cp.cod_cat = c.cod_cat";

        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("cod_pro"),
                    rs.getString("nom_pro"),
                    rs.getString("stock_pro"),
                    rs.getString("prec_pro"),
                    rs.getString("nom_cat")

                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.getMessage());
        }

        return modelo;
    }

    public DefaultTableModel buscarProductoPorNombre(String nombre) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("cod_pro");
        modelo.addColumn("nom_pro");
        modelo.addColumn("stock_pro");
        modelo.addColumn("prec_pro");
        modelo.addColumn("categoria");

        String sql = "SELECT p.cod_pro, p.nom_pro, p.stock_pro, p.prec_pro, c.nom_cat "
                + "FROM Producto p "
                + "INNER JOIN CategoriaProducto cp ON p.cod_pro = cp.cod_pro "
                + "INNER JOIN Categoria c ON cp.cod_cat = c.cod_cat "
                + "WHERE p.nom_pro LIKE ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombre + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("cod_pro"),
                    rs.getString("nom_pro"),
                    rs.getString("stock_pro"),
                    rs.getString("prec_pro"),
                    rs.getString("nom_cat")
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.getMessage());
        }

        return modelo;
    }

    public DefaultTableModel buscarProductoPorCategoria(String nombre) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("cod_pro");
        modelo.addColumn("nom_pro");
        modelo.addColumn("stock_pro");
        modelo.addColumn("prec_pro");
        modelo.addColumn("categoria");

        String sql = "SELECT p.cod_pro, p.nom_pro, p.stock_pro, p.prec_pro, c.nom_cat "
                + "FROM Producto p "
                + "INNER JOIN CategoriaProducto cp ON p.cod_pro = cp.cod_pro "
                + "INNER JOIN Categoria c ON cp.cod_cat = c.cod_cat "
                + "WHERE c.nom_cat LIKE ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombre + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("cod_pro"),
                    rs.getString("nom_pro"),
                    rs.getString("stock_pro"),
                    rs.getString("prec_pro"),
                    rs.getString("nom_cat")
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.getMessage());
        }

        return modelo;
    }

    public boolean editarProducto(Producto p, int cod_cat) throws SQLException {
        String sqlProducto = "UPDATE producto SET nom_pro = ?, stock_pro = ?, prec_pro = ? WHERE cod_pro = ?";
        String sqlCategoriaProducto = "UPDATE categoriaproducto SET cod_cat = ? WHERE cod_pro = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (
                    PreparedStatement pstProducto = con.prepareStatement(sqlProducto); PreparedStatement pstCategoria = con.prepareStatement(sqlCategoriaProducto)) {
                // Actualiza datos del producto
                pstProducto.setString(1, p.nom_pro);
                pstProducto.setInt(2, p.stock_pro);
                pstProducto.setFloat(3, p.prec_pro);
                pstProducto.setString(4, p.cod_pro);
                pstProducto.executeUpdate();

                // Actualiza la categoría asociada
                pstCategoria.setInt(1, cod_cat);
                pstCategoria.setString(2, p.cod_pro);
                pstCategoria.executeUpdate();

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Error al editar producto: " + e.getMessage());
                return false;
            }
        }
    }

    public boolean eliminarProducto(String cod_pro) throws SQLException {
        String sqlCategoria = "DELETE FROM CategoriaProducto WHERE cod_pro = ?";
        String sqlProducto = "DELETE FROM Producto         WHERE cod_pro = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement pstCat = con.prepareStatement(sqlCategoria); PreparedStatement pstProd = con.prepareStatement(sqlProducto)) {

                pstCat.setString(1, cod_pro);
                pstCat.executeUpdate();

                pstProd.setString(1, cod_pro);
                int rows = pstProd.executeUpdate();

                con.commit();
                return rows > 0;

            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(
                        null,
                        "Error al eliminar producto: " + e.getMessage()
                );
                return false;
            }
        }
    }

    
    //LISTAR PRODUCTO COMBO-BOLETA
    public List<Producto> listarProductosBoletas() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setCod_pro(rs.getString("cod_pro"));
                p.setNom_pro(rs.getString("nom_pro"));
                p.setStock_pro(rs.getInt("stock_pro"));
                p.setPrec_pro(rs.getFloat("prec_pro"));
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return lista;
    }
    
    
    public List<ProductoDTO> listarProductosConPrecioDescuento() {
        List<ProductoDTO> productos = new ArrayList<>();
        String sql = "SELECT p.cod_pro, p.nom_pro, p.prec_pro, c.desc_cat, " +
                     "p.prec_pro * (1 - c.desc_cat/100) AS precio_con_descuento " +
                     "FROM producto p " +
                     "JOIN categoriaproducto cp ON p.cod_pro = cp.cod_pro " +
                     "JOIN categoria c ON cp.cod_cat = c.cod_cat " +
                     "ORDER BY p.nom_pro";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String codigo = rs.getString("cod_pro");
                String nombre = rs.getString("nom_pro");
                float precioUnitario = rs.getFloat("prec_pro");
                int descuento = rs.getInt("desc_cat");
                float precioConDesc = rs.getFloat("precio_con_descuento");

                productos.add(new ProductoDTO(codigo, nombre, precioUnitario, descuento, precioConDesc));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

}
