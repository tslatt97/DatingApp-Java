package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * Her har vi også en socket-kommunikasjon, for å kunne selektere ut data fra database via server.
 *
 * @author Eksamensgruppe11
 */
public class sokeConnection {
    public static ArrayList<Model> connection(List<String> params) {
        ArrayList<Model> list = new ArrayList<>();
        try(Socket socket = new Socket("127.0.0.1", 7000)) {
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            String response;


            Scanner scanner = new Scanner(System.in);
            Iterator<String> iterator = params.iterator();
            // Oppdatert echoString for å få med LIKE på kjønn string og lagt til interesser 2 og interesser 3
            String echoString = "2SELECT a.Id,a.kjønn,a.alder,a.interesser1,a.interesser2,a.interesser3,a.bosted,a.tlf,a.fnavn FROM Bruker a WHERE a.alder BETWEEN '" + iterator.next() + "' and '" + iterator.next() + "' and a.kjønn LIKE '%"+iterator.next()+"%'" + "LIMIT " + iterator.next();


            stringToEcho.println(echoString);

            response = echoes.readLine();

            String[] brukere = response.split("#");
            for(String s : brukere){
                String[] felt = s.split(" ");
                Model tmp = new Model(Integer.parseInt(felt[0]), felt[1], felt[2], felt[3], felt[4],felt[5],felt[6],felt[7],felt[8]);
                list.add(tmp);
            }

            // Sorterer listen basert på interesse alfabetiskt
            Collections.sort(list, new Comparator<Model>() {
                @Override
                public int compare(Model o1, Model o2) {
                    return o1.getInteresse().compareTo(o2.getInteresse());
                }
            });

            stringToEcho.println("exit");





        } catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }
        return list;
    }
}
