package co.empathy.academy.search.service.engine;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public interface SearchEngine {

    String executeQuery(String query);

    ElasticsearchClient getElasticSearchClient();
}
