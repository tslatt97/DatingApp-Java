package Server;

import java.sql.Connection;

/**
 * Her lager vi parametere som skal hentes fra tabell i databasen og sendes videre til Client
 *
 * @author Eksamensgruppe11
 */
public class Model {
    String firstname;
    String lastname;
    String gender;
    String age;
    String city;
    String phone;
    String interest1;
    String interest2;
    String interest3;
    Connection connection;


    public Model() {
        connection = Sqliteconnection.Connector();
        if (connection == null) System.exit(1);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }
}

