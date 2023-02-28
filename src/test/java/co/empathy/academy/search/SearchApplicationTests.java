package co.empathy.academy.search;

import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SearchApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void test1() throws Exception{
		SearchApplication s = new SearchApplication();
		String query = "example";
		JSONObject result = s.request(query);
		JSONObject expected = new JSONObject();
		expected.put("query", query);
		expected.put("cluster-name", result.get("cluster-name"));
		assertEquals(result, expected);
	}



}
