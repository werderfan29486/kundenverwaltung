package kundenverwaltung;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Termin {

    String pattern;
    SimpleDateFormat simpleDateFormat;
    Date datum;

    public Termin(String pattern, SimpleDateFormat simpleDateFormat, Date datum) {
        this.pattern = pattern;
        this.simpleDateFormat = simpleDateFormat;
        this.datum = datum;
    }
}
