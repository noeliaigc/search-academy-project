package co.empathy.academy.search.service;

import net.minidev.json.JSONObject;

public interface SearchService {
    String search(String query);
    JSONObject getClusterName(String query);
}
