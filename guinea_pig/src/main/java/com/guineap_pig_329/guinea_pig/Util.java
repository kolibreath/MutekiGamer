package com.guineap_pig_329.guinea_pig;

import com.guineap_pig_329.guinea_pig.dao.Cen;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    public static Cen CEN ;

    private static Logger getInstance(Class className){
        return LoggerFactory.getLogger(className);
    }

    /**
     * debug method
     * @param className
     * @param string 输出的内容
     */
    public static void debug(Class className,String string){
        getInstance(className).debug(string);
    }

    /**
     * info method
     * @param className
     * @param string 输出的内容
     */


    public static void info(Class className,String string){
        getInstance(className).info(string);
    }

    /**
     *
     * warning method
     * @param className
     * @param string 输出的内容
     */
    public static void warning(Class className,String string){
        getInstance(className).warn(string);
    }
    /**
     * error method
     * @param className
     * @param string 输出的内容
     */
    public static void error(Class className,String string){
        getInstance(className).error(string);
    }

    public static Cen getCenInstance(){
        File file = new File("/Users/kolibreath/Desktop/cen.txt");
        if(!file.exists() || !file.isFile() ){
            return null;
        }
        StringBuffer content = new StringBuffer();
        try{
            char[] temp = new char[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream,"GBK");
            while(reader.read(temp) != -1){
                content.append(new String(temp));
                temp = new char[1024];
            }

            fileInputStream.close();
            reader.close();
        }catch(Exception e){
            return null;
        }

        JSONObject jsonObject = JSONObject.fromObject(content.toString());
        return (Cen)JSONObject.toBean(jsonObject,Cen.class);
    }

}
