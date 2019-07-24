/*created by chenshi at   2019/5/14*/
package com.dytt.module.es.resource;

import com.dytt.common.mvc.ResponseResult;
import com.dytt.common.utils.MapUtil;
import com.dytt.module.es.entity.EsParams;
import com.dytt.module.es.util.EsUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-14
 */
@Slf4j
@RestController()
@RequestMapping(produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
public class EsController {

    @Autowired
    EsUtil  esUtil;

    /**
     * 创建索引index
     *
     * @param index
     * @return
     * @throws IOException
     */
    @GetMapping(value = {"/createIndex"})
    public ResponseResult add(@RequestParam String index) throws IOException {
        esUtil.createIndex(index);
        return new ResponseResult("index=" + index + "创建成功!");
    }


    @PostMapping(value = {"/insertOrUpdate"})
    public ResponseResult insertOrUpdate(@RequestBody EsParams requestParams) throws IOException {
        Boolean result = esUtil.insertOrUpdate(requestParams);
        return new ResponseResult(result);
    }

    @GetMapping(value = {"/get/{index}/{type}/{id}"})
    public ResponseResult get(@PathVariable("index") String index,
                              @PathVariable("type") String type,
                              @PathVariable("id") String id) throws IOException {
        Map source = esUtil.get(index, type, id);
        return new ResponseResult(MapUtil.toJson(source));
    }

    @GetMapping(value = {"/delete/{index}/{type}/{id}"})
    public ResponseResult delete(@PathVariable("index") String index,
                                 @PathVariable("type") String type,
                                 @PathVariable("id") String id) throws IOException {
        Boolean result = esUtil.delete(index, type, id);
        return new ResponseResult(result);
    }

    @GetMapping(value = {"/search/{index}"})
    public ResponseResult search(@PathVariable("index") String index,
                                 @RequestParam("key") String key,
                                 @RequestParam("value") String value){

        List<Map> result = esUtil.search(index, new SearchSourceBuilder()
                .query(
                        QueryBuilders.boolQuery().must(QueryBuilders.termQuery(key,value)).boost(1)
                )
//                .query(QueryBuilders.boolQuery().must(QueryBuilders.multiMatchQuery(value,key,"namt","title")))
                .from(0)
                .size(100));
        return new ResponseResult(result);
    }

    @GetMapping(value = {"/hi"})
    public ResponseResult   hi(){
        return new ResponseResult("啊啊啊呃呃呃嗡嗡嗡 hello word!");
    }

}
