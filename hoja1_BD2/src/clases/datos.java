/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Héctor
 */
public class datos {

    private Connection con = null;
    private final conexion conexion;

    public datos() {
        conexion = new conexion();
        con = conexion.getConection();
    }

    public void Start_Transaction() {
        try {
            String query = "Start Transaction;";
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Commit_Rollback(boolean estado) {
        try {
            String query = (estado) ? "COMMIT" : "ROLLBACK";
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertar_estudiante(String nombres, String apellidos, int edad, String carne) {
        boolean guardado = false;
        try {
            String query = "INSERT INTO estudiante(nombre, apellido, edad, carne) VALUES (?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nombres);
            pst.setString(2, apellidos);
            pst.setInt(3, edad);
            pst.setString(4, carne);
            int res = pst.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante guardado");
                guardado = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar estudiante");
                guardado = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardado;
    }

    public DefaultTableModel getInventario(JTable tabla) {

        try {
            String titulos[] = new String[4];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT * FROM estudiante";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[4];
            while (rs.next()) {
                registros[0] = rs.getString("nombre");
                registros[1] = rs.getString("apellido");
                registros[2] = rs.getString("edad");
                registros[3] = rs.getString("carne");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
