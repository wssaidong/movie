package com.x.movie.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.RuntimeUtil;
import com.x.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
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

    private static String diskFilePath = "D:\\movie.csv";

    private static String tPath = "C:\\Program Files (x86)\\Thunder Network\\Thunder\\Program\\ThunderStart.exe";

    @Override
    public void downloadMovie() {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(diskFilePath));
        List<CsvRow> rows = data.getRows();
        //遍历行
        for (CsvRow csvRow : rows) {
           String filePath = csvRow.get(1);
           String link = tPath + " " + filePath;
           //RuntimeUtil.execForStr(link);
        }
    }

    private void checkfileIsComplete(String file){
        Boolean existis = FileUtil.file(file).exists();
        log.info(file, "is" ,existis);
    }
}

