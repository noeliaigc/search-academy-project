package co.empathy.academy.search.repositories;

import java.io.IOException;

public interface SearchEngine {

    String executeQuery(String query);

    String getCluster() throws IOException;
}
