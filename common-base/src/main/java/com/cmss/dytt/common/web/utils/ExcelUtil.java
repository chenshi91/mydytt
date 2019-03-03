/* created by chenshi at 2018-05-05 */
package com.cmss.dytt.common.web.utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelUtil {

    /**
     * @param fileName（文件名称）
     * @param url（路径）
     * @param map(key:execl中sheet页的名称，value:exlel每页的数据)
     * @throws IOException
     * @throws WriteException
     */
    public static void outputExcel(String fileName, String url, Map<String, List<String[]>> map) throws IOException, WriteException {
        StringBuffer fileUrl = new StringBuffer(url);
        fileUrl.append("/").append(fileName).append(".xls");
        FileOutputStream file = new FileOutputStream(fileUrl.toString());
        WritableWorkbook workbook = Workbook.createWorkbook(file);


        int i = 0;
        Set<Map.Entry<String, List<String[]>>> entries = map.entrySet();
        for (Map.Entry<String, List<String[]>> entry : entries) {
            //1，创建一页sheet
            WritableSheet sheet1 = workbook.createSheet(entry.getKey(), i);

            //2,填充sheet内容
            List<String[]> writeList = entry.getValue();
            int size = writeList.size();
            //y轴坐标从0到list集合的长度
            for (int j = 0; j < size; j++) {
                String[] writeLine = writeList.get(j);
                //x轴坐标从0到end
                for (int i1 = 0; i1 < writeLine.length; i1++) {
                    sheet1.addCell(new Label(i1, j, writeLine[i1]));
                }
            }
            i++;
        }


        workbook.write();
        workbook.close();

    }
}
