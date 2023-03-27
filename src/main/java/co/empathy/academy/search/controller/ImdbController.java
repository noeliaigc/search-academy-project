package co.empathy.academy.search.controller;

import co.empathy.academy.search.service.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class ImdbController {

    private final ImdbService imdbService;

    @Autowired
    public ImdbController(ImdbService imdbService) {
        this.imdbService = imdbService;
    }

    @PostMapping("/uploadMovies")
    public ResponseEntity uploadFiles(@RequestParam("basics") MultipartFile basics,
                                      @RequestParam("akas") MultipartFile akas,
                                      @RequestParam("ratings") MultipartFile ratings,
                                      @RequestParam("crew") MultipartFile crew,
                                      @RequestParam("principals") MultipartFile principals){
        try {
            imdbService.uploadFiles(basics, akas, ratings,crew, principals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.accepted().build();
    }
}
