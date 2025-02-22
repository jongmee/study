package org.learn.es;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(ElasticsearchProperties properties) {
        return new RestHighLevelClient(
                RestClient.builder(properties.getHosts())
                        .setHttpClientConfigCallback(this::buildHttpClientConfigCallback)
                        .setRequestConfigCallback(builder -> buildRequestConfig(builder, properties))
        );
    }

    private HttpAsyncClientBuilder buildHttpClientConfigCallback(HttpAsyncClientBuilder builder) {
        CredentialsProvider credentialProvider = new BasicCredentialsProvider();
        return builder.setDefaultCredentialsProvider(credentialProvider);
    }

    private RequestConfig.Builder buildRequestConfig(RequestConfig.Builder builder,
                                                     ElasticsearchProperties properties) {
        return builder.setConnectTimeout(properties.getConnectionTimeout())
                .setSocketTimeout(properties.getSocketTimeout());
    }
}
