package co.empathy.academy.search.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImdbService {
    void uploadFiles(MultipartFile basics, MultipartFile akas,
                     MultipartFile ratings, MultipartFile crew,
                     MultipartFile participants) throws IOException;

}
