package kundenverwaltung;
import java.util.UUID;

public class Kunde {

    final String uuid = UUID.randomUUID().toString().replace("-", "");
    private String id;
    private String name;
    private String vorname;
    private String strasse;
    private String hausnummer;
    private String plz;

    public Kunde(String name, String vorname, String strasse, String hausnummer, String plz) {

        this.id = uuid;
        this.name = name;
        this.vorname = vorname;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }


    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getPlz() {
        return plz;
    }

}
