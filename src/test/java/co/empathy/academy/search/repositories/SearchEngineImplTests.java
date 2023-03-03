package co.empathy.academy.search.repositories;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SearchEngineImplTests {

    @Test
    void givenSearchEngine_getClusterMethod_returnClusterName() throws IOException {
        SearchEngineImpl searchEngine = mock(SearchEngineImpl.class);
        String expected = "docker-cluster";
        given(searchEngine.getCluster()).willReturn(expected);
    }
}
