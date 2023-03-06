package co.empathy.academy.search.repositories;

import co.empathy.academy.search.models.User;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public interface SearchEngine {

    String executeQuery(String query);

    String getCluster() throws IOException;
}
