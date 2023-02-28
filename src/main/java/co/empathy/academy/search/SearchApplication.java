package co.empathy.academy.search;

import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import org.example.ClusterName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import net.minidev.json.JSONObject;

import java.io.IOException;

@SpringBootApplication
@RestController
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}


	@GetMapping("/search")
	public JSONObject request(@RequestParam String query) throws IOException {
		ClusterName cn = new ClusterName();
		JsonValue jo = cn.getClusterName().get("cluster_name");
		String s = jo.toString();
		JSONObject aux = new JSONObject();
		aux.put("query", query);
		aux.put("cluster-name", s);

		return aux;
	}






}
