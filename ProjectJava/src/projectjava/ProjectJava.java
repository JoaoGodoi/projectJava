package projectjava;

import Connect.PostgreSQLConnection;
import DAO.UsuarioDAO;
import View.f_login;


public class ProjectJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PostgreSQLConnection psql = new PostgreSQLConnection();
        UsuarioDAO uDAO = new UsuarioDAO();
        
        f_login fl = new f_login();
        fl.setVisible(true);
    }
    
}
