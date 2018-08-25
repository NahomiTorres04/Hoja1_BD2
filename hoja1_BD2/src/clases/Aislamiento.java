/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mynor
 */
public class Aislamiento {

    private final Connection con;
    private final conexion conexion;

    public Aislamiento() {
        conexion = new conexion();
        con = conexion.getConection();
    }

    public void SetAislamiento(String nivel) {
        try {
            String sql = "SET GLOBAL TRANSACTION ISOLATION LEVEL " + nivel + ";";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Aislamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAislamiento() {
        try {

            String sql = "SHOW VARIABLES LIKE 'tx_isolation';";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getString("Value");
        } catch (SQLException ex) {
            Logger.getLogger(Aislamiento.class.getName()).log(Level.SEVERE, null, ex);
            return "Conexión fallida";
        }
    }
}
