package Client;

import java.util.Comparator;


/**
 * Her tar vi inn paramtere, som skal brukes for å plassere data fra socket og database. constructor, getters og setter.
 *
 * @author Eksamensgruppe11
 */
public class Model implements Comparable<Model>, Comparator<Model> {
    /**
     * Verdi for kjønnet til brukeren
     */
    String kjønn;

    /**
     * Verdi for alderen til brukeren
     */
    String alder;

    /**
     * Verdi for interessene til brukeren
     */
    String interesse;
    String interesse2;
    String interesse3;

    /**
     * Verdi for bostedet til brukeren
     */
    String bosted;

    /**
     * Verdi for telefonnummeret til brukeren
     */
    String tlf;

    /**
     * Verdi for fornavnet til brukeren
     */
    String fnavn;

    /**
     * verdi for den unike IDen til brukeren
     */
    int id;


    /**
     * @param             id den unike IDen til brukeren, brukes til å identifisere brukeren
     * @param kjønn       Kjønnet til brukeren
     * @param alder       Alderen til brukeren
     * @param interesse   Interessen til brukeren
     * @param interesse2  Interessen til brukeren
     * @param interesse3  Interessen til brukeren
     * @param bosted      Bostedet til brukeren
     * @param tlf         Telefonnummeret til brukeren
     * @param fnavn       Fornavnet til brukeren
     */
    public Model(int id, String kjønn, String alder, String interesse, String interesse2, String interesse3, String bosted, String tlf, String fnavn){
        this.id = id;
        this.kjønn = kjønn;
        this.alder = alder;
        this.interesse = interesse;
        this.interesse2 = interesse2;
        this.interesse3 = interesse3;
        this.bosted = bosted;
        this.tlf = tlf;
        this.fnavn = fnavn;

    }


    // Getters og setters for verdiene som blir brukt under registrering av ny bruker
    public String getFnavn() {
        return fnavn;
    }

    public void setFnavn(String fnavn) {
        this.fnavn = fnavn;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKjønn() {
        return kjønn;
    }

    public void setKjønn(String kjønn) {
        this.kjønn = kjønn;
    }

    public String getAlder() {
        return alder;
    }

    public void setAlder(String alder) {
        this.alder = alder;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public String getInteresse2() { return interesse2; }

    public void setInteresse2(String interesse2) { this.interesse2 = interesse2; }

    public String getInteresse3() { return interesse3; }

    public void setInteresse3(String interesse3) { this.interesse3 = interesse3; }

    public String getBosted() {
        return bosted;
    }

    public void setBosted(String bosted) {
        this.bosted = bosted;
    }

    @Override
    public int compareTo(Model o) {   //  For øyeblikket ubrukt
        return this.getInteresse().compareTo(o.getInteresse());
    }

    @Override
    public int compare(Model o1, Model o2) {   //  For øyeblikket ubrukt

        return 0;
    }

}

