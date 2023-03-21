package co.empathy.academy.search.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImdbService {
    void uploadFiles(MultipartFile basics, MultipartFile akas, MultipartFile ratings) throws IOException;
}
