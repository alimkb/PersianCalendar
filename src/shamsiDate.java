
import java.util.Date;


public class shamsiDate {


    public static void main(String[] args) {


        PersianCalendar cal = new PersianCalendar();
        String tarikh ;

        Date date = new Date();
        //tarikh = date.MiladiToShamsi(dt);
        tarikh = cal.miladiToShamsi("06/02/2020");
        System.out.println("Shamsi Date Is : "+tarikh);

        System.out.println("Weekday is : "+cal.weekDay(2001,02,22));

    }


}
