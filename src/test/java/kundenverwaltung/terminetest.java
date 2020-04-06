package kundenverwaltung;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class terminetest {

    Termine termin1 = new Termine();

    Kunde kunde = new Kunde("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Kunde kunde2 = new Kunde("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Kunde kunde3 = new Kunde("Jüttner", "Thomas", "Lessingstraße", "9", "64283");


    @Test
    public void testeTerminErstellen() {
        termin1.terminErstellen(kunde, "20. Juni");
        termin1.terminErstellen(kunde, "30. August");
        termin1.terminErstellen(kunde, "10. August");
        termin1.terminErstellen(kunde2, "15. März");
        termin1.terminErstellen(kunde2, "30. März");
        termin1.terminErstellen(kunde2, "30. August");
        System.out.println(kunde.getName());
        System.out.println(termin1.kundentermine.get(kunde));
        System.out.println(kunde2.getName());
        System.out.println(termin1.kundentermine.get(kunde2));
    }

    @Test
    public void testeTerminErstellenNeu() {
        termin1.TerminErstellenNeu(kunde, "20 Mai 2020 16:00");
        termin1.TerminErstellenNeu(kunde, "17 Juni 2030 14:00");
        termin1.TerminErstellenNeu(kunde2, "18 September 2010 17:00");
        termin1.TerminErstellenNeu(kunde3, "09 Januar 2009 20:00");
        System.out.println(termin1.m);
        System.out.println(kunde.getName());
        System.out.println(termin1.m.get(kunde));
        System.out.println(kunde2.getName());
        System.out.println(termin1.m.get(kunde2));
    }


}
