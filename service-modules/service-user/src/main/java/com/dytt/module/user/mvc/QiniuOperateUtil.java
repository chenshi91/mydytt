package com.dytt.module.user.mvc;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 七牛云相关操作工具类
 *
 * @author yu_chen
 * @date 2018年4月2日17:02:07
 */
@Slf4j
public class QiniuOperateUtil {
    /**
     * txt换行符
     */
    private static final String QN_NEWLINE = "\n";
    /**
     * 索引文件名称
     */
    private static final String TXT_NAME   = "index.txt";
    private static final String BUCKET     = "yinian-app";
    private static       Auth   auth       = Auth.create(QiNiuConstant.ACCESS_KEY,
            QiNiuConstant.SECRET_KEY);

    private static Configuration cfg = new Configuration(Zone.zone0());


    /**
     * 打包压缩图片
     *
     * @param pictures 图片地址 以,分隔
     * @return 打包信息
     */
    public static Map<String, String> makeZip(String pictures) {
        Map<String, String> map = new HashMap<String, String>();
        String[] picArray = pictures.split(",");
        String downUrl = "";
        String zipId = "";
        String zipName = UUID.randomUUID().toString().replaceAll("-", "") + ".zip";
        BucketManager bucketManager = new BucketManager(auth,cfg);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            //调用listFiles方法列举指定空间的指定文件
            //参数一：bucket    空间名
            //参数二：prefix    文件名前缀
            //参数三：marker    上一次获取文件列表时返回的 marker
            //参数四：limit     每次迭代的长度限制，最大1000，推荐值 100
            //参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
            //FileListing fileListing = bucketManager.listFiles(BUCKET, prefix, null, 100, null);
            //FileInfo[] items = fileListing.items;
            String content = "";
            for (int i = 0; i < picArray.length; i++) {
                //String safeUrl = "/url/" + UrlSafeBase64.encodeToString(getDownloadToken("http://7xpend.com1.z0.glb.clouddn.com/" + picArray[i]));
                String safeUrl = "/url/" + UrlSafeBase64.encodeToString(QiNiuConstant.QI_NIU_OPEN_ADDRESS + picArray[i]);
                content += ((StringUtils.isBlank(content) ? "" : QN_NEWLINE) + safeUrl);
            }
            //System.out.println(content);

            //索引文件路径
            String txtKey = "yinian/" + TXT_NAME;
            //生成索引文件token（覆盖上传）
            String uptoken = auth.uploadToken(BUCKET, txtKey, 3600, new StringMap().put("insertOnly", 0));
            //上传索引文件
            Response res = uploadManager.put(content.getBytes(), txtKey, uptoken);
            //默认utf-8，但是中文显示乱码，修改为gbk
            String fops = "mkzip/4/encoding/" + UrlSafeBase64.encodeToString("utf-8") + "|saveas/" + UrlSafeBase64.encodeToString(BUCKET + ":" + zipName);

            OperationManager operater = new OperationManager(auth,cfg);

            StringMap params = new StringMap();
            //压缩完成后，七牛回调URL
            //params.put("notifyURL", NOTIFY_URL);

            String id = operater.pfop(BUCKET, txtKey, fops, params);
            if (null != id && !"".equals(id)) {
                // downUrl = getDownloadToken("http://7xpend.com1.z0.glb.clouddn.com/" + zipName);
                downUrl = QiNiuConstant.QI_NIU_OPEN_ADDRESS + zipName;
                zipId = id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("downUrl", downUrl);
        map.put("zipId", zipId);
        return map;
    }

    /**
     * 向七牛云发送请求获取图片信息
     *
     * @param url
     * @return
     */
    public static String sentHttpRequestToGetImageInfo(String url) {

        String result = "";
        BufferedReader in = null;
        try {
            while (true) {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("accept", "*/*");
                con.setDoOutput(true);
                con.setDoInput(true);
                int code = con.getResponseCode();
                if (!(code == 500)) {
                    // 系统没有发生错误，则获取返回信息，并返回
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        result += line;
                    }
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取下载token
     *
     * @param url
     * @return
     */
    public static String getDownloadToken(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 默认有效时长：3600秒
        return auth.privateDownloadUrl(url, 3600 * 240);
    }

    /**
     * 下载七牛云私有云图片
     * 并将图片 转化成byte数组   格式为jpg格式
     *
     * @param address 图片私有云地址
     * @author 杨岳
     */
    public static byte[] bufferedImageToByteArray(String address) {
        QiniuOperateUtil qiniu = new QiniuOperateUtil();
        String firstPicUrl = getDownloadToken(address);
        byte[] bytes = null;
        //转换成 byte
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage picBi = ImageIO.read(new URL(firstPicUrl));
            ImageIO.write(picBi, "jpg", byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        } catch (IOException e) {
            log.error("从私有云下载图片失败", e);
        }
        return null;
    }

    /**
     * 通过上传字节的方式
     * 上传文件到公开空间
     *
     * @param data     上传文件到公开空间
     * @param fileName 文件名
     */
    public static void uploadByteToOpenSpace(byte[] data, String fileName) {
        String token = getUploadToken();
        UploadManager upload = new UploadManager(cfg);
        try {
            upload.put(data, fileName, token);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件到公开空间
     *
     * @param filePath 文件本地路径
     * @param fileName 文件名
     */
    public static void uploadFileToOpenSpace(String filePath, String fileName) {
        String token = getUploadToken();
        UploadManager upload = new UploadManager(cfg);
        try {
            final Response put = upload.put(filePath, fileName, token);
        } catch (QiniuException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 上传文件到公开空间
     *
     * @param data     上传文件到公开空间
     * @param fileName 文件名
     */
    public static void uploadFileToOpenSpace(byte[] data, String fileName) {
        String token = getUploadToken();
        UploadManager upload = new UploadManager(cfg);
        try {
            upload.put(data, fileName, token);
        } catch (QiniuException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 上传文件到私有云地址
     *
     * @param filePath
     * @param fileName
     */
    public static void uploadFileToPrivateSpace(String filePath, String fileName) {
        String token = getPrivateUploadToken();
        UploadManager upload = new UploadManager(cfg);
        try {
            upload.put(filePath, fileName, token);
        } catch (QiniuException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 通过上传字节的方式
     * 上传文件到私有云
     *
     * @param data     上传文件到公开空间
     * @param fileName 文件名
     */
    public static void uploadByteToPrivateSpace(byte[] data, String fileName) {
        String token = getPrivateUploadToken();
        UploadManager upload = new UploadManager(cfg);
        try {
            upload.put(data, fileName, token);
        } catch (QiniuException e) {
           throw new RuntimeException(e);
        }
    }

    /**
     * 获取公共空间上传token
     *
     * @return
     */
    public static String getUploadToken() {
        return auth
                .uploadToken(
                        QiNiuConstant.OPEN_BUCKET,
                        null,
                        3600 * 24,
                        new StringMap()
                                .putNotEmpty(
                                        "returnBody",
                                        "{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}"));
    }

    /**
     * 获取私有云上传token
     *
     * @return
     */
    public static String getPrivateUploadToken() {
        return auth
                .uploadToken(
                        QiNiuConstant.PRIVATE_BUCKET,
                        null,
                        3600 * 24,
                        new StringMap()
                                .putNotEmpty(
                                        "returnBody",
                                        "{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}"));
    }

    public static String photoPublicPrefix(String photoUrl) {
        if (StringUtils.isBlank(photoUrl)) {
            return null;
        }
        if (!photoUrl.startsWith("https") && (!photoUrl.startsWith("http"))) {
            return QiNiuConstant.QI_NIU_OPEN_ADDRESS + photoUrl;
        }
        return photoUrl;
    }

    /**
     * BY LK 20171108 获取段视频封面
     *
     * @param url 视频URL
     * @return 视频封面的
     */
    public static String getVideoCover(String url) {
        return url + "?vframe/jpg/offset/1/w/500";
    }

    public static PicMeasureBO getPicMeasureBO(String url) throws IOException {
        BufferedImage bufImg = ImageIO.read(new URL(photoPublicPrefix(url)));
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        return new PicMeasureBO(width, height);
    }


    public static String getWaterPink(String picUrl){
        String url = picUrl+"?watermark/1/image/";

        String picCss = "/gravity/SouthEast/dissolve/30/dx/1/dy/1/ws/0";

        String waterPic = UrlSafeBase64.encodeToString("https://app.public.zhuiyinanian.com/AppIcon.png?imageView2/2/w/200");

        return url.concat(waterPic).concat(picCss);
    }

}