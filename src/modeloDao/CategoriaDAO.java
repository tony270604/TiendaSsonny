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
}
