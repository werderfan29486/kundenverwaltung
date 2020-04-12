package customerManagementSoftware;

import java.util.Scanner;

public class CreateDate {

    private String day;
    private String month;
    private String year;
    private String hours;
    private String minutes;

    public CreateDate(String day, String month, String year, String hours, String minutes) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static CreateDate inputDate (final Scanner scanner) {
        boolean isTrue = true;
        String day = dateValues(scanner, isTrue, "Tag eingeben: ", "Bitte 2 Ziffern zwischen 01 und 31 eingeben: ", "^(?:0*[1-9]|[12][0-9]|3[01])$" );
        String month = dateValues(scanner, isTrue, "Monat eingeben ", "Bitte 2 Ziffern zwischen 01 und 12 eingeben: ", "0*([1-9]|1[0-2])");
        String year = dateValues(scanner, isTrue, "Jahr eingeben ", "Bitte eine Jahreszahl zwischen 2020 und 2099 angeben: ", "0*(20[2-8][0-9]|209[0-9])" );
        String hours = dateValues(scanner, isTrue, "Stunden eingeben ", "Bitte eine Zahl zwischen 0 und 23 eingeben: ", "0*([0-9]|1[0-9]|2[0-3])");
        String minutes = dateValues(scanner, isTrue, "Minuten eingeben ", "Bitte Zahl zwischen 00 und 60 eingeben ", "0*([0-9]|[1-5][0-9]|60)" );

        return new CreateDate(day, month, year, hours, minutes);
    }

    public static String dateValues(Scanner scanner, boolean isTrue, String expression1, String expression2, String regex) {
        String dateExpression;
        do {
            if (isTrue) {
                System.out.println(expression1);
                dateExpression = scanner.next();
                isTrue = false;
            } else {
                System.out.println(expression2);
                dateExpression = scanner.next();
            }
        }
        while (!dateExpression.matches(regex));
        return dateExpression;
    }

    public String getDate() {
        return day + "." + month + "." + year + " " + hours + ":" + minutes + " Uhr";
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }
}
