package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    
    private static PostgreSQLConnection instance;
    
    private String driver   = "org.postgresql.Driver";
    private String user     = "postgres";
    private String password = "123";
    private String url      = "jdbc:postgresql://localhost:5432/project";
    private Connection conn = null;
    
    public PostgreSQLConnection() {instance = this;}
    
    public PostgreSQLConnection(String driver, String user, String password, String url){
        instance = this;
        this.driver = driver;
        this.user = user;
        this.password = password;
        this.url = url;
    }
    
    public Connection getConn() { return conn; }

    public static PostgreSQLConnection getInstance() { return PostgreSQLConnection.instance;}
    
    public void OpenPSQLConn() throws ClassNotFoundException, SQLException{
        Class.forName(this.driver);
        this.conn = DriverManager.getConnection(url, user, password);
    }
    
    public void ClosePSQLConn() throws SQLException{
        if(this.conn != null) this.conn.close();
    }
}
