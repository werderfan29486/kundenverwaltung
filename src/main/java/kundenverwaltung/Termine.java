package kundenverwaltung;

import java.util.*;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.ArrayListMultimap;
import java.text.SimpleDateFormat;

public class Termine {


    //für Methode terminErstellenNeu
    ListMultimap<Kunde, String> m = ArrayListMultimap.create();

    //für Methode terminErstellenAlt
    Map<Kunde, List<String>> kundentermine = new HashMap<Kunde, List<String>>();  //--> bleibt


    public void terminErstellen(Kunde kunde, String datum) {

        if (kundentermine.isEmpty()) {
            List<String> daten = new ArrayList<String>();
            daten.add(datum);
            kundentermine.put(kunde, new LinkedList<>(daten));
        } else {
            for (Map.Entry<Kunde, List<String>> entry : kundentermine.entrySet()) {
                if (entry.getKey().uuid.equals(kunde.uuid)) {
                    entry.getValue().add(datum);
                } else {
                    List<String> daten = new ArrayList<>();
                    daten.add(datum);
                    kundentermine.put(kunde, new LinkedList<>(daten));
                }
            }
        }
    }



    public void TerminErstellenNeu(Kunde kunde, String datum) {
        m.put(kunde, datum);
    }

}