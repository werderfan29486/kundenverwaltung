package kundenverwaltung;

import java.util.*;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.ArrayListMultimap;
import java.text.SimpleDateFormat;

public class Termine {

    //für Methode terminErstellenAlt
    Map<Kunde, List<Date>> terminListe = new HashMap<>();


    //Methode mit
    public void terminErstellen(Kunde kunde, Date date) {

        Optional<Kunde> existingKunde = Optional.empty();

        if(terminListe.isEmpty()) {
            List<Date> termine = new ArrayList<>();
            termine.add(date);
            terminListe.put(kunde, termine);
        }else {
            for(Map.Entry<Kunde,List<Date>> entry : terminListe.entrySet()) {
                if(entry.getKey().getId().equals(kunde.getId())) {
                    entry.getValue().add(date);
                    existingKunde = Optional.ofNullable(entry.getKey());
                }else {
                    existingKunde = Optional.empty();
                }
            }

            if(existingKunde.isEmpty()){
                List<Date> termine = new ArrayList<>();
                termine.add(date);
                terminListe.put(kunde, termine);
            }

        }
    }

}