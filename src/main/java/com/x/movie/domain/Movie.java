package com.x.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-9-24
 * @Version V1.0
 **/
@Data
public class Movie {
    @Id
    public String id;

    public String fileName;

    public String magnetUrl;

    public String downloadUrl;
}
