/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava;

import Connect.PostgreSQLConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class ProjectJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        PostgreSQLConnection psql = new PostgreSQLConnection();
        
        try {
            PostgreSQLConnection.getInstance().OpenPSQLConn();
            String query = "INSERT INTO usuario(ysername, senha) VALUES(?, ?);";
            PreparedStatement stmte = PostgreSQLConnection.getInstance().getConn().prepareStatement(query);
            stmte.setString(1, "tonho");
            stmte.setString(2, "top");
            stmte.execute();
            PostgreSQLConnection.getInstance().ClosePSQLConn();
        } catch (SQLException ex) {
            Logger.getLogger(ProjectJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
