package co.empathy.academy.search;

import jakarta.json.JsonValue;
import net.minidev.json.JSONObject;
import org.example.ClusterName;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class HelloWorldController {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name){

        return "Hello " + name;
    }
    @GetMapping("/search")
    public JSONObject request(@RequestParam String query) throws IOException {
        ClusterName cn = new ClusterName();
        String clusterName = cn.getClusterName().getString("cluster_name");
        JSONObject result = new JSONObject();
        result.put("query", query);
        result.put("cluster-name", clusterName);

        return result;
    }



}
