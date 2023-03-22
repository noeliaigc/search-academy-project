package co.empathy.academy.search.service;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.models.Book;

import java.io.InputStream;

public interface ElasticsearchService {
    void createIndex(InputStream input);

    GetIndexResponse getIndixes();

    void indexDocument(Book book);
}
