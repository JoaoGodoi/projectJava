package DAO;

import Beans.Usuario;
import Connect.PostgreSQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    private static UsuarioDAO instance;

    public UsuarioDAO() { instance = this;}

    public static UsuarioDAO getInstance() { return UsuarioDAO.instance;}
    
    public static String SenhaMD5(String senha) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(senha.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) 
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) { System.err.println(e.getMessage());}
        return null;
    } 
    
    public boolean EmailUsernameCadastrado(String email, String username) throws ClassNotFoundException, SQLException{
        String query = "SELECT * FROM usuario u WHERE (u.email = ? OR u.username = ?);";
        PostgreSQLConnection.getInstance().OpenPSQLConn();
        PreparedStatement stmte = PostgreSQLConnection.getInstance().getConn().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmte.setString(1, email);
        stmte.setString(2, username);
        ResultSet rs = stmte.executeQuery();
        PostgreSQLConnection.getInstance().ClosePSQLConn();
        rs.first();
        if(rs.getRow() == 1)return true;
        else                return false;
    }
    
    public void AdicionaUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String query = "INSERT INTO usuario(username, email, senha) VALUES(?,?,?);";
        PostgreSQLConnection.getInstance().OpenPSQLConn();
        PreparedStatement stmte = PostgreSQLConnection.getInstance().getConn().prepareStatement(query);
        stmte.setString(1, u.getUsername());
        stmte.setString(2, u.getEmail());
        stmte.setString(3, u.getSenha());
        stmte.execute();
        PostgreSQLConnection.getInstance().ClosePSQLConn();
    }
    
    public Usuario LoginUsuario(String email, String senha) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM usuario u WHERE u.email = ? AND u.senha = ?;";
        PostgreSQLConnection.getInstance().OpenPSQLConn();
        PreparedStatement stmte = PostgreSQLConnection.getInstance().getConn().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmte.setString(1, email);
        stmte.setString(2, senha);
        ResultSet rs = stmte.executeQuery();
        rs.first();
        PostgreSQLConnection.getInstance().ClosePSQLConn();
        Usuario usuario = null;
        if(rs.getRow() == 0)
            return usuario;
        else{
            usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setUsername(rs.getString("username"));
            return usuario;
        }
    }
    
}
