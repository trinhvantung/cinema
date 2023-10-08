package vn.trinhtung.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
public class ElasticsearchConfig {
    @Bean
    public RestClient getRestClient() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport(RestClient restClient) {
        return new RestClientTransport(restClient, new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient getElasticsearchClient(ElasticsearchTransport elasticsearchTransport) {
        ElasticsearchClient client = new ElasticsearchClient(elasticsearchTransport);
        return client;
    }

}
