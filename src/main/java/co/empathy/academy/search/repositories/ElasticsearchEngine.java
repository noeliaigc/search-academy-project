package co.empathy.academy.search.repositories;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.models.Book;

import java.io.InputStream;

public interface ElasticsearchEngine {
    void createIndex(InputStream input);

    GetIndexResponse getIndexes();

    void indexDocument(Book book);
}
