package com.x.movie.scheduler;

import com.x.movie.controller.MovieSpiderController;
import com.x.movie.service.MovieService;
import com.x.movie.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-8-29
 * @Version V1.0
 **/
@Slf4j
@Component
public class SpiderTask {

    @Autowired
    private MovieService movieService;
    @Autowired
    private SpiderService spiderService;

    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void task() throws Exception {
        spiderService.captureMovieUrl();

        movieService.downloadMovie();
    }
}
