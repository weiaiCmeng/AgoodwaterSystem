package com.agoodwater.system.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.agoodwater.system.MainActivity;
import com.agoodwater.system.R;
import com.agoodwater.system.activity.LoginActivity;
import com.agoodwater.system.utils.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;

/**
 * Created by xiaodi on 2016/6/4.
 */
public class JpushReceiver extends BroadcastReceiver {

    private String type;


    public static SoundPool sound;
    public static int soundId;
    private boolean jpush = true ;

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("hahahhahah,woshoudao le ");
//        Log.e("Jpush", "我是推送-------------------");
//        Log.e("Jpush", "我是推送-------------------");
//        Log.e("Jpush", "我是推送-------------------");

//        Intent intent1 = new Intent(context , MainActivity.class);
//
        Bundle bundle = intent.getExtras();
        Log.e("shiqiang", "onReceive - " + intent.getAction());
//接受ID
//        String regID = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//
//        System.out.println("你的id为----------------" + regID);
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String registrationID = JPushInterface.getRegistrationID(context);
//            System.out.println(registrationID + "-----------hehe----------");
            Log.e("woshi ", "我是id啊----------" + registrationID);
//            Log.e("woshi ", "我是id啊----------" + registrationID);


        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//            System.out.println("收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        /**
         * 使用 RegistrationID 推送的关键于，
         * App 开发者需要在开发 App 时，获取到这个 RegistrationID，
         * 保存到 App 业务服务器上去，并且与自己的用户标识对应起来。
         */
            //得到推送的不同Id
//            String registrationID = JPushInterface.getRegistrationID(context);
//            System.out.println(registrationID + "-----------hehe----------");
//            Log.e("woshi ", "我是id啊----------" + registrationID);
//            Log.e("woshi ", "我是id啊----------" + registrationID);

            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
//            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//            System.out.println("收到了自定义消息@@消息内容是:"+ content);
//            System.out.println("收到了自定义消息@@消息extra是:"+ extra);

            //自定义声音

            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);

            System.out.println("收到了自定义消息@@消息内容是:"+ content);
            System.out.println("收到了自定义消息@@消息extra是:"+ extra);

            //**************解析推送过来的json数据并存放到集合中 begin******************
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(extra);
                String type = jsonObject.getString("type");
                map.put("type", type);
            } catch (JSONException e) {

                e.printStackTrace();
            }
            map.put("content", content);
            //获取接收到推送时的系统时间
            Calendar rightNow = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            String date = fmt.format(rightNow.getTime());
            map.put("date", date);
//            MyApp.data.add(map);


            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知,并发送");



            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);

            System.out.println("收到了自定义消息@@消息内容是:"+ content);
            System.out.println("收到了自定义消息@@消息extra是:"+ extra);

//            BasicPushNotificationBuilder builderNoti = new BasicPushNotificationBuilder(
//                    context);
//            builderNoti.notificationDefaults = Notification.DEFAULT_LIGHTS; //只设置了默认闪光灯
//            builderNoti.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为自动消失
//            JPushInterface.setPushNotificationBuilder(1, builderNoti); //设置Jpush的通知栏样式为build，并设置样式编号为1，发送推送时需使用此编号，此方法需在Jpush初始化后使用
//
//            //初始化对象
//             sound = new SoundPool(3,  AudioManager.STREAM_MUSIC, 0);
//            soundId = sound.load(context, R.raw.agoodbucket, 1);
//            sound.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);//利用初始化后的SoundPool对象播放






            //**************解析推送过来的json数据并存放到集合中 begin******************
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(extra);
                type = jsonObject.getString("type");
                System.out.println(type);
            } catch (JSONException e) {

                e.printStackTrace();
            }


            jpush = SpUtils.getBoolean(context, "jpush", true);
            if (jpush) {
                //通知的声音
                final NotificationManager manger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                //为了版本兼容  选择V7包下的NotificationCompat进行构造
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                final Notification notification = builder.build();

                //设置通知的小图标
                builder.setSmallIcon(R.drawable.ic_launch);

                if (!TextUtils.isEmpty(type)) {
                    if (type.equals("bucket")) {

                        notification.sound = Uri.parse("android.resource://"
                                + context.getPackageName() + "/" + R.raw.agoodbucket);


                    } else {
                        notification.sound = Uri.parse("android.resource://"
                                + context.getPackageName() + "/" + R.raw.agood_water);


                    }

                } else {

                    notification.sound = Uri.parse("android.resource://"
                            + context.getPackageName() + "/" + R.raw.agood_water);

                }


                builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE | NotificationCompat.DEFAULT_LIGHTS);
                //一直响,直到用户相应
                notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;


                Intent clickIntent = new Intent(); //点击通知之后要发送的广播
                final int id = (int) (System.currentTimeMillis() / 1000);
                clickIntent.addCategory("com.agoodwater.system");
                clickIntent.setAction(JPushInterface.ACTION_NOTIFICATION_OPENED);
                clickIntent.putExtra(JPushInterface.EXTRA_EXTRA, bundle.getString(JPushInterface.EXTRA_EXTRA));
                PendingIntent contentIntent = PendingIntent.getBroadcast(context, id, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.contentIntent = contentIntent;
                manger.notify(id, notification);

            }


            EventBus.getDefault().post(new OrderEvent());



            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");

            // 在这里可以自己写代码去定义用户点击后的行为
           String userId = SpUtils.getString(context, "userName", "-1");
            if (!"-1".equals(userId)){

                Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }else{

                Intent i = new Intent(context, LoginActivity.class);  //自定义打开的界面
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }




        } else {
            Log.e("shiqiang", "Unhandled intent - " + intent.getAction());
        }


    }

}

