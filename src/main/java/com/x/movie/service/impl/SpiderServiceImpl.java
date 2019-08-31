package com.x.movie.service.impl;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import com.x.movie.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-8-29
 * @Version V1.0
 **/
@Slf4j
@Service
public class SpiderServiceImpl implements SpiderService {

    private static String srcPath = "https://www.dytt8.net/html/gndy/dyzz/index.html";

    private static String diskFilePath = "D:/movie.csv";

    @Override
    public void captureMovieUrl() throws Exception {
        log.info("spider start");
        List<String[]> movies = new ArrayList<>();
        Document doc = Jsoup.connect(srcPath).get();
        Elements elements = doc.getElementsByClass("co_content8");
        Elements tables = elements.get(0).getElementsByTag("ul").get(0).getElementsByTag("table");
        tables.forEach(table -> {
            String[] movie = new String[2];
            Element e = table.getElementsByClass("ulink").get(0);
            String name = e.text();
            String url = e.attr("abs:href");
            movie[0] = name;
            try {
                Document d = Jsoup.connect(url).get();
                Element downloadLink = d.getElementsByAttributeValue("style","WORD-WRAP: break-word").get(0);
                movie[1] = downloadLink.text();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            movies.add(movie);
        });
        CsvWriter writer = CsvUtil.getWriter(diskFilePath, CharsetUtil.CHARSET_UTF_8);
        writer.write(movies);
        writer.close();
    }
}
