package co.empathy.academy.search.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.empathy.academy.search.service.SearchService;
import co.empathy.academy.search.service.SearchServiceImpl;
import co.empathy.academy.search.service.engine.SearchEngine;
import co.empathy.academy.search.service.engine.SearchEngineImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    /**@Bean
    public ElasticsearchClient getElastic(){
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);
        return client;
    }**/

    @Bean
    public SearchEngine searchEngineClient() {
        return new SearchEngineImpl();
    }

    // Spring will handle wiring bean dependencies for us
    // In this case it will retrieve the SearchEngineClient from the context and pass it to our SearchService
    @Bean
    public SearchService searchService(SearchEngine searchEngine) {
        return new SearchServiceImpl(searchEngine);
    }
}
