package co.empathy.academy.search.service;

import co.empathy.academy.search.repositories.SearchEngine;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SearchServiceImplTest {

    @Test
    void givenQuery_WhenExecuted_JSONReturnedWithQueryAndClusterName(){
        SearchServiceImpl service = mock(SearchServiceImpl.class);
        String query = "example";

        JSONObject expected = new JSONObject();
        expected.put("query", query);
        expected.put("cluster-name", "docker-cluster");
        given(service.getClusterName(query)).willReturn(expected);
        assertEquals(service.getClusterName(query).size(), 2);
    }

    @Test
    void givenQuery_WhenExecutedGetClusterNameMethod_AlsoExecutedGetClusterMethod() throws IOException {
        SearchEngine client = mock(SearchEngine.class);
        SearchService searchService = new SearchServiceImpl(client);
        JSONObject result = searchService.getClusterName("example");
        verify(client).getCluster();
    }
}
