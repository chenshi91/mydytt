/* created by chenshi at 2018-12-03 */
package model;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.test.BaseTest;
import com.dytt.module.movie.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * movie
 */
@Slf4j
public class MovieApiTest extends BaseTest {

    @Test
    public void 电影列表() {
        super.httpRequestOfGET("/list", null);
    }

    @Test
    public void hi() {
        super.httpRequestOfGET("/hi", null);
    }

    @Test
    public void 电影列表分页() {
        super.httpRequestOfGET("/page?pageNo=1&pageSize=10");
    }

    @Test
    public void asss() throws IOException {
        log.info("开始执行添加数据,url:https://www.dy2018.com");
        Document document = Jsoup.connect("https://www.dy2018.com" + "/i/96585.html").get();
        if (document.childNodeSize() > 0) {
            List<Node> childNodes = document.childNodes();
            //初始化数据
            this.bodyNodes = new LinkedList<>();
            this.imgUrl = new StringBuffer();
            this.content = new StringBuffer();
            getNodes(childNodes);
        }
        Movie requestParams = getOneMovieRequest();
        //发送http请求
        super.httpEntity = new HttpEntity<>(requestParams, httpHeaders);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange("/insert", HttpMethod.POST, httpEntity, JSONObject.class, "");
        JSONObject responseResult = responseEntity.getBody();
        if ("000000".equals(responseResult.getString("code"))) {
            log.info("-------添加数据ok:" + requestParams.getTitle());
        } else {
            log.info("-------添加数据error:" + requestParams.getTitle());
        }
    }

    @Test
    public void add() {
        //遍历每页数据,提取url集合
        String url = "https://www.dy2018.com";
        Set<String> moviesUrlSet = null;
        try {
            moviesUrlSet = getMoviesUrlSet(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int okCount = 0, failCount = 0, totalCount = 0;
        StringBuffer sb = new StringBuffer();
        for (String movieUrl : moviesUrlSet) {
            if ("/i/96585.html".equals(movieUrl) || "/html/gndy/dyzz/20130131/41375.html".equals(movieUrl)) {
                continue;
            }
            log.info("开始执行添加数据,url:" + movieUrl);
            Document document = null;
            try {
                document = Jsoup.connect(url + movieUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (document.childNodeSize() > 0) {
                List<Node> childNodes = document.childNodes();
                //初始化数据
                this.bodyNodes = new LinkedList<>();
                this.imgUrl = new StringBuffer();
                this.content = new StringBuffer();
                getNodes(childNodes);
            }
            Movie requestParams = getOneMovieRequest();
            //发送http请求
            super.httpEntity = new HttpEntity<>(requestParams, httpHeaders);
            ResponseEntity<JSONObject> responseEntity = null;
            try {
                responseEntity = restTemplate.exchange("/insert", HttpMethod.POST, httpEntity, JSONObject.class, "");
            } catch (RestClientException e) {
                e.printStackTrace();
            }
            JSONObject responseResult = responseEntity.getBody();
            if ("000000".equals(responseResult.getString("code"))) {
                okCount++;
                log.info("-------添加数据ok:" + requestParams.getTitle());
            } else {
                failCount++;
                sb.append(requestParams.getTitle() + ",");
                log.info("-------添加数据error:" + requestParams.getTitle());
            }
            totalCount++;
        }

        log.info("总计:" + totalCount + ",成功总数:" + okCount + ",失败总数" + failCount);
        log.info("失败名称:" + sb.toString());
//        Elements elements = document.getAllElements();

    }

    private Movie getOneMovieRequest() {
        StringBuffer downUrl = new StringBuffer();//下载链接
        for (Node node : bodyNodes) {
            if (((Element) node).attr("href").contains("ftp://")) {
                String href = ((Element) node).attr("href");
                if (StringUtils.isEmpty(downUrl.toString())) {
                    downUrl.append(href);
                } else {
                    downUrl.append("," + href);
                }

            }
        }
        String[] downUrls = downUrl.toString().split(",");
        if (downUrls.length > 2) {
            downUrl = new StringBuffer(downUrls[0] + "," + downUrls[1]);
        }
        String[] imgurls = this.imgUrl.toString().split(",");
        if (imgurls.length > 2) {
            this.imgUrl = new StringBuffer(imgurls[0] + "," + imgurls[1]);
        }
        String name = null;//片名
        String name2 = null;//译名
        String age = null;//年代
        String place_of_origin = null;//产地
        String category_id = null;//类别
        String language = null;//语言
        String subtitle = null;//字幕
        String release_time = null;//上映时间
        String douban_score = null;//豆瓣评分
        String IMDb_score = null;//IBM评分
        String file_format = null;//文件格式
        String video_size = null;//视频尺寸
        String file_size = null;//文件大小
        String length = null;//片长
        String director = null;//导演
        String actor = null;//主演
        String summary = null;//简介
        String awards = null;//获奖情况

        if (!this.content.toString().contains(",")) {
            return new Movie();
        }
        String[] split = this.content.toString().split(",");
        for (String string : split) {
            if (string.contains("译　　名")) {
                name2 = string;
            } else if (string.contains("片　　名")) {
                name = string;
            } else if (string.contains("年　　代")) {
                age = string;
            } else if (string.contains("产　　地")) {
                place_of_origin = string;
            } else if (string.contains("类　　别")) {
                category_id = string;
            } else if (string.contains("语　　言")) {
                language = string;
            } else if (string.contains("字　　幕")) {
                subtitle = string;
            } else if (string.contains("上映日期")) {
                release_time = string;
            } else if (string.contains("豆瓣评分")) {
                douban_score = string;
            } else if (string.contains("IMDb评分")) {
                IMDb_score = string;
            } else if (string.contains("文件格式")) {
                file_format = string;
            } else if (string.contains("视频尺寸")) {
                video_size = string;
            } else if (string.contains("文件大小")) {
                file_size = string;
            } else if (string.contains("片　　长")) {
                length = string;
            } else if (string.contains("导　　演")) {
                director = string;
            } else if (string.contains("主　　演")) {
                actor = string;
            } else if (string.contains("简　　介")) {
                summary = string;
            } else if (string.contains("获奖情况")) {
                awards = string;
            }


        }
        Movie movie = new Movie();
        movie.setTitle(this.title);//标题
        movie.setName(name);
        movie.setName2(name2);
        movie.setAge(age);
        movie.setPlaceOfOrigin(place_of_origin);
        movie.setCategoryId(category_id);
        movie.setLanguage(language);
        movie.setSubtitle(subtitle);
        movie.setReleaseTime(release_time);
        movie.setDoubanScore(douban_score);
        movie.setImdbScore(IMDb_score);
        movie.setFileFormat(file_format);
        movie.setVideoSize(video_size);
        movie.setFileSize(file_size);
        movie.setLength(length);
        movie.setDirector(director);
        movie.setActor(actor);
        movie.setSummary(summary);
        movie.setDownloadLink(downUrl.toString());
        movie.setImgUrl(this.imgUrl.toString());
        movie.setCreateUserId(new Integer(1));
        movie.setLastUpdateUserId(new Integer(1));
        movie.setAwards(awards);
        movie.setCreateDate(getPushDate());
        return movie;
    }

    private Date getPushDate() {
        if (!StringUtils.isEmpty(this.createDate) && this.createDate.contains(":")) {
            return null;
        }
        String[] split = this.createDate.split("：");
        if (split.length != 2) {
            return null;
        }
        String date = split[1].trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateResult = null;
        try {
            dateResult = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateResult;
    }

    private Set<String> getMoviesUrlSet(String url) throws IOException, InterruptedException {
        Set<String> movieUrl = new HashSet<>();
        for (int i = 1; i < 302; i++) {
//            Thread.sleep(1000);
            bodyNodes = new LinkedList<>();//清空集合
            content = new StringBuffer();
            imgUrl = new StringBuffer();
            StringBuffer dyttUrl = new StringBuffer(url);
            dyttUrl.append("/html/gndy/dyzz/index");
            if (i == 1) {
                dyttUrl.append(".html");
            } else {
                dyttUrl.append("_" + i).append(".html");
            }

            Document document = Jsoup.connect(dyttUrl.toString()).get();
            if (document.childNodeSize() > 0) {
                List<Node> childNodes = document.childNodes();
                getNodes(childNodes);
            }

            //筛选精准url
            for (Node node : bodyNodes) {
                String title = ((Element) node).attr("title");
                String nodeClass = ((Element) node).attr("class");
                String href = ((Element) node).attr("href");
                if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(nodeClass) && "ulink".equals(nodeClass)) {
                    System.out.println(title + ",href=" + ((Element) node).attr("href"));
                    movieUrl.add(href);
                }

            }
            log.info("获取第" + i + "页数据");
        }
        return movieUrl;
    }

    private void getNodes(List<Node> childNodes) {
        for (Node node : childNodes) {
            //1,Element类型则递归
            if (Element.class.equals(node.getClass())) {
                if (node.childNodeSize() > 0) {
                    List<Node> nodes = node.childNodes();
                    getNodes(nodes);
                }
                //影片截图
                if (node.childNodeSize() == 0 && ((Element) node).tagName().equals("img")) {
                    if (!StringUtils.isEmpty(imgUrl.toString())) {
                        imgUrl.append(",");
                    }
                    imgUrl.append(((Element) node).attr("src"));
                }
            }
            //2，TextNode类型则判断是否是目标<a></a>
            else if (TextNode.class.equals(node.getClass())) {
                Node parentNode = node.parentNode();
                if (Element.class.equals(parentNode.getClass())) {
                    String tagName = ((Element) parentNode).tagName();
                    //获取超链接地址
                    if ("a".equals(tagName)) {
                        bodyNodes.add(parentNode);
                    }
                    //标题
                    if ("h1".equals(tagName)) {
                        String title = ((TextNode) node).getWholeText();
                        if (title.contains("《") && title.contains("》")) {
                            this.title = title;
                        }
                    }
                    //内容
                    if ("p".equals(tagName)) {
                        String title = ((TextNode) node).getWholeText();
                        this.content.append(title + ",");
                    }
                    //发布时间
                    if ("span".equals(tagName) && "updatetime".equals(parentNode.attr("class"))) {
                        String updatetime = ((TextNode) node).getWholeText();
                        this.createDate = updatetime;
                    }

                }
            }
            //截图


        }
    }

    /**
     * 从String提取数字
     *
     * @param string
     * @return
     */
    private Integer getNumFromString(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
                sb.append(string.charAt(i));
            }
        }
        return Integer.valueOf(sb.toString());
    }

    private List<Node> bodyNodes;

    private String title;
    private StringBuffer imgUrl;
    private StringBuffer content;
    private String createDate;


}
