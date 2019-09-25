package com.x.movie.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.x.movie.aria2.Aria2Util;
import com.x.movie.domain.Movie;
import com.x.movie.domain.repository.MovieRepository;
import com.x.movie.service.Constant;
import com.x.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-8-29
 * @Version V1.0
 **/
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void downloadMovie() {
        List<Movie> list = movieRepository.findAll();
        list.stream().forEach(movie -> {
            if(!checkFileIsExist(movie.getFileName())){
                Aria2Util.addUri(movie.getFileName(),movie.getMagnetUrl());
            }
        });
    }

    private Boolean checkFileIsExist(String fileName){
        List<String> fileList = FileUtil.listFileNames(Constant.downloadPath);
        log.info(JSONUtil.toJsonStr(fileList));
        Long count = fileList.stream().filter(x -> x.indexOf("fileName") > -1).count();
        log.info(fileName, " count =" ,count);
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}

