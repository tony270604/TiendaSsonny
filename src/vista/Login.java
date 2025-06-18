package vista;

import java.awt.Panel;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Vendedor;
import modeloDao.VendedorDAO;
import java.sql.SQLException;
import javax.swing.JLayeredPane;
import modelo.NombreUsuario;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents(); 
        this.setLocationRelativeTo(null); 
        //Para superponer el jframa
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(Login, JLayeredPane.POPUP_LAYER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Celeste = new javax.swing.JPanel();
        Morado = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        Login = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        txtContra = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Celeste.setBackground(new java.awt.Color(102, 204, 255));
        Celeste.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Celeste, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 480));
        Celeste.getAccessibleContext().setAccessibleName("");

        Morado.setBackground(new java.awt.Color(153, 153, 255));
        Morado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("vendedor1@tienda.com\npass1234\n");
        Morado.add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 140, 40));

        getContentPane().add(Morado, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 300, 480));

        Login.setBackground(new java.awt.Color(255, 255, 255));
        Login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("LOGIN");
        Login.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 121, 44));

        jLabel2.setText("Correo:");
        Login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 98, 44, -1));

        jLabel3.setText("Contraseña:");
        Login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 172, 72, -1));

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        Login.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 130, 210, 25));

        btnIngresar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        Login.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 258, 91, -1));

        txtContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraActionPerformed(evt);
            }
        });
        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraKeyPressed(evt);
            }
        });
        Login.add(txtContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 200, 210, 25));

        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 280, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        txtContra.requestFocus();
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraActionPerformed

    private void txtContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            btnIngresar.requestFocus();
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txtContraKeyPressed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String correo = txtCorreo.getText();
        String contra = txtContra.getText();
        Vendedor v = new Vendedor();
        VendedorDAO vd = new VendedorDAO();
        v.setCorreo_ven(correo);
        v.setContra_ven(contra);

        try {
            if (vd.verificarLogin(v)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                NombreUsuario.nombre_Usuario = v.getNom_ven();
                Principal principal = new Principal();
                principal.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
        }

    }//GEN-LAST:event_btnIngresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Celeste;
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Morado;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
