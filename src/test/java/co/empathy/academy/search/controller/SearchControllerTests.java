package co.empathy.academy.search.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTests {


   @Test
    void givenQuery_whenRequest_returnQueryAndClusterName() throws Exception{
        SearchController search = mock(SearchController.class);
        String query = "example";
        JSONObject res = new JSONObject();
        res.put("query", query);
        res.put("cluster-name", "docker-cluster");
        given(search.request(query)).willReturn(res);
        assertEquals(search.request(query), res);
        assertEquals(search.request(query).size(), 2);
    }
}
