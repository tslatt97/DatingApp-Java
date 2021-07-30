package Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;


/**
 * Her bestemmes det meste som skjer i clienten. Alle knapper i applikasjonen har en egen metode her.
 * Det har vært noe trøbbel med socket-kommunikasjon. Så vi har vært nødt for å koble
 * direkte opp mot database i noen av metodene.
 *
 * @author Eksamensgruppe11
 */
public class Controller implements Initializable {


    /**
     * Fornavnet til brukeren
     */
    @FXML
    private TextField Firstname;

    /**
     * Etternavner til brukeren
     */
    @FXML
    private TextField Lastname;

    /**
     * Alderen til brukeren
     */
    @FXML
    private TextField Age;

    /**
     * Kjønnet til brukeren
     */
    @FXML
    private ChoiceBox registerGender;

    /**
     * Interessen til brukeren
     */
    @FXML
    private TextField Interest1;

    /**
     * Interessen til brukeren
     */
    @FXML
    private TextField Interest2;

    /**
     * Interessen til brukeren
     */
    @FXML
    private TextField Interest3;

    /**
     * Telefonnummeret til brukeren
     */
    @FXML
    private TextField phoneRegister;

    /**
     * Bostedet til brukeren
     */
    @FXML
    private TextField City;

    /**
     * Registrerings knappen
     */
    @FXML
    private Button Register;

    //Søke IDer

    /**
     * Kjønn på personer brukeren ønsker å se i søkefeltet
     */
    @FXML
    private ChoiceBox Gender;

    /**
     * Minimum alder på personer brukeren ønsker å se i søkefeltet
     */
    @FXML
    private TextField Age1;

    /**
     * Maks alder på personer brukeren ønsker å se i søkefeltet
     */
    @FXML
    private TextField Age2;

    /**
     * Antall personer brukeren ønsker å få fram i søke listen
     */
    @FXML
    private TextField Antall;

    /**
     * Fornavnet til personene brukeren får opp i søkefeltet
     */
    @FXML
    private String fnavn;

    /**
     * Telefon nummeret til personene brukeren får opp i søkefeltet
     */
    @FXML
    private String phone;

    /**
     * Inneholder fornavn og tlfnr til personen som har matchet med brukeren, teksten blir plassert i TextArea som ligger i "Hent informasjon" panelet
     */
    @FXML
    private TextArea matching;

    /**
     * Inneholder Fornavn og Tlf Nr til den personen brukeren velger i "Søk" listen
     */
    @FXML
    private TextArea textmatch;
    @FXML
    private TableView<Model> table;

    /**
     * Verdi til "Søke metoden", inneholder kjønnet til personen som dukker opp under søket
     */
    @FXML
    private TableColumn<Model, String> kjønnT;

    /**
     * Inneholder alderen til personene som dukker opp under søk
     */
    @FXML
    private TableColumn<Model, String> alderT;
    /**
     * Innerholder interessene til personene som dukker opp under søk
     */
    @FXML
    private TableColumn<Model, String> interesserT;
    @FXML
    private TableColumn<Model, String> getInteresserT2;
    @FXML
    private  TableColumn<Model, String> getGetInteresserT3;

    /**
     * Inneholder bostedet til personene som som opp under søk
     */
    @FXML
    private TableColumn<Model, String> bostedT;
    @FXML
    private Button search;
    @FXML
    private Button btn;
    @FXML
    private ImageView imgview1;

    @FXML
    private Button uploadimg;
    @FXML
    private TextField test;

    private InputStream input;


    private String msg;

    private ServerSocket serverSocket;
    private Socket socket;
    private ArrayList<String> params;
    private ArrayList<Model> list;
    ObservableList<Model> oblist = FXCollections.observableArrayList();


    public Controller() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Mulig connection her

    }


    /**
     * Metode for å legge til bilde i databasen slik at brukerene kan ha sitt eget bilde
     */
    @FXML
    public void uploadimage(){

        // Knapp for å legge til bilde

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(ext1, ext2);
        File file = fc.showOpenDialog(null);
        String im = file.toString();
        test.setText(im);
 }


    /**
     * Denne metoden blir brukt til å registrere ny bruker i programmet
     * metoden tar inn ulike verdier som da blir lagt inn i en Arraylist som blir
     * sendt til ClientConnection classen som derfra plasserer arraylisten inn i en iterator
     * som leser verdiene og legger dem inn i spørringen som blir sendt videre til databasen
     *
     * @throws SQLException kaste SQL exception om det er oppdaget feil
     */
    @FXML public void NewUser() throws SQLException {  // Metode til registrer knapp
        String firstname = Firstname.getText();
        String lastname = Lastname.getText();
        String age = Age.getText();
        String registergender = registerGender.getSelectionModel().getSelectedItem().toString();
        String interest1 = Interest1.getText();
        String interest2 = Interest2.getText();
        String interest3 = Interest3.getText();
        String userPhone = phoneRegister.getText();
        String city = City.getText();
        String img = test.getText();

        params = new ArrayList<String>();

        params.add(firstname);
        params.add(lastname);
        params.add(age);
        params.add(registergender);
        params.add(interest1);
        params.add(interest2);
        params.add(interest3);
        params.add(city);
        params.add(userPhone);
        params.add(img);


        ClientConnection.connection(params);
    }

    /**
     * Fyller søkelisten med personer som passer kriteriene som brukeren taster inn i søkefeltet
     * Henter input fra de ulike feltene i panelet og plasserer dem i en string som da blir lagt
     * inn i en arraylist(params)
     *
     * Tabellen som er i panelet blir da fylt med de personene som passer kriteriene til brukeren
     * og brukeren får da muligheten til å velge en person og se mer informasjon om de ønsker ved hjelp av GetShow() metoden
     */
    @FXML
    public void GetMatch() {     // Metode til søk knapp

        System.out.println("Starter GetMatch() ");
        String gender = Gender.getSelectionModel().getSelectedItem().toString();
        String age1 = Age1.getText();
        String age2 = Age2.getText();
        String antall = Antall.getText();
        

        if (age1.isEmpty())
            age1 = "15";
        if (age2.isEmpty())
            age2 = "99";
        if (antall.isEmpty())
            antall = "1";



        params = new ArrayList<String>();
        params.add(age1);
        params.add(age2);
        params.add(gender);
        params.add(antall);


        list = sokeConnection.connection(params);

        oblist.clear(); // refresher listen som blir lagt inn i tableview

        for (Model model : list) {
            oblist.add(model);

            kjønnT.setCellValueFactory(new PropertyValueFactory<>("kjønn"));
            alderT.setCellValueFactory(new PropertyValueFactory<>("alder"));
            interesserT.setCellValueFactory(new PropertyValueFactory<>("interesse"));
            interesserT.setCellValueFactory(new PropertyValueFactory<>("interesse2"));
            interesserT.setCellValueFactory(new PropertyValueFactory<>("interesse3"));
            bostedT.setCellValueFactory(new PropertyValueFactory<>("bosted"));

        }

        table.setItems(oblist);
        System.out.println("GetMatch() Ferdig");

    }


    /**
     * Denne metoden er ansvarlig for å vise brukeren informasjon om den personen de har valgt i "Søk" tabellen
     * @throws SQLException Unntaket som kastes når SQL Server returnerer en advarsel eller feil.
     */
    @FXML
    public void GetInfo() throws SQLException { // Metode til kontakt knapp
        System.out.println("Starter GetInfo()");
        Model model = table.getSelectionModel().getSelectedItem();
        String s = model.getTlf();
        String d = model.getFnavn();
        int i = model.getId();
        int l = model.getId();


        // Henter id fra tekstfil
        // Nytt
        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File("Users.txt")));

            String linje;
            linje = reader.readLine();
            i = Integer.parseInt(linje);
            System.out.println(linje);
            reader.close();
        }
        catch ( Exception e) {
            System.out.print(e.getMessage());
        }


        textmatch.setText("Fornavn: " + d + "\n" + "Tlf nr: " + s);


        String url = "jdbc:sqlite:Eksamen.db";

        Connection conn = DriverManager.getConnection(url);
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO LoggBruker(Id1, Id2,Fnavn,tlf) VALUES('" + i + "','"+l+"', '"+d+"' , '"+s+"')");
            conn.close();
            System.out.println("GetInfo() Ferdig");

        }    catch (SQLException e){
            System.out.println("Something went wrong, inserting to logg: ");
            System.out.println(e.getMessage());
        }
        // Kode for å hente ut bilde
        String url1 = "jdbc:sqlite:Eksamen.db";
        String response;
        Connection conn1 = DriverManager.getConnection(url1);


        Statement stmt1 = null;
        File file = null;

        // Nytt
        String q = "Select Img from Bruker Where Id=+"+l+"";

        try {
            stmt1 = conn1.createStatement();
            ResultSet rs = stmt1.executeQuery(q);
            System.out.println(rs);
            input = rs.getBinaryStream("Img");

            file = new File(rs.getString(1));

            Image imge = new Image(file.toURI().toURL().toExternalForm());
            imgview1.setImage(imge);

            // Close connection
            conn1.close();

        }catch (SQLException | MalformedURLException e){
            System.out.println("Something went wrong getting image");
            System.out.println(e.getMessage());
        }


    }


    /**
     * Denne metoden henter informasjon om de personene som har matchet men brukeren som velger å hente informasjon fra databasen
     * @throws SQLException Unntaket som kastes når SQL Server returnerer en advarsel eller feil.
     */
    @FXML
    public void GetShow() throws SQLException { // Metode til hent knapp



        String url = "jdbc:sqlite:Eksamen.db";

        Connection conn = DriverManager.getConnection(url);
        Statement stmt = null;
        int l=0;
        int i=0;

        // Nytt
        try {
            // System.out.println(l);
            BufferedReader reader = new BufferedReader(new FileReader(new File("Users.txt")));

            String linje;
            linje = reader.readLine();
            i = Integer.parseInt(linje);
            System.out.println(linje);

            reader.close();
        }
        catch ( Exception e) {
            System.out.print(e.getMessage());
        }

        try {
            System.out.print(i);
            stmt = conn.createStatement();
            // Nytt
             ResultSet rs = stmt.executeQuery("select distinct enavn,kjønn,bruker.fnavn,bruker.tlf from bruker inner join loggbruker on bruker.id = loggbruker.id2 where id1 = " + i  +";");

            String tekst1 = "";
            while( rs.next()) {
                tekst1 += rs.getString("Fnavn") + " tlf: " + rs.getString("tlf") + " \n";
            }
            matching.setText(tekst1);

            conn.close();

        }catch (SQLException e){

            System.out.println("Something went wrong, inserting to logg: ");
            System.out.println(e.getMessage());

        }
    }
}
