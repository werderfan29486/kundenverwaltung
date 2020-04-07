package kundenverwaltung;

import java.util.Map;

public class Rechnung {

    private int stundenpreis = 80;
    private double halberstundenpreis = 40.00;
    private int anzahlStunden;
    private static String rechnungsnummer;
    private static int count = 0;

    Konto konto = new Konto();

    public Rechnung() {
        ++count;
        String formatValue = String.format("%03d", count);
        this.rechnungsnummer = formatValue;
    }

    public double errechneGesamtBetrag(Kunde kunde, Termine terminliste) {
        anzahlStunden = terminliste.termineGesamt(kunde);
        return anzahlStunden*stundenpreis;
    }

    public void generiereRechnung(Kunde kunde, Termine terminliste) {
        System.out.println("Rechnungsnummer: " + rechnungsnummer);
        System.out.println("Kundennummer:   " + kunde.getKundennummer() + "              Name: " + kunde.getVorname() + " " +kunde.getName());
        anzahlStunden = terminliste.termineGesamt(kunde);
        System.out.println("-----------------------------------------------------");
        System.out.println("Anzahl der Stunden:                               " + anzahlStunden);
        System.out.println("Stundenpreis:                                    "+ stundenpreis);
        System.out.println("-----------------------------------------------------");
        System.out.println("                                  Gesamtbetrag: " + errechneGesamtBetrag(kunde, terminliste));
    }

    public void rechnungBezahlt(Kunde kunde, Termine kundentermine) {
       konto.kontostand += errechneGesamtBetrag(kunde, kundentermine);
        System.out.println("Kontostand " + konto.getKontostand());
        kundentermine.kundentermine.get(kunde).clear();
    }

}
