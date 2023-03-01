package co.empathy.academy.search.controller;

import co.empathy.academy.search.service.SearchService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {
    /**public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }**/

    private final SearchService searchService;
    @Autowired
    public SearchController (SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name){

        return "Hello " + name;
    }

    @GetMapping("/search")
    public JSONObject request(@RequestParam String query) {
        /**searchService.getClusterName(query);
        String uri = "http://localhost:9200";
        RestTemplate rest = new RestTemplate();
        JSONObject result = rest.getForObject(uri, JSONObject.class);
        JSONObject aux = new JSONObject();
        aux.put("query", query);
        aux.put("cluster-name", result.get("cluster_name"));**/
        return searchService.getClusterName(query);
    }


}
