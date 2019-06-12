package com.dytt.module.es.resource;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.services.EsRemoteService;
import com.dytt.module.es.constant.EsIndexConstant;
import com.dytt.module.es.entity.EsParams;
import com.dytt.module.es.util.EsUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-16
 */
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
public class EsRemoteResource implements EsRemoteService {

    @Autowired
    EsUtil esUtil;

    @Override
    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody JSONObject params) {
        return esUtil.insertOrUpdate(new EsParams() {{
            setIndex(EsIndexConstant.User.index);
            setType(EsIndexConstant.type);
            setSource(params);
        }});
    }

    @Override
    @GetMapping("/searchUser")
    public List<Map> searchUser(@RequestParam("key") String key, @RequestParam("value") String value) {
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(QueryBuilders.termQuery(key,value));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        // 获取记录数，默认10
        sourceBuilder.from(0);
        sourceBuilder.size(100);
        return esUtil.search(EsIndexConstant.User.index,sourceBuilder);
    }

    @Override
    @GetMapping("/deleteUser")
    public Boolean deleteUser(@RequestParam("id") String id) {
        return esUtil.delete(EsIndexConstant.User.index, EsIndexConstant.type, id);
    }

}
