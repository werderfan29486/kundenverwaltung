package kundenverwaltung;

import java.util.*;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.ArrayListMultimap;

public class Termine {


    //für Methode terminErstellenNeu
    ListMultimap<Kunde, String> terminliste = ArrayListMultimap.create();

    //für Methode terminErstellenAlt
    Map<Kunde, List<String>> kundentermine = new HashMap<Kunde, List<String>>();  //--> bleibt


    public void terminErstellen(Kunde kunde, String datum) {

        Optional<Kunde> existingKunde = Optional.empty();

        if (kundentermine.isEmpty()) {
            List<String> daten = new ArrayList<String>();
            daten.add(datum);
            kundentermine.put(kunde, daten);
        } else {
            for (Map.Entry<Kunde, List<String>> entry : kundentermine.entrySet()) {
                if (entry.getKey().uuid.equals(kunde.uuid)) {
                    entry.getValue().add(datum);
                    existingKunde = Optional.ofNullable(entry.getKey());
                } else {
                    existingKunde = Optional.empty();
                }
            }

            if(existingKunde.isEmpty()) {
                List<String> daten = new ArrayList<>();
                daten.add(datum);
                kundentermine.put(kunde, daten);
            }
        }
    }

    public void terminLöschen(Kunde kunde, String datum) {

        if (kundentermine.isEmpty()) {
            System.out.println("Terminliste ist leer");
        }
         else (!kundentermine.isEmpty()) {
            for (Map.Entry<Kunde, List<String>> entry : kundentermine.entrySet()) {
                if (entry.getKey().uuid != kunde.uuid) {
                    System.out.println("Kunde " + kunde.getName() + " nicht in der Terminliste");
                } else if (entry.getKey().uuid.equals(kunde.uuid)) {
                    kundentermine.remove(datum);
                    System.out.println("Termin am " + datum + " für Kunde " + kunde.getName() + " gelöscht");
                }
            }
        }
    }

    public void druckeTermine(Kunde kunde, Termine termin) {
        System.out.println(kunde.getName());
        System.out.println(termin.kundentermine.get(kunde));
    }



    public void TerminErstellenNeu(Kunde kunde, String datum) {
        terminliste.put(kunde, datum);
    }

}