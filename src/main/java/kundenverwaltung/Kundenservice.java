package kundenverwaltung;

import java.util.*;

public class Kundenservice {


    List<Kunde> kundenliste = new ArrayList<Kunde>();


    public void anlegen(Kunde kunde) {
        kundenliste.add(kunde);
    }

    public void updateKunde(Kunde kunde) {

        for (int i = 0; i < kundenliste.size(); i++) {
           //sich den Kunden holen, bei dem sich was geändert hat
            Kunde kunde1 = kundenliste.get(i);
            if (kunde1.getId() == kunde.getId()) {
                if (kunde1.getName() != kunde.getName()) {
                    kunde1.setName(kunde.getName());
                    if (kunde1.getPlz() != kunde.getPlz()) {
                        kunde1.setPlz(kunde.getPlz());
                    }
                    if (kunde1.getVorname() != kunde.getVorname()) {
                        kunde1.setVorname(kunde.getPlz());
                    }
                    if (kunde1.getStrasse() != kunde.getStrasse()) {
                        kunde1.setStrasse(kunde.getStrasse());
                    }
                    if (kunde1.getHausnummer() != kunde.getHausnummer()) {
                        kunde1.setHausnummer(kunde.getHausnummer());
                    }
                }
            }
        }
    }

        public void druckeDatensatz(Kunde kunde) {
            System.out.println("Kunde mit der ID: " + kunde.getId());
            System.out.println("Name: " + kunde.getName());
            System.out.println("Vorname " + kunde.getVorname());
            System.out.println("Straße: " + kunde.getStrasse());
            System.out.println("Hausnummer " + kunde.getHausnummer());
            System.out.println("Plz:  " + kunde.getPlz());
        }

        public boolean durchsuchen(String suchwort) {
        for (int i = 0; i < kundenliste.size(); i++) {
            Kunde kunde1 = kundenliste.get(i);
            if (kunde1.getName() == suchwort) {
                druckeDatensatz(kunde1);
            } else if (kunde1.getVorname() == suchwort) {
                druckeDatensatz(kunde1);
            } else if (kunde1.getStrasse() == suchwort) {
                druckeDatensatz(kunde1);
            } else if (kunde1.getPlz() == suchwort) {
                druckeDatensatz(kunde1);
            } else if (kunde1.getHausnummer() == suchwort) {
                druckeDatensatz(kunde1);
            }

        }
        return true;
    }

    public void löschen(Kunde kunde) {
        for (int i = 0; i < kundenliste.size(); i++) {
            if (kundenliste.get(i).getId() == kunde.getId()) {
                kundenliste.remove(i);
            }
        }
    }

    public void allekunden() {
        for (int i = 0; i < kundenliste.size(); i++) {
            Kunde kunde1 = kundenliste.get(i);
            System.out.println("Kundennummer " + kunde1.getKundennummer());
            System.out.println("Name: " + kunde1.getName());
            System.out.println("Vorname: " + kunde1.getVorname());
            System.out.println("Straße: " + kunde1.getStrasse());
            System.out.println("Hausnummer: " + kunde1.getHausnummer());
            System.out.println("PLZ: " + kunde1.getPlz());
            System.out.println();

        }
    }

}


