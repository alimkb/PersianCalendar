import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.text.SimpleDateFormat;



public class PersianCalendar {


    public String weekDay(int year,int month,int day) {


        Calendar calendar = Calendar.getInstance();

        calendar.set(year,month,day);
        int a = calendar.get(Calendar.DAY_OF_WEEK);
        String res;
        switch(a) {
            case 1 : res = "یکشنبه"; break;
            case 2 : res = "دوشنبه"; break;
            case 3 : res = "سه شنبه"; break;
            case 4 : res = "چهارشنبه"; break;
            case 5 : res = "پنجشنبه"; break;
            case 6 : res = "جمعه"; break;
            case 7 : res = "شنبه"; break;
            default: res = ""; break;
        }

        return res;

    }


    public String miladiToShamsi(String date){

        // date format should be : "31/12/1998"
        Date dt;
        try {
            dt = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        }catch(Exception e) {
            return "false";
        }
        return miladiToShamsi(dt);

    }

    public String miladiToShamsi(Date date){

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();

        return miladiToShamsi(month,day,year);

    }

    public String miladiToShamsi(int iMiladiMonth,int iMiladiDay,int iMiladiYear) {



        int  shamsiDay, shamsiMonth, shamsiYear;
        int  dayCount,farvardinDayDiff,deyDayDiff ;
        int  sumDayMiladiMonth[] = {0,31,59,90,120,151,181,212,243,273,304,334};
        int  sumDayMiladiMonthLeap[]= {0,31,60,91,121,152,182,213,244,274,305,335};


        String[] res = new String[3];

        farvardinDayDiff=79;


        if (miladiIsLeap(iMiladiYear))
        {
            dayCount = sumDayMiladiMonthLeap[iMiladiMonth-1] + iMiladiDay;
        }
        else
        {
            dayCount = sumDayMiladiMonth[iMiladiMonth-1] + iMiladiDay;
        }
        if((miladiIsLeap(iMiladiYear - 1)))
        {
            deyDayDiff = 11;
        }
        else
        {
            deyDayDiff = 10;
        }
        if (dayCount > farvardinDayDiff)
        {
            dayCount = dayCount - farvardinDayDiff;
            if (dayCount <= 186)
            {
                switch (dayCount%31)
                {
                    case 0 :
                        shamsiMonth = dayCount / 31;
                        shamsiDay = 31;
                        break;
                    default:
                        shamsiMonth = (dayCount / 31) + 1;
                        shamsiDay = (dayCount%31);
                        break;
                }
                shamsiYear = iMiladiYear - 621;
            }
            else
            {
                dayCount = dayCount - 186;
                switch (dayCount%30)
                {
                    case 0 :
                        shamsiMonth = (dayCount / 30) + 6;
                        shamsiDay = 30;
                        break;
                    default:
                        shamsiMonth = (dayCount / 30) + 7;
                        shamsiDay = (dayCount%30);
                        break;
                }
                shamsiYear = iMiladiYear - 621;
            }
        }
        else
        {
            dayCount = dayCount + deyDayDiff;

            switch (dayCount%30)
            {
                case 0 :
                    shamsiMonth = (dayCount / 30) + 9;
                    shamsiDay = 30;
                    break;
                default:
                    shamsiMonth = (dayCount / 30) + 10;
                    shamsiDay = (dayCount%30);
                    break;
            }
            shamsiYear = iMiladiYear - 622;

        }

        res[0] = String.valueOf(shamsiYear);
        res[1] = String.valueOf(shamsiMonth);
        res[2] = String.valueOf(shamsiDay);


        return res[0]+"-"+res[1]+"-"+res[2];

    }

    // the function check a miladiyear is leap or not.

    public boolean miladiIsLeap(int miladiYear)
    {
        if(( (miladiYear % 100)!= 0 && (miladiYear % 4) == 0 ) || ((miladiYear % 100)== 0 && (miladiYear % 400) == 0))
            return true;
        else
            return false;

    }








}
