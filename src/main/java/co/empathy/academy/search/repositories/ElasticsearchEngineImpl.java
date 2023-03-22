package co.empathy.academy.search.repositories;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.configuration.ElasticSearchConfig;
import co.empathy.academy.search.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ElasticsearchEngineImpl implements ElasticsearchEngine {

    private final ElasticSearchConfig elasticSearchConfig;

    @Autowired
    public ElasticsearchEngineImpl(ElasticSearchConfig elasticSearchConfig) {
        this.elasticSearchConfig = elasticSearchConfig;
    }

    @Override
    public void createIndex(InputStream input) {
        try {
            elasticSearchConfig.getElasticClient().indices().create(b -> b.index(
                    "books").withJson(input));
        }catch(IOException e){
            System.out.println("error");
        }
    }

    @Override
    public GetIndexResponse getIndexes() {
        /*SearchRequest searchRequest =  SearchRequest.of(s -> s.index
        ("books"));

        List<Book> books = new ArrayList<>();
        try {
            SearchResponse searchResponse = elasticSearchConfig.getElasticClient().search(searchRequest,
                    Book.class);
            List<Hit> hits = searchResponse.hits().hits();

            for (Hit object : hits) {

                System.out.print(((Book) object.source()));
                books.add((Book) object.source());

            }
            getIndice();
            return books;
        }catch(IOException e){
            System.out.println("error");
        }*/
        try {
            GetIndexResponse request =
                    elasticSearchConfig.getElasticClient().indices().get(c -> c.index("books"));
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
                            "books").id(book.getId()).document(book));
            System.out.println("indexed " + ir.version());
        }catch(IOException e){
            System.out.println("error");
        }

    }

    public GetIndexResponse getIndice(){
        try {
            GetIndexResponse request =
                    elasticSearchConfig.getElasticClient().indices().get(c -> c.index("books"));
            return request;
        }catch(IOException e){

        }
        return null;
    }

}
