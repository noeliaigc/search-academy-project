package co.empathy.academy.search.repositories;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.empathy.academy.search.configuration.ElasticSearchConfig;
import co.empathy.academy.search.models.Book;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ElasticsearchEngineImpl implements ElasticsearchEngine {

    private static final String INDEX = "books";
    private final ElasticSearchConfig elasticSearchConfig;

    @Autowired
    public ElasticsearchEngineImpl(ElasticSearchConfig elasticSearchConfig) {
        this.elasticSearchConfig = elasticSearchConfig;
    }

    @Override
    public void createIndex(InputStream input) {
        try {

            elasticSearchConfig.getElasticClient().indices().create(b -> b.index(
                    INDEX).withJson(input));
        }catch(IOException e){
            System.out.println("error");
        }
    }

    @Override
    public GetIndexResponse getIndexes() {
        try {
            GetIndexResponse request =
                    elasticSearchConfig.getElasticClient().indices().get(c -> c.index(INDEX));
            IndexState is = request.get("books");
            return request;
        }catch(IOException e){

        }
        return null;
    }

    @Override
    public void indexDocument(Book book) {
        try {
            IndexResponse ir =
                    elasticSearchConfig.getElasticClient().index(i -> i.index(
                            INDEX).id(book.getId()).document(book));
            System.out.println("indexed " + ir.version());
        }catch(IOException e){
            System.out.println("error");
        }
    }

    @Override
    public void indexDocumentById(String id, Book book) {
        try{
            IndexResponse ir =
                    elasticSearchConfig.getElasticClient().index(i -> i.index(INDEX).id(id).document(book));
            System.out.println("indexed " + ir.version());
        }catch (IOException e){
            System.out.println("Cannot be indexed");
        }
    }

    @Override
    public List<Book> getDocuments() {
        SearchRequest searchRequest =  SearchRequest.of(s -> s.index
        (INDEX));

        List<Book> books = new ArrayList<>();
        try {
            SearchResponse searchResponse = elasticSearchConfig.getElasticClient().search(searchRequest,
                    Book.class);
            List<Hit> hits = searchResponse.hits().hits();

            for (Hit object : hits) {
                System.out.print(((Book) object.source()));
                books.add((Book) object.source());

            }
            return books;
        }catch(IOException e){
            System.out.println("error");
        }
        return books;
    }

    @Override
    public void deleteIndex(String index) {
        try{

            DeleteIndexResponse deleteIndexResponse =
                    elasticSearchConfig.getElasticClient().indices().delete(c -> c.index(INDEX));

            if(deleteIndexResponse.acknowledged()){
                System.out.println(("deleted"));
            }else{
                System.out.println("not deleted");
            }
        }catch(IOException e){
            System.out.println("cannot be deleted");
        }
    }

}
