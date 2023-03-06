package co.empathy.academy.search.repositories;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.empathy.academy.search.configuration.ElasticSearchConfig;
import co.empathy.academy.search.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public  class UserEngineImpl {

    private final String indexName = "user";
    private final ElasticSearchConfig elasticSearchConfig;


    @Autowired
    protected UserEngineImpl(ElasticSearchConfig elasticSearchConfig) {
        this.elasticSearchConfig = elasticSearchConfig;
    }



    public void create(User user) throws IOException {
        IndexResponse response = elasticSearchConfig.getElasticClient().index(i -> i
                .index(indexName)
                .id(user.getId())
                .document(user)
        );
        if(response.result().name().equals("Created")){
            System.out.println("Document has been successfully created.");
        }
    }
}
