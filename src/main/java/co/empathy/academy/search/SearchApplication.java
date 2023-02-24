package co.empathy.academy.search;

import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.erhlc.RestClients;
import net.minidev.json.JSONObject;

@SpringBootApplication
@RestController
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@RequestMapping("/everything")
	public JSONObject requestEverything(){
		String uri = "http://localhost:9200";
		RestTemplate rest = new RestTemplate();
		JSONObject result = rest.getForObject(uri, JSONObject.class);
		JSONObject aux = new JSONObject();
		aux.put("cluster-name", result.get("cluster_name"));
		return aux;
	}

	@GetMapping("/search")
	public JSONObject request(@RequestParam String query) {
		String uri = "http://localhost:9200";
		RestTemplate rest = new RestTemplate();
		JSONObject result = rest.getForObject(uri, JSONObject.class);
		JSONObject aux = new JSONObject();
		aux.put("query", query);
		aux.put("cluster-name", result.get("cluster_name"));
		return aux;
	}





}
