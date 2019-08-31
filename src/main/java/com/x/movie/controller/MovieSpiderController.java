package com.x.movie.controller;

import com.x.movie.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-8-29
 * @Version V1.0
 **/

@Slf4j
@RestController
@RequestMapping(value = {"/api/movie/spider"})
public class MovieSpiderController {

    @Autowired
    private SpiderService spiderService;

    @GetMapping("/start")
    public void start() throws Exception {
        spiderService.captureMovieUrl();
    }
}
