package co.empathy.academy.search.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.empathy.academy.search.service.engine.SearchEngine;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class SearchServiceImpl implements SearchService{
    private final SearchEngine searchEngine;

    @Autowired
    public SearchServiceImpl(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public String search(String query) {
        if (!query.isBlank()) {
            return searchEngine.executeQuery(query);
        }
        return "";
    }

    @Override
    public JSONObject getClusterName(String query) {
        JSONObject result = new JSONObject();
        result.put("query", query);
        try {
            result.put("cluster-name", searchEngine.getElasticSearchClient().cluster().state().valueBody().toJson().asJsonObject().getString("cluster_name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
