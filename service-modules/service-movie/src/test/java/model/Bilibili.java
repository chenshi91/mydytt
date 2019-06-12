/* created by chenshi at 2018-12-17 */
package model;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Bilibili {

    @Test
    public void sss() throws IOException {
        String bilibiliUrl = "https://www.bilibili.com/v/life/funny/?spm_id_from=333.10.b_7375626e6176.2#/all/click/0/15/2017-08-10,2017-10-17";
        String dyttUrl = "http://www.dy2018.com/";
        /** HtmlUnit请求web页面 */
        WebClient wc = new WebClient(BrowserVersion.CHROME);
        wc.getOptions().setUseInsecureSSL(true);
        wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false); // 禁用css支持
        wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
        wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        wc.getOptions().setDoNotTrackEnabled(false);
        HtmlPage page = wc.getPage(bilibiliUrl);

        DomNodeList<DomElement> links = page.getElementsByTagName("a");

    }

    @Test
    public void add() {
        Document document = null;
        try {
            String bilibiliUrl = "https://www.bilibili.com/v/life/funny/?spm_id_from=333.10.b_7375626e6176.2#/all/click/0/15/2017-08-10,2017-10-17";
            Connection connect = Jsoup.connect(bilibiliUrl);
            connect.maxBodySize(0);
//            connect.header("Host", bilibiliUrl);
            connect.header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0");
//            connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            connect.header("Accept-Language", "zh-cn,zh;q=0.5");
//            connect.header("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
//            connect.header("Connection", "keep-alive");
            document = connect.timeout(30 * 1000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document.childNodeSize() > 0) {
            List<Node> childNodes = document.childNodes();
            //递归调用
            getNodes(childNodes);
        }
    }

    private void getNodes(List<Node> childNodes) {
        for (Node node : childNodes) {
            //1,Element类型则递归
            if (Element.class.equals(node.getClass())) {
                if (node.childNodeSize() > 0) {
                    List<Node> nodes = node.childNodes();
                    getNodes(nodes);
                }

            }
            //2，TextNode类型则判断是否是目标<a></a>
            else if (TextNode.class.equals(node.getClass())) {
                Node parentNode = node.parentNode();
                if (Element.class.equals(parentNode.getClass())) {
                    String tagName = ((Element) parentNode).tagName();
                    //获取超链接地址
                    if ("a".equals(tagName)) {
//                        bodyNodes.add(parentNode);
                    }


                }
            }
            //截图


        }
    }
}
