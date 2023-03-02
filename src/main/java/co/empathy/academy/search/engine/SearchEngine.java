package co.empathy.academy.search.engine;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import java.io.IOException;

public interface SearchEngine {

    String executeQuery(String query);

    String getCluster() throws IOException;
}
