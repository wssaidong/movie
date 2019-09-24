package com.x.movie.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.x.movie.service.Constant;
import com.x.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void downloadMovie() {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(Constant.movieDataPath));
        List<CsvRow> rows = data.getRows();
        //遍历行
        for (CsvRow csvRow : rows) {
            String fileName = csvRow.get(0);
            String filePath = csvRow.get(1);
            String link = Constant.thunderPath + " " + filePath;
            if(!checkfileIsExist(fileName)){
                RuntimeUtil.execForStr(link);
            }
        }
    }

    private Boolean checkfileIsExist(String fileName){
        List<String> fileList = FileUtil.listFileNames(Constant.movieSavePath);
        log.info(JSONUtil.toJsonStr(fileList));
        Long count = fileList.stream().filter(x -> x.indexOf("fileName") > -1).count();
        log.info(fileName, " count =" ,count);
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static void main(String[] args) {
        List<String> fileList = new ArrayList<>();
        fileList.add("bbbbcccc.mv");
        fileList.stream().filter(x -> x.indexOf("cccc") > -1).count();
    }
}

