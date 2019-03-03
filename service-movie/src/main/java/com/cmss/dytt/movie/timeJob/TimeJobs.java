/* created by chenshi at 2018-12-10 */
package com.cmss.dytt.movie.timeJob;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TimeJobs {

    /**
     * 定时爬虫资源到本地数据库
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void addMovie() throws IOException {
        Document document = Jsoup.connect("https://www.dy2018.com").get();
        Elements elements = document.getAllElements();
        for (Element element : elements) {
            String attr = element.attr("<a>");
        }
    }
}
