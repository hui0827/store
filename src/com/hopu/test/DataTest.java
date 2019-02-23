package com.hopu.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataTest {

    public static void main(String[] args) {
        System.out.println(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(new Date());
        try {
            Date parse = sdf.parse(format);
            System.out.println(parse);

            SimpleDateFormat s = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.US);
            Date d = s.parse(new Date().toString());
            System.out.println(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
