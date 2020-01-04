package com.guineap_pig_329.guinea_pig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {


    public static String long2DataStr(long time){
        String template = "yyyy-mm-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    public static long string2Long(String dateStr){
        String template = "yyyy-mm-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
