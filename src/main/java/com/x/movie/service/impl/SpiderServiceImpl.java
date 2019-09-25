package com.x.movie.service.impl;

import com.x.movie.domain.Movie;
import com.x.movie.domain.repository.MovieRepository;
import com.x.movie.service.Constant;
import com.x.movie.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-8-29
 * @Version V1.0
 **/
@Slf4j
@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void captureMovieUrl() throws Exception {
        log.info("spider start");
        List<String[]> movies = new ArrayList<>();
        Document doc = Jsoup.connect(Constant.movieSrcPath).get();
        Elements elements = doc.getElementsByClass("co_content8");
        Elements tables = elements.get(0).getElementsByTag("ul").get(0).getElementsByTag("table");
        tables.forEach(table -> {
            Movie movie = new Movie();
            Element e = table.getElementsByClass("ulink").get(0);
            String name = e.text();
            String url = e.attr("abs:href");
            movie.setId(name);
            movie.setFileName(name);
            movie.setCreateTime(LocalDateTime.now());
            try {
                Document d = Jsoup.connect(url).get();
                Elements elmList = d.getElementsByTag("a");
                List<Element> aList = elmList.stream().filter(a -> a.attr("href").indexOf("magnet:") > -1).collect(Collectors.toList());
                String magnetUrl = aList.get(0).attr("href");
                movie.setMagnetUrl(magnetUrl);
                Element downloadLink = d.getElementsByAttributeValue("style","WORD-WRAP: break-word").get(0);
                String downloadUrl = downloadLink.text();
                movie.setDownloadUrl(downloadUrl);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            movieRepository.save(movie);
        });

    }
}
