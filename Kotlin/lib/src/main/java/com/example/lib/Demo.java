package com.example.lib;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.util.LunarUtil;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Lunar lunar = Lunar.fromDate(new Date(time));
//        System.out.println(lunar.getTimeGan());
//        System.out.println(lunar.getTimeZhi());
//        System.out.println(lunar.getTimeTianShenLuck());

        System.out.println("======================");

        for (int i = 0; i < 23 ; i+=2) {
            Lunar lunar1 =  Lunar.fromYmdHms(lunar.getYear(),lunar.getMonth(),lunar.getDay(),i,0,0);
            System.out.println(lunar1.getTimeInGanZhi()+lunar1.getTimeTianShenLuck());
            System.out.println(lunar1.getTimeTianShen());
        }

    }
}
