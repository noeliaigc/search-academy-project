package co.empathy.academy.search.service;

import co.empathy.academy.search.models.Movie;
import co.empathy.academy.search.util.Parser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImdbServiceImpl implements ImdbService{
    private Parser parser;

    @Async
    @Override
    public void uploadFiles(MultipartFile basics, MultipartFile akas, MultipartFile ratings) throws IOException {
        try{
            if(basics.isEmpty() || akas.isEmpty() || ratings.isEmpty() ){
                throw new IOException("File is empty");
            }

            parser = new Parser(basics, akas, ratings);
            parser.getLines();
            List<Movie> movies = parser.getMovies();
            int size = movies.size();


        }catch(IOException exception){
            throw exception;
        }
    }
}
