package com.dytt.module.user.mvc;

import com.dytt.common.threadPool.ThreadPoolManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author zhanghao
 * @date 2019/4/18 11:14
 **/
@RestController
@RequestMapping( "/readExecl")
@Slf4j
public class ReadExcelToEventController {


    private final static String UPLOAD_FAIL_FILE_PATH = "C:\\Users\\EDZ\\Documents\\asasas\\failExcelData.txt";
    /**
     * excel文件多条评论分隔符
     */
    public static final String COMMONS_SPLIT = "\\|\\|";
    public static final String imgSplit = ",";

    @Autowired
    RestTemplate restTemplate;

   

   

    @GetMapping(value = {"/createImgExcel"})
    public void createImgExcel( @RequestParam(value = "excelPath", required = true) String excelPath,
                                  @RequestParam(value = "imageDirectoryPath", required = true) String imageDirectoryPath) throws IOException, InterruptedException {
        //原有excel文件
        File excelFile = new File(excelPath);
        //原有图片文件夹文件
        File imgFiles = new File(imageDirectoryPath);
        String excelLocation = excelFile.getParent();
        //写入excel文件
        File file = new File(excelLocation.concat("/imgResources.xls"));
        //写入map参数
        HashMap<String, List<String>> map = Maps.newHashMap();
        //1，读取excel内容
        final Path path = Paths.get(excelPath);
        String name = path.getFileName().toString();
        String originalFileName = path.getFileName().toString();
        String contentType = "text/plain";
        byte[] content = Files.readAllBytes(path);
        MultipartFile multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        final List<String[]> execlResult = ReadExecl.readExcel(multipartFile);
        List<String[]> collect = execlResult.stream().filter(l -> l.length == 3).collect(Collectors.toList());
        log.info("--------读取到excel数据大小:{}", execlResult.size());
        for (String[] strings : execlResult) {
            if (strings.length==3) {
                continue;
            }
            //获取图片文件夹编号
            String imgDir = null;
            try {
                imgDir = strings[3];
                if (StringUtils.isEmpty(imgDir)||StringUtils.isEmpty(imgDir.trim())||strings.length==4) {
                    continue;
                }
            } catch (Exception e) {
                log.info("数组下标越界--------------------");
                e.printStackTrace();
                continue;
            }
            String startWithString = null;
            if (imgDir.length() == 3) {
                startWithString = imgDir.substring(0, 1).concat("-");
            } else if (imgDir.length() == 4) {
                startWithString = imgDir.substring(0, 2).concat("-");
            } else if (imgDir.length() == 5) {
                startWithString = imgDir.substring(0, 3).concat("-");
            }
            log.info("-----图片文件件编号:{},开头字段判断:{}", imgDir, startWithString);
            ArrayList<String> imgUrls = Lists.newArrayList();
            //2,上传编号文件夹内全部图片到千牛云
            for (File file1 : imgFiles.listFiles()) {
                //若文件名是以编号的前1,2位开头则进入
                if (file1.getName().startsWith(startWithString)) {
                    log.info("----文件夹是以{}开头的", startWithString);
                    for (File file2 : file1.listFiles()) {
                        //若文件名和编号相等则符合目标图片文件夹
                        if (file2.getName().equals(imgDir)) {
                            if (file2.listFiles().length == 0) {
                                log.info("--------{}文件夹里面没有图片,跳过", imgDir);
                                continue;
                            }
                            for (File file3 : file2.listFiles()) {
                                //图片文件导入千牛云
                                final String[] fileNameArr = file3.getName().split("\\.");
                                final String ext = fileNameArr[fileNameArr.length - 1];
                                final String fileName = UUID.randomUUID().toString().concat(".").concat(ext);

                                //开启多线程来处理耗时上传操作
                                ThreadPoolManager.getInstance().execute(() -> {
                                    BufferedImage picBi = null;
                                    try {
                                        picBi = ImageIO.read(file3);
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        ImageIO.write(picBi, "png", byteArrayOutputStream);

                                        byte[] bytes = byteArrayOutputStream.toByteArray();
                                        QiniuOperateUtil.uploadByteToOpenSpace(bytes, fileName);
                                        log.info("--------图片成功上传到千牛云!url={}", QiNiuConstant.QI_NIU_OPEN_ADDRESS + fileName);
                                    } catch (IOException e) {
                                        log.info("---------图片上传到千牛云--失败!url={}", QiNiuConstant.QI_NIU_OPEN_ADDRESS + fileName);
                                        e.printStackTrace();
                                    }
                                });
                                imgUrls.add(QiNiuConstant.QI_NIU_OPEN_ADDRESS + fileName);
                                Thread.sleep(200);
                            }
                        }
                    }
                }
            }
            map.put(imgDir, imgUrls);
            log.info("------------------添加进入map:key={},value={}", imgDir, imgUrls.toString());
        }
        //3,将千牛云图片路径写入imgResources.xls
        writeExcel(map, file);
        log.info("------------写入excel结束!--");
//        return ResultUtil.success();
    }

//    @GetMapping(value = {"/createImgExcel2"})
//    public Result createImgExcel2(@ApiParam(name = "excelPath", value = "excel文件路径", required = true) @RequestParam(value = "excelPath", required = true) String excelPath,
//                                 @ApiParam(name = "imageDirectoryPath", value = "图片目录路径", required = true) @RequestParam(value = "imageDirectoryPath", required = true) String imageDirectoryPath) throws IOException, InterruptedException {
//        //原有excel文件
//        File excelFile = new File(excelPath);
//        //原有图片文件夹文件
//        File imgFiles = new File(imageDirectoryPath);
//        String excelLocation = excelFile.getParent();
//        //写入excel文件
//        File file = new File(excelLocation.concat("/imgResources.xls"));
//        //写入map参数
//        HashMap<String, List<String>> map = Maps.newHashMap();
//        //1，读取excel内容
//        final Path path = Paths.get(excelPath);
//        String name = path.getFileName().toString();
//        String originalFileName = path.getFileName().toString();
//
//        final List<String[]> execlResult = ExcelUtil.readExcel(excelFile);
//        log.info("--------读取到excel数据大小:{}", execlResult.size());
//        for (String[] strings : execlResult) {
//            //获取图片文件夹昵称,编号
//            String dirName=null;
//            String imgDir = null;
//            try {
//                dirName=strings[strings.length-1].trim();
//                imgDir = strings[6].trim();
//                if (StringUtils.isEmpty(dirName)||StringUtils.isEmpty(imgDir)) {
//                    continue;
//                }
//                imgDir=imgDir.split("[.]")[0];
//                log.info("-----读取到文件夹名称:{},编号:{}",dirName,imgDir);
//            } catch (Exception e) {
//                log.info("数组下标越界--------------------");
//                e.printStackTrace();
//                continue;
//            }
//            ArrayList<String> imgUrls = Lists.newArrayList();
//            //2,上传编号文件夹内全部图片到千牛云
//            for (File file1 : imgFiles.listFiles()) {
//                if (file1.getName().trim().equals(dirName)) {
//                    log.info("--进入{}文件夹", dirName);
//                    for (File file2 : file1.listFiles()) {
//                        //若文件名和编号相等则符合目标图片文件夹
//                        if (file2.getName().equals(imgDir)) {
//                            log.info("------进入{}文件夹",imgDir);
//                            if (file2.listFiles().length == 0) {
//                                log.info("--------{}文件夹里面没有图片,跳过", imgDir);
//                                continue;
//                            }
//                            for (File file3 : file2.listFiles()) {
//                                //图片文件导入千牛云
//                                final String[] fileNameArr = file3.getName().split("\\.");
//                                final String ext = fileNameArr[fileNameArr.length - 1];
//                                final String fileName = UUID.randomUUID().toString().concat(".").concat(ext);
//
//                                BufferedImage picBi = ImageIO.read(file3);
//                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                                ImageIO.write(picBi, "png", byteArrayOutputStream);
//                                byte[] bytes = byteArrayOutputStream.toByteArray();
//                                //开启多线程来处理耗时上传操作
//                                ThreadPoolManager.getInstance().execute(() -> {
//                                    QiniuOperateUtil.uploadByteToOpenSpace(bytes, fileName);
//                                    log.info("--------图片成功上传到千牛云!url={}", QiNiuConstant.QI_NIU_OPEN_ADDRESS + fileName);
//                                });
//                                imgUrls.add(QiNiuConstant.QI_NIU_OPEN_ADDRESS + fileName);
//                                Thread.sleep(200);
//                            }
//                        }
//                    }
//                }
//            }
//            String key = dirName.concat("_").concat(imgDir);
//            map.put(key, imgUrls);
//            log.info("------------------添加进入map:key={},value={}", key, imgUrls.toString());
//        }
//        //3,将千牛云图片路径写入imgResources.xls
//        writeExcel(map, file);
//        return ResultUtil.success();
//    }

    private void writeExcel(Map<String, List<String>> map, File file) {
        //1,创建excel对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2,创建一个sheet
        HSSFSheet sheet1 = workbook.createSheet("imgUrls");
        //3,添加表头信息
        HSSFRow row1 = sheet1.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        HSSFCell cell2 = row1.createCell(1);
        cell1.setCellValue("图片编号");
        cell2.setCellValue("图片urls");
        //4,写入数据
        int i = 1;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String imgDirNumber = entry.getKey();
            List<String> imgUrls = entry.getValue();
            HSSFRow row = sheet1.createRow(i);
            //设置单元格的宽度:255,高度:5
            /*sheet1.setColumnWidth(i,255*256);
            row.setHeightInPoints(200);*/
            //创建2个单元格并设置值
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(imgDirNumber);
            cell = row.createCell(1);
            cell.setCellValue(imgUrls.toString());
            i++;
        }

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            workbook.write(stream);
            stream.close();
            log.info("-----------写入成功!");
        } catch (FileNotFoundException e) {
            log.info("-----------FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            log.info("-----------IOException!");
            e.printStackTrace();
        }
    }

    @Slf4j
    private static class ReadExecl {

        private final static String xls = "xls";
        private final static String xlsx = "xlsx";

        /**
         * 读入excel文件，解析后返回
         *
         * @param file
         * @throws IOException
         */
        public static List<String[]> readExcel(MultipartFile file) throws IOException {
            //检查文件
            checkFile(file);
            //获得Workbook工作薄对象
            Workbook workbook = getWorkBook(file);
            //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
            List<String[]> list = new ArrayList<String[]>();
            if (workbook != null) {
                for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                    //获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(Integer.valueOf(sheetNum));
                    //获得当前sheet的开始行
                    int firstRowNum = sheet.getFirstRowNum();
                    //获得当前sheet的结束行
                    int lastRowNum = sheet.getLastRowNum();
                    //循环除了第一行的所有行
                    for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {

                        //获得当前行
                        Row row = sheet.getRow(rowNum);
                        if (row == null) {
                            continue;
                        }
                        //获得当前行的开始列
                        int firstCellNum = row.getFirstCellNum();
                        //获得当前行的列数
                        int lastCellNum = firstCellNum == 3 ? row.getPhysicalNumberOfCells() + firstCellNum : row.getPhysicalNumberOfCells();
                        String[] cells = new String[lastCellNum + 2];
                        //循环当前行
                        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            cells[cellNum] = getCellValue(cell);
                        }
                        if (firstCellNum == 3||(lastCellNum>3&&StringUtils.isEmpty(cells[0])&&StringUtils.isEmpty(cells[1])&&StringUtils.isEmpty(cells[2]))) {
                            final String[] valueArr = list.get(list.size() - 1);
                            cells[0] = valueArr[0];
                            cells[1] = valueArr[1];
                            cells[2] = valueArr[2];
                        }
                        //将sheetName添加到数组的末尾
                        cells[cells.length - 1] = sheet.getSheetName();
                        list.add(cells);
                    }
//            }
                    workbook.close();
                }
            }
            return list;

        }

        public static void checkFile(MultipartFile file) throws IOException {
            //判断文件是否存在
            if (null == file) {
                log.error("文件不存在！");
                throw new FileNotFoundException("文件不存在！");
            }
            //获得文件名
            String fileName = file.getOriginalFilename();
            //判断文件是否是excel文件
            if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
                log.error(fileName + "不是excel文件");
                throw new IOException(fileName + "不是excel文件");
            }
        }

        public static Workbook getWorkBook(MultipartFile file) {
            //获得文件名
            String fileName = file.getOriginalFilename();
            //创建Workbook工作薄对象，表示整个excel
            Workbook workbook = null;
            try {
                //获取excel文件的io流
                InputStream is = file.getInputStream();
                //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
                if (fileName.endsWith(xls)) {
                    //2003
                    workbook = new HSSFWorkbook(is);
                } else if (fileName.endsWith(xlsx)) {
                    workbook = new XSSFWorkbook(is);
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            return workbook;
        }

        public static String getCellValue(Cell cell) {
            String cellValue = "";
            if (cell == null) {
                return cellValue;
            }
            //如果是日期，就直接返回
//            String dataFormatString = cell.getCellStyle().getDataFormatString();
//            short dataFormat = cell.getCellStyle().getDataFormat();
            //把数字当成String来读，避免出现1读成1.0的情况
//            if (cell.getCellType() == NUMERIC) {
//                cell.setCellType(CellType.STRING);
//            }
            //判断数据的类型
            switch (cell.getCellType()) {
                //数字
                case NUMERIC:
                    cellValue = getCellString(cell);
                    break;
                //字符串
                case STRING:
                    cellValue = String.valueOf(cell.getStringCellValue());
                    break;
                //Boolean
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                //公式
                case FORMULA:
                    cellValue = getCellString(cell);
                    break;
                //空值
                case BLANK:
                    cellValue = "";
                    break;
                //故障
                case ERROR:
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
            return cellValue;
        }

    }

    private static String getCellString(Cell cell) {
        String cellValue;
        if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
            Date theDate = cell.getDateCellValue();
            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cellValue = dff.format(theDate);
        } else {
            DecimalFormat df = new DecimalFormat("0");
            cellValue = df.format(cell.getNumericCellValue());
        }
        return cellValue;
    }

   
   

   


}
