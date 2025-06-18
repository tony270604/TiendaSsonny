package modeloDao;

import confi.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.*;

public class VendedorDAO {

    Vendedor v;

    public boolean verificarLogin(Vendedor v) throws SQLException {
        String sql = "SELECT * FROM vendedor WHERE correo_ven = ? AND contra_ven = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, v.getCorreo_ven());
            pst.setString(2, v.getContra_ven());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    //llenar info del vendedor para mostrarlo en otro jframe
                    v.setNom_ven(rs.getString("nom_ven"));
                    return true; 
                } else {
                    return false; 
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar login: " + e.getMessage());
            return false;
        }
    }
        
}
