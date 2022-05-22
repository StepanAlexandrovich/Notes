package java.android.notes;

import java.util.Calendar;
import java.util.Date;

public class Time {
    //private static Date date = new Date();
    //public static Date getDate() { return date; }

    private static Calendar calendar = Calendar.getInstance();
    private static int[] dateYMD = {Calendar.DAY_OF_MONTH,Calendar.MONTH,Calendar.YEAR};

    public static void setDay(int i){ dateYMD[0] = i; }
    public static void setMonth(int i){ dateYMD[1] = i; }
    public static void setYear(int i){ dateYMD[2] = i; }

    public static int getDay(){ return dateYMD[0]; }
    public static int getMonth(){ return dateYMD[1]; }
    public static int getYear(){ return dateYMD[2]; }

    public static String getDateYMD(){ return ""+dateYMD[0]+" "+dateYMD[1]+" "+dateYMD[2]; }

}
