package co.empathy.academy.search.service;

import co.empathy.academy.search.repositories.SearchEngine;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
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

    /**
     * Creates a JSON object where it will be saved the query performed and the requested cluster name
     *
     * @param query
     * @return JSONObject with the query and cluster name
     */
    @Override
    public JSONObject getClusterName(String query) {
        JSONObject result = new JSONObject();
        result.put("query", query);
        try {
            result.put("cluster-name", searchEngine.getCluster());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
