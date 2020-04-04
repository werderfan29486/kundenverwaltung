package kundenverwaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.ArrayListMultimap;
import java.text.SimpleDateFormat;

public class Termine {

    String pattern = "dd MMMM yyyy hh:mm";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date1 = simpleDateFormat.format(new Date());

    //für Methode terminErstellenNeu
    ListMultimap<Kunde, String> m = ArrayListMultimap.create();

    //für Methode terminErstellenAlt
    Map<Kunde, List<Date>> kunden = new HashMap<Kunde, List<Date>>();
    List<Date> daten = new ArrayList<Date>();

    public String erstelleDatum(String pattern) {
        this.pattern = pattern;
        return pattern;
    }

    //Methode mit
    public void terminErstellenAlt(Kunde kunde, Date date) {
        daten.add(date);
        kunden.put(kunde, daten);
    }

    public void TerminErstellenNeu(Kunde kunde, String pattern) {
        String date1 = erstelleDatum(pattern);
        m.put(kunde, date1);
    }

}