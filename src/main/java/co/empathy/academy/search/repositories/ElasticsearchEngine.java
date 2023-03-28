package co.empathy.academy.search.repositories;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.models.Movie;

import java.io.InputStream;
import java.util.List;

public interface ElasticsearchEngine {
    void createIndex(InputStream input);

    void indexDocuments(List<Movie> movies);

    List<Movie> getDocuments();

    void deleteIndex();

    GetIndexResponse getIndexes();
}
