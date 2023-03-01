package co.empathy.academy.search.service;

import co.empathy.academy.search.controller.SearchController;
import co.empathy.academy.search.service.engine.SearchEngine;
import co.empathy.academy.search.service.engine.SearchEngineImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SearchServiceImplTest {
    @Test
    void givenQuery_whenSearch_thenReturnResult() {
        String exampleQuery = "example query";
        String expectedResult = "result for example query";
        // We create a mock for the SearchEngineClient
        SearchEngine client = mock(SearchEngine.class);
        // We mock the call to executeQuery on the client
        // You can look at it like: "given this method is called with this parameter then it will return this result"
        //System.out.println(client.executeQuery(exampleQuery));

        given(client.executeQuery(exampleQuery)).willReturn(expectedResult);

        SearchService searchService = new SearchServiceImpl(client);

        String result = searchService.search(exampleQuery);
        //System.out.println(result);

        // We assert that the result of what we're testing matches what we're expecting
        assertEquals(expectedResult, result);

        // Sometimes it's useful to check whether specific methods haven been called on a mock, we can do that with Mockito
        verify(client).executeQuery(exampleQuery);
    }

    @Test
    void givenQuery_whenErrorDuringSearch_thenLetItPropagate() {
        String exampleQuery = "example query";
        SearchEngine client = mock(SearchEngine.class);
        // In this case we're going to simulate that the client throws an error, maybe it couldn't connect to Elastic
        given(client.executeQuery(exampleQuery)).willThrow(RuntimeException.class);

        SearchService searchService = new SearchServiceImpl(client);

        // In this case we just check that the exception bubbles up
        assertThrows(RuntimeException.class, () -> searchService.search(exampleQuery));
    }

    @Test
    void givenBlankQuery_whenSearch_thenDoNotExecuteQueryAndReturnEmptyString() {
        SearchEngine client = mock(SearchEngine.class);
        SearchService searchService = new SearchServiceImpl(client);
        String result = searchService.search("   ");

        assertTrue(result.isEmpty());
        // For this test we didn't need to mock any calls because we should've never called the client, we can check that with Mockito
        verifyNoInteractions(client);
    }

    @Test
    void test1(){
        /**SearchEngine client = mock(SearchEngine.class);
        SearchService searchService = new SearchServiceImpl(client);
        searchService.getClusterName("example");**/

        SearchEngine s = new SearchEngineImpl();
        SearchService searchService = new SearchServiceImpl(s);
        System.out.println(searchService.getClusterName("example"));



    }
}
