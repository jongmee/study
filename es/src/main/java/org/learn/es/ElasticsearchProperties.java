package org.learn.es;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {

    private List<String> hosts;
    private int socketTimeout;
    private int connectionTimeout;

    public HttpHost[] getHosts() {
        return hosts.stream()
                .map(HttpHost::create)
                .toArray(HttpHost[]::new);
    }
}
