package co.empathy.academy.search.controller;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.models.Book;
import co.empathy.academy.search.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class IndexController {

    private final ElasticsearchService elasticsearchService;

    @Autowired
    public IndexController(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }


    @PutMapping("/books")
    public void createIndex(@RequestBody MultipartFile file){
        try {
            InputStream input = file.getInputStream();
            elasticsearchService.createIndex(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/_cat/indices")
    public String getIndices(){
        return elasticsearchService.getIndixes().toString();
    }

    @PostMapping("/books/_doc")
    public void indexDocument(@RequestBody Book book){
        elasticsearchService.indexDocument(book);

    }

    @PutMapping("/books/_doc/{id}")
    public void indexPutDocument(String id, @RequestBody Book book){
        elasticsearchService.indexDocumentById(id, book);
    }

    @GetMapping("/books/_search")
    public ResponseEntity<List<Book>> getDocuments(){
        return ResponseEntity.ok(elasticsearchService.getDocuments());
    }

    @DeleteMapping("/books/delete")
    public void deleteIndex(@RequestParam String index){
        elasticsearchService.deleteIndex(index);
    }
}
