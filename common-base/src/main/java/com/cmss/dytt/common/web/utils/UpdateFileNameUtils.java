/**
 * Created	by	chenshi  at	2017年12月1日 上午10:54:10
 */
package com.cmss.dytt.common.web.utils;

import java.io.File;

/**
 * @description: UpdateFileNameUtils.java
 * @packageName: com.quanhu.activie.base.utils
 * @projectName: common-base
 * @revision: v1.0.0
 * @author: chenshi
 */
public class UpdateFileNameUtils {
    public static void main(String[] args) {
        String fileUrl = "E:\\OneDrive - vip365.ooo.ac.nz\\电影";
        String fileName1 = "[电影天堂www.dy2018.net]";
        String fileName2 = "[飘花www.piaohua.com]";
        String fileName3 = "[电影天堂-www.dy2018.net]";
        String fileName4 = "HD";
        String fileName5 = "[迅雷下载www.2tu.cc]";
        String fileName6 = "[迅雷下载Www.99b.Cc]";
        String fileName7 = "[迅雷下载www.99bo.cc]";
        String fileName8 = "[迅雷下载www.XunBo.Cc]";
        String fileName9 = "[阳光电影www.ygdy8.com]";
        String fileName10 = "【lol电影天堂www.loldytt.com】";
        String fileName11 = "【LOL电影天堂www.loldytt.com】";
        String fileName12 = "【lol电影天堂www.loldytt.com】";
        String fileName13 = "[电影天堂www.dyg2018.net]";
        String fileName14 = "[电影天堂www.dygod.com]";
        String fileName15 = "[迅雷下载Xunbo.Cc]";
        String fileName16 = "[阳光电影www.ygdy8.net]";
        String fileName17 = "[知电影www.zhidy.com]";
        String fileName18 = "高清";
        String fileName19 = "1280";
        String fileName29 = "1024";
        String fileName20 = "BD";
        String fileName21 = "720p";
        String fileName22 = "国语中字";
        String fileName23 = "中英双字幕";
        String fileName24 = "阳光电影wwwygdy8com";
        String fileName25 = "720p.国语中字";
        String fileNmae26 = "双语双字";
        String fileName27 = "国英";
        String fileName28 = "国粤";
        String fileName30 = "中英双字";
        String fileName31 = "阳光电影wwwygdy8net";

        updateFileName(fileUrl, fileName1, fileName2, fileName3, fileName4, fileName5, fileName6, fileName7
                , fileName8, fileName9, fileName10, fileName11, fileName12, fileName13, fileName14, fileName15
                , fileName16, fileName17, fileName18, fileName19, fileName20, fileName21, fileName22
                , fileName25, fileName23, fileName24, fileName25, fileNmae26, fileName27, fileName28, fileName29
                , fileName30, fileName31);

        /*String fileUrl = "E:\\OneDrive - vip365.ooo.ac.nz\\电影";
        String fileName="rmvb";
        String  fileName2="mp4";
        String fileName3="mkv";
        String fileName4="avi";
        updateFileName(fileUrl,fileName,fileName2,fileName3,fileName4);*/
    }


    /**
     * @param fileUrl
     * @param replaceNames
     * @return void
     * @description:updateFileName(批量修改文件名称)
     * @author chenshi
     */
    private static void updateFileName(String fileUrl, String... replaceNames) {
        File f = new File(fileUrl);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    String fileName = files[i].getName();
                    int count = 0;
                    for (String name : replaceNames) {
                        if (fileName.contains(name)) {
                            fileName = fileName.replace(name, "");
                            count++;
                        }
                    }
                    if (count > 0) {
                        String path = files[i].getParent();
                        File newfile = new File(path + "/" + fileName);
                        if (newfile.exists()) {
                            System.out.println(fileName + "已经存在！");
                        } else {
                            files[i].renameTo(newfile);
                        }
                    }
                }
            }
        } else {
            System.out.println("File does not exist!");
        }
    }
}
