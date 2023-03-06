package co.empathy.academy.search.repositories;

import co.empathy.academy.search.configuration.ElasticSearchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SearchEngineImpl implements SearchEngine{

    private final ElasticSearchConfig elasticSearchConfig;

    @Autowired
    public SearchEngineImpl(ElasticSearchConfig elasticSearchConfig){
        this.elasticSearchConfig = elasticSearchConfig;
    }

    @Override
    public String executeQuery(String query) {
        return "query result";
    }

    /**
     * Calls the elasticsearch client and requests the cluster name
     * @return Elastisearch cluster name
     * @throws IOException
     */
    @Override
    public String getCluster() throws IOException {
        return elasticSearchConfig.getElasticClient().cluster().state().valueBody().toJson().asJsonObject().getString("cluster_name");
    }


}
