package com.x.movie.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.x.movie.domain.repository.MovieRepository;
import com.x.movie.service.Constant;
import com.x.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List list = movieRepository.findAll();
        list.stream().forEach(movie -> {

        });
    }

    private Boolean checkfileIsExist(String fileName){
        List<String> fileList = FileUtil.listFileNames(Constant.movieSavePath);
        log.info(JSONUtil.toJsonStr(fileList));
        Long count = fileList.stream().filter(x -> x.indexOf("fileName") > -1).count();
        log.info(fileName, " count =" ,count);
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}

