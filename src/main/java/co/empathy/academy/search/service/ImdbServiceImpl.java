package co.empathy.academy.search.service;

import co.empathy.academy.search.models.Movie;
import co.empathy.academy.search.util.ImbdReader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImdbServiceImpl implements ImdbService{
    private ImbdReader reader;

    @Async
    @Override
    public void uploadFiles(MultipartFile basics, MultipartFile akas, MultipartFile ratings) throws IOException {
        try{
            if(basics.isEmpty() || akas.isEmpty() || ratings.isEmpty() ){
                throw new IOException("File is empty");
            }

            List<Movie> movies = new ArrayList<>();
            reader = new ImbdReader(basics, akas, ratings);


            while(movies.size() <= 800) {
                reader.getLines();
                Movie movie = reader.getMovie();
                movies.add(movie);
            }


        }catch(IOException exception){
            throw exception;
        }
    }
}
