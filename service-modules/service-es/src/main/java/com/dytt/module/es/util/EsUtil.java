/*created by chenshi at   2019/5/16*/
package com.dytt.module.es.util;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.utils.StringUtil;
import com.dytt.module.es.entity.EsParams;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-16
 */
@Slf4j
@Service
public class EsUtil {

    @Autowired
    RestHighLevelClient client;

    /**
     * 创建索引index
     *
     * @param index
     */
    public void createIndex(String index) {
        GetIndexRequest getRequest = new GetIndexRequest();
        getRequest.indices(index);
        boolean exists = false;
        try {
            exists = client.indices().exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("----------es查询index:{}出现异常:{}", index, e);
            e.printStackTrace();
        }
        if (exists) {
            log.info("----------es已经存在index:{}", index);
        } else {
            log.info("-----------开始执行es添加index={}", index);
            CreateIndexRequest indexRequest = new CreateIndexRequest(index);
            CreateIndexResponse response = null;
            try {
                response = client.indices().create(indexRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                log.info("-----------开始执行es添加index={}出现异常:{}", index, e);
                e.printStackTrace();
            }
            log.info("------------结束es创建索引index操作,response:{}", response);
        }
    }


    /**
     * 添加es
     *
     * @param requestParams
     */
    public Boolean insertOrUpdate(EsParams requestParams) {
        String index = requestParams.getIndex();
        String type = requestParams.getType();
        JSONObject source = requestParams.getSource();
        String id = source.getString("id");

        if (StringUtil.isNull(id)) {
            throw new RuntimeException("id为空!");
        }

        createIndex(index);

        boolean exists = false;
        try {
            exists = client.exists(new GetRequest(index,type,id){{
                                        fetchSourceContext(new FetchSourceContext(false));
                                        storedFields("_none_");
                                    }},
                                    RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("查询es库中index={},type={},id={}出现异常:{}!", index, type, id, e);
            e.printStackTrace();
            throw new RuntimeException("查询es库中是否存在数据出现异常");
        }
        if (exists) {
            log.info("es库中index={},type={},id={}已存在,执行修改操作...", index, type, id);
            UpdateResponse updateResponse = null;
            try {
                updateResponse = client.update(new UpdateRequest(index, type, id).doc(source),
                        RequestOptions.DEFAULT);
                RestStatus status = updateResponse.status();
                log.info("修改返回状态status:{}",status);
                if (!RestStatus.OK.equals(status)) {
                    log.info("修改状态status不符合，操作失败!");
                    return false;
                }
            } catch (IOException e) {
                log.info("执行修改es库中index={},type={},id={}出现异常:{}!", index, type, id, e);
                e.printStackTrace();
            }
        } else {
            log.info("es库中index={},type={},id={}不存在,执行新增操作!", index, type, id);
            try {
                IndexResponse indexResponse = client.index(new IndexRequest(index, type, id).source(source),
                        RequestOptions.DEFAULT);
                RestStatus status = indexResponse.status();
                log.info("新增返回值status:{}",status);
                if (!RestStatus.CREATED.equals(status)) {
                    log.info("新增状态status不符合，操作失败！");
                    return false;
                }
            } catch (IOException e) {
                log.info("es库中index={},type={},id={}执行新增操作出现异常:{}!", index, type, id, e);
                e.printStackTrace();
            }

        }
        log.info("新增/修改状态status符合，操作成功！");
        return true;
    }

    /**
     * 查询es
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public Map get(String index, String type, String id) {
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse documentFields = null;
        try {
            documentFields = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("-------------查询es出现异常:{}", e);
            e.printStackTrace();
        }
        log.info("----------查询得到es的数据:{}", documentFields);
        Map<String, Object> source = documentFields.getSource();
        return source;
    }

    public Boolean delete(String index, String type, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("删除数据出现异常:{}", e);
            e.printStackTrace();
        }
        log.info("----------删除es的数据:{}", deleteResponse);
        RestStatus status = deleteResponse.status();
        if (!RestStatus.OK.equals(status)) {
            log.info("status:{},操作失败",status);
            return false;
        }
        log.info("操作成功！");
        return true;
    }

    /**
     * es搜索
     *
     * @param index
     * @param sourceBuilder
     * @return
     */
    public List<Map> search(String index, SearchSourceBuilder sourceBuilder) {
        log.info("-------------开始执行es搜索...");
        SearchResponse response = null;
        try {
            response = client.search(new SearchRequest(index).source(sourceBuilder),
                    RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("------------es搜索出现异常:{}", e);
            e.printStackTrace();
        }
        log.info("-----------执行完es搜索,result:{}", response);
        RestStatus status = response.status();
        if (!RestStatus.OK.equals(status)) {
            log.info("查询失败");
            throw new RuntimeException("查询es返回status异常!");
        }

        SearchHit[] hits = response.getHits().getHits();
        return new ArrayList<Map>(){{
            Arrays.stream(hits).forEach(hit->add(hit.getSourceAsMap()));
        }};
    }
}
