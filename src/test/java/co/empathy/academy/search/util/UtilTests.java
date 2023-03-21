package co.empathy.academy.search.util;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UtilTests {

   /* @Test
    void test1() throws IOException {
        ReadFile r = new ReadFile();
        /*Path path = Paths.get("/Users/noeliaiglesias/Documents/Files " +
                "imdb/title.basics.tsv");
        String name = "title.basics.tsv";
        String originalFileName = "title.basics.tsv";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

        MultipartFile multipartFile = new MockMultipartFile("title.basics.tsv",
                new FileInputStream(new File("/Users/noeliaiglesias/Documents" +
                        "/Files imdb/title.basics.tsv")));


        r.read(multipartFile);
    }*/
}
