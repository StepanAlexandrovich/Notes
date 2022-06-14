package java.android.notes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
    public static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMMM.yyyy");

    public static void setDay   (int day)   { calendar.set(Calendar.DAY_OF_MONTH,day); }
    public static void setMonth (int month) { calendar.set(Calendar.MONTH,month);      }
    public static void setYear  (int year)  { calendar.set(Calendar.YEAR,year);        }

    public static int getDay(){ return calendar.get(Calendar.DAY_OF_MONTH); }
    public static int getMonth(){ return calendar.get(Calendar.MONTH); }
    public static int getYear(){ return calendar.get(Calendar.YEAR); }

    public static String getDateYMD(){
        return dateFormat.format(calendar.getTime());
    }

}
