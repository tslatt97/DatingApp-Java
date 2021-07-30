package Server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Main.java
 * Her har vi lagt til Socket-kommunikasjon, slik at en kan hente data fra database og sendes videre til Client.
 * Samt at vi har metoder som mottar spørring fra applikasjon og returnerer tilbake svar.
 * Vi har også en metode som sorterer hvilken spørring som kommer fra Client. Samt en txt-fil som skrives ut med Id.
 *
 * @author Eksamensgruppe11
 */
public class Main {


    public static void main(String[] args) {

        while (true){
            connection();
        }

    }

    public static void connection(){
        System.out.println("Server Startet");
        try(ServerSocket serverSocket = new ServerSocket(7000)){
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                String echoString = input.readLine();
                String tmp = echoString.substring(0,1);
                String query = echoString.substring(1);


                if(echoString.equals("exit")){
                    socket.close();
                    break;
                } else if(tmp.equals("1")){
                    handlesave(query); // Setter inn registrert bruker
                }else if(tmp.equals("2")){
                    output.println(GetMatch(query));
                }
                output.println("From Server:  " + echoString);

            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Sql for å registrere bruker
    public static void handlesave(String query){
        Connection con = Sqliteconnection.Connector();
        Statement stmt = null;

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException e){
            System.out.println("Insert went wrong:");
            System.out.println(e.getMessage());
        }

        // Lagrer tekstfil
        returnRowid();
    }

    //  Henter ut data etter søk
    public static String GetMatch(String query){
        StringBuilder svar = new StringBuilder();
        Connection con = Sqliteconnection.Connector();
        Statement stmt = null;
        ResultSet response = null;

        try{
            stmt = con.createStatement();
            response = stmt.executeQuery(query);

            while (response.next()){
                        svar.append(response.getInt("Id")).append(" ")
                        .append(response.getString("kjønn")).append(" ")
                        .append(response.getString("alder")).append(" ")
                        .append(response.getString("interesser1")).append(" ")
                        .append(response.getString("interesser2")).append(" ")
                        .append(response.getString("interesser3")).append(" ")
                        .append(response.getString("bosted")).append(" ")
                        .append(response.getString("tlf")).append(" ")
                        .append(response.getString("fnavn")).append("#");
            }
            con.close();
        }catch (SQLException e){
            System.out.println("Select went wrong:");
            System.out.println(e.getMessage());
        }


        return svar.toString();
    }



     // Lagrer tekstfil
    public static void returnRowid(){
        Connection con = Sqliteconnection.Connector();
        Statement stmt = null;
        ResultSet response = null;
        Writer output = null;
        StringBuilder responseString = new StringBuilder();

 /*
        try{
            stmt = con.createStatement();
            response = stmt.executeQuery("SELECT Id FROM Bruker");

            while (response.next())
                responseString.append(response.getInt("Id") + ", ");
            System.out.println(responseString);
            con.close();

            File file = new File("Users.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(responseString.toString());

            output.close();

        }catch (SQLException | IOException e){
            System.out.println("Insert went wrong:");
            System.out.println(e.getMessage());
        } */
    }
}

