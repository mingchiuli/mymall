package com.atguigu.mymall.search.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 孟享广
 * @date 2021-01-02 11:41 上午
 * @description
 * 1 导入依赖
 * 2 编写配置 给容器中注入一个RestHighLevelClient
 * 3 参API
 */

@Configuration
public class MymallElasticSearchConfig {

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

//    @Bean
//    public RestHighLevelClient esRestClient() {
//        RestClientBuilder builder = RestClient.builder(
//                new HttpHost("localhost", 9200, "http")
//                        new HttpHost("localhost", 9201, "http")
//        );
//        RestHighLevelClient client = new RestHighLevelClient(builder);


//        return client;
//    }


    @Bean(destroyMethod ="close")
    public RestHighLevelClient client() {
        final CredentialsProvider credentialsProvider =new BasicCredentialsProvider();
        Credentials creds =new UsernamePasswordCredentials("elastic", "123456");
        credentialsProvider.setCredentials(AuthScope.ANY, creds);
        RestClientBuilder restClientBuilder =RestClient.builder(new HttpHost("localhost", 9200, "http"))

                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        return new RestHighLevelClient(restClientBuilder);


    }
}
