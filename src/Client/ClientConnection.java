package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 * Denne klassen tar for seg klienten sin kobling til databasen (Eksamen.db)
 * Den er også ansvarlig for å sende INSERT QUERIES til databasen slik at brukeren kan registrer seg i systemet
 *
 *
 *@author Eksamensgruppe11
 */
public class ClientConnection {
    /**
     * @param params inneholder verdier fra registreringen til brukeren, verdiene blir sendt til en iterator som da leser dem inn i spørringen som blir sendt til databasen
     * @throws SQLException
     */
    public static void connection(List<String> params) throws SQLException {
        try(Socket socket = new Socket("127.0.0.1", 7000)){
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            String response;

            Scanner scanner = new Scanner(System.in);
            Iterator<String> iterator = params.iterator();

            //  Nytt
            // String id = iterator.next();

            // Nytt
            String echoString = "1INSERT INTO Bruker(fnavn,enavn,alder,kjønn,interesser1,interesser2,interesser3,bosted,tlf,Img) VALUES('"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"','"+iterator.next()+"')";



            stringToEcho.println(echoString);

            stringToEcho.println("exit");
        } catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }





        // HENTE ID'er her, istedetfor på serverside


            String url = "jdbc:sqlite:Eksamen.db"; // URLen til Database filen, som skal ligge i prosjektmappen

            Connection con = DriverManager.getConnection(url);
            Statement stmt = null;
            ResultSet response1 = null;
            Writer output = null;
            StringBuilder responseString = new StringBuilder();


    try{
                stmt = con.createStatement();

                // Nytt
                response1 = stmt.executeQuery("select id from bruker order by id desc limit 1");

                String tekst = response1.getInt("Id") + "";
                System.out.println(tekst);


                File file = new File("Users.txt");
                output = new BufferedWriter(new FileWriter(file));
                output.write(tekst);

                con.close();
                output.close();

            }catch (SQLException | IOException e){
                System.out.println("Insert went wrong:");
                System.out.println(e.getMessage());
            }
       }
 }

