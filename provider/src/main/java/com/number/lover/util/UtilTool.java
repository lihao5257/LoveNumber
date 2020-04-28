package com.number.lover.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/*
 * Util class
 */
public class UtilTool {

    /*
     * Get files from specified path
     */
    public static List<String> getFiles(String path) {
        if (path == null || path.length() == 0) {
            return null;
        }
        if (!path.endsWith("/")) {
            path = path + "/";
        }

        File file = new File(path);
        List<String> fileResultList = new LinkedList<String>();
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                fileResultList.add(path + fileName);
            }

        }
        return fileResultList;
    }

    /*
     * Get content from file
     */
    public static String readFile(String file) {
        String jsonStr;
        if (file == null || file.length() == 0) {
            return null;
        }

        // java 8 autoClose file reader,inputStream .etc
        try (FileReader fileReader = new FileReader(file);
                Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8")) {

            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Delete file 
     */
    public static void deleteFile(String deleteFile) {
        File file = new File(deleteFile);// 读取
        if (file.isFile()) { // 判断是否是文件夹
            file.delete();// 删除
        }
    }
}
