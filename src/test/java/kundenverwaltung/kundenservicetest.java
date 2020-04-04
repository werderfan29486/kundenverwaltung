package kundenverwaltung;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class kundenservicetest {

    List<Kunde> kundenliste = new ArrayList<Kunde>();
    Kunde kunde1 = new Kunde("Gantzert", "Sebastian", "Auf der Schmelz", "30", "64380");
    Kunde kunde2 = new Kunde("Schwarck", "Alex", "Fuchsenhütte", "27", "64380");
    Kunde kunde3 = new Kunde("Jüttner", "Thomas", "Lessingstraße", "9", "64283");
    Kunde kunde4 = new Kunde("Schwarck", "Martin", "Keineahnung", "11", "64380");

    Kundenservice kundenservice = new Kundenservice();

    @Test
    public void testkundenanlegen() {
        kundenservice.anlegen(kunde1);
        System.out.print(kundenservice.kundenliste);
    }

    @Test
    public void testkundenlöschen() {
        kundenservice.anlegen(kunde1);
        kundenservice.anlegen(kunde2);
        System.out.println("Kunden in der Liste: " + kundenservice.kundenliste.size());
        kundenservice.löschen(kunde2);
        if (kundenservice.kundenliste.isEmpty()) {
            System.out.println("gelöscht");
        } else {
            System.out.println("Kunden in der Liste: " + kundenservice.kundenliste.size());
        }
        kundenservice.allekunden();
    }

    @Test
    public void testedurchsuchen() {
        kundenservice.anlegen(kunde4);
        kundenservice.anlegen(kunde2);
        kundenservice.durchsuchen("27");
    }

    @Test
    public void testeDruckeKunde() {
        kundenservice.anlegen(kunde1);
        kundenservice.anlegen(kunde2);
        kundenservice.druckeDatensatz(kunde1);
    }

    @Test
    public void testeupdateKunde() {
        kundenservice.anlegen(kunde1);

        System.out.println(kundenservice.kundenliste.get(0).getId());
        System.out.println(kundenservice.kundenliste.get(0).getName());
        System.out.println(kundenservice.kundenliste.get(0).getPlz());
        System.out.println(kundenservice.kundenliste.get(0).getHausnummer());
        System.out.println(kundenservice.kundenliste.get(0).getStrasse());

        kunde1.setName("Schwarck");
        kunde1.setVorname("Sebastian");
        kunde1.setPlz("64380");
        kunde1.setHausnummer("30");
        kunde1.setStrasse("Auf der Schmelz");

        kundenservice.updateKunde(kunde2);
        System.out.println(kundenservice.kundenliste.get(0).getId());
        System.out.println(kundenservice.kundenliste.get(0).getName());
        System.out.println(kundenservice.kundenliste.get(0).getPlz());
        System.out.println(kundenservice.kundenliste.get(0).getHausnummer());
        System.out.println(kundenservice.kundenliste.get(0).getStrasse());
    }

    @Test
    public void testallekunden() {
        kundenservice.anlegen(kunde1);
        kundenservice.anlegen(kunde2);
        kundenservice.löschen(kunde2);
        kundenservice.anlegen(kunde3);
        kundenservice.anlegen(kunde4);
        kundenservice.allekunden();
    }

}
