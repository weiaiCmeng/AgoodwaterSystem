package com.agoodwater.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiqiang on 2016/9/13.
 */
public class FileUtils {


    /**
     * 保存图片到指定文件夹中
     */
    public static boolean puImgToFile(String f, String fileName) {

        FileInputStream sIn = null;
        FileOutputStream sOut = null;
        /*f= f.replace("/","//");*/
        File oldFile = new File(f);
        try {
        /*String musicName = f.substring(f.lastIndexOf("/") + 1);*/
            String newFile = Constants.PHONE_SHOW_IMAGE + fileName;
            File file = new File(newFile);

            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true);
                file.setReadable(true);
            }
            if (oldFile.exists()) {
                sIn = new FileInputStream(f);

                sOut = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int le;
                int by = 0;
                int b = 0;
                while ((by = sIn.read(buffer)) != -1) {
                    b += by;
                    sOut.write(buffer, 0, by);
                }
                sIn.close();
                sOut.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 取出保存的图片的路径String型
     */
    public static List<String> getImgFileList(String file) {
        List<String> mlist = new ArrayList<>();
        File file1 = new File(file);
        if (file1.exists() && file1.isDirectory()) {
            if (file1.list().length != 0) {
                String[] lists = file1.list();
                for (int i = 0; i < lists.length; i++) {
                    mlist.add(lists[i]);
                }
                return mlist;
            }
            return null;
        } else {
            return null;
        }
    }


    /**
     * 获取文件夹下所有文件夹额名称
     */
    public static List<String> getPhoneShowName(String file) {
        List<String> mlist = new ArrayList<>();
        File file1 = new File(file);
        if (file1.exists() && file1.isDirectory()) {
            if (file1.list().length != 0) {
                File[] files = file1.listFiles();
                for (int i = 0; i < files.length; i++) {

                    if (files[i].isDirectory()) {
                        String fileStr = files[i].toString();
                        int i1 = fileStr.lastIndexOf("/");
                        mlist.add(fileStr.substring(i1 + 1));
                    }
                }
                return mlist;
            }
            return null;
        } else {
            return null;
        }
    }

}
