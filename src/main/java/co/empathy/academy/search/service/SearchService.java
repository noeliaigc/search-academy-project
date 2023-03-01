package co.empathy.academy.search.service;

import co.elastic.clients.elasticsearch.sql.QueryResponse;
import jakarta.json.JsonObject;
import net.minidev.json.JSONObject;

public interface SearchService {
    String search(String query);
    JSONObject getClusterName(String query);
}
