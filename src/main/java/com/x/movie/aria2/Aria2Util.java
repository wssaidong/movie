package com.x.movie.aria2;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.x.movie.aria2.vo.AriaRqtVO;
import com.x.movie.service.Constant;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-9-25
 * @Version V1.0
 **/
public class Aria2Util {

    public static void addUri(String fileName, String path){
        AriaRqtVO ariaRqtVO = new AriaRqtVO();
        ariaRqtVO.setId(fileName);
        ariaRqtVO.setToken(fileName);
        ariaRqtVO.setUrl(path);
        ariaRqtVO.setDir(Constant.downloadPath);
        ariaRqtVO.build();
        String resp = HttpUtil.post(Constant.aria2RpcServerUrl, JSONUtil.toJsonStr(ariaRqtVO));
    }
}
