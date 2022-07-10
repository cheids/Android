package android.bignerdranch.lost.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class utils {
    public static int ID=0;
    public static String passname="";
    public static String passpwd="";
    public static List<Integer> list=new ArrayList<>();
    public static String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss");

        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }
}