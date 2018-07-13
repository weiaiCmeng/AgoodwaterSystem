package com.agoodwater.system.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 10398 on 2016/5/17.
 */
public class TimeUtils {

    private static long millionSeconds;






    public static String plusDay(int num,String newDate){

        if (TimeUtils.getLongTime(newDate) > System.currentTimeMillis()){

            plusDay(6, newDate);

        }


              SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date  currdate = null;
        try {
            currdate = format.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("现在的日期是：" + currdate);
             Calendar ca = Calendar.getInstance();
               ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
                 currdate = ca.getTime();
                 String enddate = format.format(currdate);
              System.out.println("增加天数以后的日期：" + enddate);
               return enddate;
           }



    /**
     * 将字符串数据转化为毫秒数
     */
    public static Long time2MS(String time) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(time));
            long timeInMillis = c.getTimeInMillis();
            return timeInMillis;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将YYYY-MM-dd HH:mm:ss转成YYYY-MM-dd型和HH:mm:ss型
     * String i = "2015-05-16 10:48:02";转成2015-05-16
     */
    public static String date2Year(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = (Date) sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(d);
               /* System.out.println(sdf.format(d));//获取
                System.out.println(d.getTime());//获取毫秒*/
        return format;
    }

    /**
     * 毫秒转时间
     */
    public static String ms2DetailTime2(Long ms) {
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String format = sdf.format(date);
        return format;
    }

    /**
     * 将YYYY-MM-dd HH:mm:ss转成YYYY-MM-dd型和HH:mm:ss型
     * String i = "2015-05-16 10:48:02";转成10:48
     */
    public static String date2Hour(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = (Date) sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("HH:mm");
        String format = sdf.format(d);
	           /* System.out.println(sdf.format(d));//获取
	            System.out.println(d.getTime());//获取毫秒*/
        return format;
    }


    /**
     * 将毫秒数转化为日期
     */
    public static String ms2Time(Long ms) {
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    /**
     * 毫秒转时间
     */
    public static String ms2DetailTime(Long ms) {
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String format = sdf.format(date);
        return format;
    }
/**
     * 毫秒转时间 HH:mm:ss
     */
    public static String hms2DetailTime(Long ms) {
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }

    /**
     * 获取HH:mm;
     */
    public static String getNowHM(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前日期时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 获取到小时
     */
    public static String getNowHH(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date curDate = new Date(System.currentTimeMillis());//获取当前日期时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 获取分钟
     */
    public static String getNowmm(){
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前日期时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 将时间MM月dd日 改为yyyy-mm-dd 格式
     */

    public static String strToTime(String str) {
        String[] split = str.split(" ");
        String strDate = split[0];

        if (TextUtils.isEmpty(strDate)){

            return "暂无配送时间" ;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        Date d = null;
        try {
            d = (Date) sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("MM-dd");
        String strFormat = sdf.format(d);

        //得到当前的年份
        int year = Calendar.getInstance().get(Calendar.YEAR);

        String format = year + "-" + strFormat;
	           /* System.out.println(sdf.format(d));//获取
	            System.out.println(d.getTime());//获取毫秒*/
        return format;
    }


   /**
     * 将String Date  改为yyyy-mm-dd 格式
     */

    public static String strToDate(String str) {

        try {
            Date date = new Date(str);

            SimpleDateFormat formatter   =
                    new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(date);

            return strDate;
        }catch (IllegalArgumentException e){

            return "无时间";

        }


    }


    /**
     * 将String Date  改为yyyy-mm-dd 格式
     */

    public static String strToDateHH(String str) {

        try {

            Date date = new Date(str);

            SimpleDateFormat formatter   =
                    new SimpleDateFormat( "HH:mm");
            String strDate = formatter.format(date);

            return strDate;

        }catch (Exception e){

            return "无时间";


        }

    }


    /**
     * 将String Date  改为yyyy-mm-dd 格式
     */

    public static String strToDateHHmmss(String str) {


        try {
            Date date = new Date(str);

            SimpleDateFormat formatter   =
                    new SimpleDateFormat( "HH:mm:ss");
            String strDate = formatter.format(date);

            return strDate;
        }catch (Exception e){
            return "暂无时间" ;
        }

    }


    /**
     * 传入一个"2016年12月30日"的字符串,转化成 "2016-12-30"
     *
     * @param str
     * @return
     */
    public static String getSimpleTimeStyle(String str) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date d = (Date) sdf.parse(str);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
           /* System.out.println(sdf.format(d));//获取
            System.out.println(d.getTime());//获取毫秒*/
            return sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 獲取當前時間
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }  /**
     * 獲取當前時間
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前日期时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当前天
     */
    public static String getCurrentDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当前月
     */
    public static String getCurrentMouth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 獲取當前時間
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }


    public static String getDayOfWeekByDate() {
        String dayOfWeekByDate = getDayOfWeekByDate(getCurrentDateTime());
        return dayOfWeekByDate;
    }

    /**
     * 获取最近几天的日期
     * @param i  -1为昨天 0 默认为今天 1为明天 2为后天
     * @return
     */

    public static String getLatelyDate(int i){
        SimpleDateFormat df=new SimpleDateFormat( "yyyy-MM-dd ");
        Calendar calendar= Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR,i);
        String format = df.format(calendar.getTime());
        return format;
    }


    /**
     * 时间转毫秒值
     */

    public static long getLongTime(String str){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            //毫秒
            millionSeconds = sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return millionSeconds ;

    }

    /**
     * date
     * @param date
     * @return
     */

    public static String getDataToString(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(date) ;

    }




    /**
     * 获取当前倒计时的秒数
     */

//    public static long getLastTime(int i){
//        SimpleDateFormat   df=new  SimpleDateFormat( "yyyy-MM-dd");
//        Calendar   calendar=Calendar.getInstance();
//        calendar.roll(Calendar.DAY_OF_YEAR,i);
//        String format = df.format(calendar.getTime());
//        return format;
//    }




}
