package com.example.demo.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class DateTimeUtil {
    private static final Logger logger=LoggerFactory.getLogger(DateTimeUtil.class);

    public static final String STAND_FORMAT="yyyy-MM-dd HH:mm:ss";

    public static final String STAND_SHORT_FORMAT="yyyy-MM-dd";



        public static Date addDuration(Date date, Duration duration){
            Calendar ca=Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.SECOND,(int)duration.getSeconds());
            return ca.getTime();
        }

        public static String dateFormat(Date date){
            if (null==date){
                return "";
            }
            DateFormat dateFormat=new SimpleDateFormat(STAND_FORMAT);
            return dateFormat.format(date);
        }

        public static String dateShortFormat(Date date){
            if (null==date){
                return "";
            }
            DateFormat dateFormat=new SimpleDateFormat(STAND_SHORT_FORMAT);
            return dateFormat.format(date);
        }

        public static Date parse(String dateStr,String format){
            try{
                return new SimpleDateFormat(format).parse(dateStr);

            }catch(ParseException e){
                logger.error(e.getMessage(),e);
            }
            return null;
        }

        public static Date getMonthStartDay(){
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            Calendar cale=Calendar.getInstance();
            cale.add(Calendar.MONTH,0);
            cale.set(Calendar.DAY_OF_MONTH,1);
            String dateStr=formatter.format(cale.getTime());
            return parse(dateStr,"yyyy-MM-dd HH:mm:ss");
        }

        public static Date getMonthEnDay(){
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            Calendar cale=Calendar.getInstance();
            cale.add(Calendar.MONTH,1);
            cale.set(Calendar.DAY_OF_MONTH,0);
            String dateStr=formatter.format(cale.getTime());
            return parse(dateStr,STAND_FORMAT);
        }

        public static List<String> MonthStartToNowFormat(){
            Date startTime=getMonthStartDay();
            Calendar nowCalendar=Calendar.getInstance();
            nowCalendar.setTime(new Date());
            int monthDayCount=nowCalendar.get(Calendar.DAY_OF_MONTH);
            List<String> monthDays=new ArrayList<>(monthDayCount);
            Calendar startCalendar=new GregorianCalendar();
            startCalendar.setTime(startTime);
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
            monthDays.add(formatter.format(startTime));
            for (int i=0;i<monthDayCount-1;i++){
                startCalendar.add(Calendar.DATE,1);
                Date end_date=startCalendar.getTime();
                monthDays.add(formatter.format(end_date));
            }
            return monthDays;

        }

        public static List<String> MonthDay(){
            Calendar endCalendar=Calendar.getInstance();
            endCalendar.setTime(getMonthEnDay());
            int endMonthDay=endCalendar.get(Calendar.DAY_OF_MONTH);
            List<String> list=new ArrayList<>(endMonthDay);
            for (int i=1;i<=endMonthDay;i++){
                list.add(String.valueOf(i));
            }
            return list;
        }
}
