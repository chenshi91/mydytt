/* created by chenshi at 2019-01-02 */
package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class MovieSpider extends AbstractSpider {

    @Test
    public void aaa() throws Exception {
        String url = "https://www.bilibili.com/v/life/funny/?spm_id_from=333.10.b_7375626e6176.2#/all/click/0/15/2017-08-10,2017-10-17";
        String result = AbstractSpider.getResult(url);
        Document document = Jsoup.parse(result);
    }
}
