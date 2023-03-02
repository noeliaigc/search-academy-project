package co.empathy.academy.search.controller;

import co.empathy.academy.search.service.SearchService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    @Autowired
    public SearchController (SearchService searchService){
        this.searchService = searchService;
    }

    /**
     * GET endpoint which will return the query introduced with the cluster name
     * @param query
     * @return
     */
    @GetMapping("")
    public JSONObject request(@RequestParam String query) {
        return searchService.getClusterName(query);
    }


}
