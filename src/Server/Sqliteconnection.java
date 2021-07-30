package Server;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * sqliteconnection.java
 * Her kobler vi serveren til databasen (Eksamen.db)
 *
 * @author Eksamensgruppe11
 */
public class Sqliteconnection {

    public static Connection Connector(){
        // CONNECTION TIL SQLITE DB

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Eksamen.db");
            return conn;
        }catch (Exception e){
            System.out.println("Kunne ikke koble til database i SQLite");
            return null;
        }
    }
}
