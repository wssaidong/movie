package com.x.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-9-24
 * @Version V1.0
 **/
@Data
public class Movie {
    @Id
    private String id;

    private String fileName;

    private String magnetUrl;

    private String downloadUrl;

    private LocalTime createTime;

}
