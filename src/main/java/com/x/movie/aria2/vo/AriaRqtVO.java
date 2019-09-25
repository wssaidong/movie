package com.x.movie.aria2.vo;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-9-25
 * @Version V1.0
 **/
@Data
public class AriaRqtVO {

    private String id;
    @Getter
    private String jsonrpc = "2.0";
    @Getter
    private String method = "aria2.addUri";

    private String token;

    private String url;

    private String dir;
    @Getter
    private List<Object> params = new ArrayList<>();

    public void build(){
        params.add("token:"+token);
        params.add(new String[]{url});
        Map option = new HashMap<>();
        option.put("dir",dir);
        params.add(option);
    }
}

