package kundenverwaltung;

public class Rechnung {

    private double stundenpreis = 80.00;
    private double halberstundenpreis = 40.00;
    private double anzahlStunden;


    public void errechneGesamtBetrag(Kunde kunde, Termine terminliste) {
        anzahlStunden = terminliste.termineGesamt(kunde);
        System.out.println("Gesamtrechungsbetrag für " + kunde.getName() + ": " + anzahlStunden * stundenpreis);
    }


}
