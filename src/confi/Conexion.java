package confi;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
     public static Connection getConexion(){ 
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3307/bd_sonny"; 
            String usr = "root";  
            String psw = "root"; 

            con = DriverManager.getConnection(url, usr, psw);
            System.out.println("Conexión correcta bdd ssonny.");
        } 
        catch (ClassNotFoundException ex) { 
            JOptionPane.showMessageDialog(null, "Error al cargar el driver: " + ex.toString());
        } 
        catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos: " + ex.toString());
        }
        return con;
    }
}     