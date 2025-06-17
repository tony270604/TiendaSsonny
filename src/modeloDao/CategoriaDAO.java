package modeloDao;

import confi.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class CategoriaDAO {

    ArrayList<Categoria> ved = new ArrayList<>();
    Categoria c;

    public ArrayList<Categoria> listarCategoria() {
        String sql = "select * from categoria";
        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setCod_cat(rs.getInt("cod_cat"));
                c.setNom_cat(rs.getString("nom_cat"));
                c.setDesc_cat(rs.getInt("desc_cat"));
                ved.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ved;
    }

    public boolean agregarCategoria(Categoria c) throws SQLException {
        String sql = "INSERT INTO categoria (cod_cat, nom_cat, desc_cat) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sql);) {

                // Inserta el producto
                pst.setInt(1, c.cod_cat);
                pst.setString(2, c.nom_cat);
                pst.setInt(3, c.desc_cat);
                pst.executeUpdate();

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Error al agregar categoria: " + e.getMessage());
                return false;
            }
        }
    }

    public DefaultTableModel listarCategoriaTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("cod_cat");
        modelo.addColumn("nom_cat");
        modelo.addColumn("desc_cat");

        String sql = "select * from categoria";

        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("cod_cat"),
                    rs.getString("nom_cat"),
                    rs.getString("desc_cat"),};
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar categorias tabla: " + e.getMessage());
        }

        return modelo;
    }

    public boolean editarCategoria(Categoria c) throws SQLException {
        String sql = "UPDATE categoria SET nom_cat = ?, desc_cat = ? WHERE cod_cat = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sql);) {
                // Actualiza datos del producto
                pst.setString(1, c.nom_cat);
                pst.setInt(2, c.desc_cat);
                pst.setInt(3, c.cod_cat);
                pst.executeUpdate();
                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Error al editar categoria: " + e.getMessage());
                return false;
            }
        }
    }

    public boolean eliminarCategoria(String cod_cat) throws SQLException {
        String sql = "DELETE FROM categoria WHERE cod_cat = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, cod_cat);
                pst.executeUpdate();
                con.commit();
                return true; 

            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(
                        null,
                        "Error al eliminar la categoría: " + e.getMessage()
                );
                return false;
            }
        }
    }

}
