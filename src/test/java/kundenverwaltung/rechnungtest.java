package kundenverwaltung;

import org.junit.jupiter.api.Test;

public class rechnungtest {

    Kunde kunde = new Kunde("Schwarck", "Alex", "An der Fuchsenhütte", "27", "64380");
    Kunde kunde2 = new Kunde("Gantzert", "Sega", "Auf der Schmelz", "27", "64380");
    Kunde kunde3 = new Kunde("Jüttner", "Thomas", "Lessingstraße", "9", "64283");

    Rechnung rechnung1 = new Rechnung();
    Termine terminliste = new Termine();

    @Test
    public void testeErrechneGesamtBetrag() {
        terminliste.terminErstellen(kunde, "20. Januar");
        terminliste.terminErstellen(kunde, "31. März");
        terminliste.terminErstellen(kunde, "20. Oktober");
        System.out.println(rechnung1.errechneGesamtBetrag(kunde, terminliste));
        terminliste.terminLöschen(kunde, "20. Januar");
        System.out.println(rechnung1.errechneGesamtBetrag(kunde, terminliste));
    }

    @Test
    public void testeGeneriereRechnung() {
        terminliste.terminErstellen(kunde, "20. Januar");
        terminliste.terminErstellen(kunde, "31. März");
        terminliste.terminErstellen(kunde, "20. Oktober");
        rechnung1.generiereRechnung(kunde, terminliste);
    }

    @Test
    public void testeRechnungBezahlt() {
        terminliste.terminErstellen(kunde, "20. Januar");
        terminliste.terminErstellen(kunde, "31. März");
       // terminliste.terminErstellen(kunde2, "24. Februar");
        //terminliste.termineGesamt(kunde);
        rechnung1.rechnungBezahlt(kunde, terminliste);
        //System.out.print(terminliste.termineGesamt(kunde));
        //rechnung1.rechnungBezahlt(kunde2,terminliste);
      // terminliste.terminErstellen(kunde, "20. Januar");

      // rechnung1.rechnungBezahlt(kunde, terminliste);
    }


}
