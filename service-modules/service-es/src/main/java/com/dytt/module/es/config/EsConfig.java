/*created by chenshi at   2019/5/14*/
package com.dytt.module.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenshi
 * @date 2019-05-14
 */
@Configuration
public class EsConfig {

    @Value("${es.host}")
    String  hostName;
    @Value("${es.port}")
    Integer  port;


    @Bean
    public RestHighLevelClient  restHighLevelClient(){
        HttpHost[] httpHosts = new HttpHost[1];
        httpHosts[0]=new HttpHost(hostName,port,"http");
        RestClientBuilder builder = RestClient.builder(httpHosts);
        builder.setRequestConfigCallback(rcc->{
            rcc.setConnectTimeout(1000);
            rcc.setSocketTimeout(30000);
            rcc.setConnectionRequestTimeout(500);
            return rcc;
        });
        builder.setHttpClientConfigCallback(hccc->{
            hccc.setMaxConnPerRoute(100);
            hccc.setMaxConnTotal(100);
            return hccc;
        });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}
