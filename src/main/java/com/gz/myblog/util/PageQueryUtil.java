package com.gz.myblog.util;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 分页查询参数
 * @Auther:Mr.Guo
 * @create:2023/7/24-10:46
 * @VERSON:1.8
 */

public class PageQueryUtil extends LinkedHashMap<String,Object> {

    //当前页码
    private int page;

    //每条页数
    private int limit;

    public PageQueryUtil(Map<String,Object> params){
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("start",(page - 1) * limit);
        this.put("page",page);
        this.put("limit",limit);
    }

}
