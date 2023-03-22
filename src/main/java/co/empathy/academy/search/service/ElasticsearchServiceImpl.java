package co.empathy.academy.search.service;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.models.Book;
import co.empathy.academy.search.repositories.ElasticsearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private final ElasticsearchEngine elasticsearchEngine;


    @Autowired
    public ElasticsearchServiceImpl(ElasticsearchEngine elasticsearchEngine) {
        this.elasticsearchEngine = elasticsearchEngine;
    }

    @Override
    public void createIndex(InputStream input) {
        elasticsearchEngine.createIndex(input);
    }

    @Override
    public GetIndexResponse getIndixes() {
        return elasticsearchEngine.getIndexes();
    }

    @Override
    public void indexDocument(Book book) {
        elasticsearchEngine.indexDocument(book);
    }
}
