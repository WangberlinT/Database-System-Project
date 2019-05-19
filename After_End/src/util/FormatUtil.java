package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public Date stringToDate(String y_m_d)
    {
//        String[] strings = y_m_d.split("-");
//        int year = Integer.parseInt(strings[0]);
//        int month = Integer.parseInt(strings[1]);
//        int day = Integer.parseInt(strings[2]);
        Date date = null;
        try {
            date = dateFormat.parse(y_m_d);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }

    public String formatDate(Date date)
    {
        String s = null;
        try {
            s = dateFormat.format(date);
        }
        catch (Exception e)
        {
            if(e instanceof NullPointerException)
            {
                s = null;
            }
        }
        return s;
    }

//    public static void main(String[] args) {
//        Date date = stringToDate("2000-09-27");
//        FormatUtil u = new FormatUtil();
//        System.out.println(u.dateFormat.format(date));
//    }
}
