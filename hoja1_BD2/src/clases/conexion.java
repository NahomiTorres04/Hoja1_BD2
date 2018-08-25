package clases;

import java.sql.DriverManager;

/**
 *
 * @author alex
 */
public class conexion {
    private String nombreBD="multitor_Hoja1BD2";
    private String usuario="root";
    //private String password="";
    //private String url="jdbc:mysql://localhost:3306/"+nombreBD+"?autoReconnect=true&useSSL=false";
    private String password="1234";
    private String url="jdbc:mysql://localhost:3307/"+nombreBD+"?autoReconnect=true&useSSL=false";
    
    public com.mysql.jdbc.Connection getConection()
    {
        com.mysql.jdbc.Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, usuario, password);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}

