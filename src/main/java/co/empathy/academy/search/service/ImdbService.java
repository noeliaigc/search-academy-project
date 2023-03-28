package co.empathy.academy.search.service;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.empathy.academy.search.models.Movie;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ImdbService {
    void uploadFiles(MultipartFile basics, MultipartFile akas,
                     MultipartFile ratings, MultipartFile crew,
                     MultipartFile participants) throws IOException;

    void createIndex(InputStream input);

    void indexDocument(Movie movie);


    List<Movie> getDocuments();

    void deleteIndex();


    GetIndexResponse getIndixes();
}
