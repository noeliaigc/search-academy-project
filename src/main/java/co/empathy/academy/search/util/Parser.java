package co.empathy.academy.search.util;

import co.empathy.academy.search.models.Akas;
import co.empathy.academy.search.models.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private BufferedReader basics;
    private BufferedReader akas;
    private BufferedReader ratings;

    List<String> basicFileLines = new ArrayList<>();
    List<String> akasFileLines = new ArrayList<>();
    List<String> ratingsFileLines = new ArrayList<>();

    List<String> copyRatings;
    List<String> copyAkas;

    public Parser(MultipartFile basics, MultipartFile akas, MultipartFile ratings) {
        this.basics = getBufferedReader(basics);
        this.akas = getBufferedReader(akas);
        this.ratings = getBufferedReader(ratings);

    }

    public void getLines() throws IOException {
        basicFileLines = basics.lines().collect(Collectors.toList());
        basicFileLines.remove(0);
        akasFileLines = akas.lines().collect(Collectors.toList());
        akasFileLines.remove(0);
        ratingsFileLines = ratings.lines().collect(Collectors.toList());
        ratingsFileLines.remove(0);

        //copyRatings = new ArrayList<>(ratingsFileLines);
        //copyAkas = new ArrayList<>(akasFileLines);
    }

    public BufferedReader getBufferedReader(MultipartFile file){
        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(file.getInputStream()));
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String check(String part) {
        if(part.equals("\\N")){
            return "0";
        }
        return part;
    }

    public List<Movie> getMovies() throws IOException {
        List<Movie> movies = doBasics(basicFileLines);
        return movies;
    }

    private List<Movie> doBasics(List<String> basicFileLines) {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < basicFileLines.size() - 1; i++){
            String line = basicFileLines.get(i);
            basicFileLines.remove(i);
            Movie movie = parseBasicsLine(line);
            movies.add(movie);
        }
        return movies;
    }

    private Movie parseBasicsLine(String line){
        String[] parts = line.split("\t");
        String titleId = check(parts[0]);
        String primaryTitle = check(parts[2]);
        String originalTitle = check(parts[3]);
        int isAdult = Integer.parseInt(check(parts[4]));
        int startYear = Integer.parseInt(check(parts[5]));
        int endYear = Integer.parseInt(check(parts[6]));
        String genres[]= parts[8].split(",");

        //ratings file
        String[] rtngs = doRatings(titleId);
        double avgRating = Double.parseDouble(check(rtngs[1]));
        int numVotes = Integer.parseInt(check(rtngs[2]));

        List<Akas> akas = doAkas(titleId);

        return new Movie(titleId, primaryTitle, originalTitle, isAdult,
                startYear, endYear, genres, avgRating, numVotes, akas);
    }

    private List<Akas> doAkas(String titleId) {
        List<Akas> akas = new ArrayList<>();
        for(int i = 0; i < akasFileLines.size() - 1; i=0){
            String line = akasFileLines.get(i);
            String id = line.split("\t")[0];
            if(id.equals(titleId)){
                String[] parts = line.split("\t");
                Akas aka = new Akas(parts[2], parts[3], parts[4], parts[7]);
                akas.add(aka);
                akasFileLines.remove(i);
            }else{
                break;
            }
        }
        return akas;
    }

    private String[] doRatings(String titleId) {
        for(int i = 0; i < ratingsFileLines.size() - 1; i++){
            String line = ratingsFileLines.get(i);
            String id = line.split("\t")[0];
            if(id.equals(titleId)){
                ratingsFileLines.remove(i);
                return line.split("\t");
            }
        }
        return new String[] {"id", "0.0", "0"};
    }
}
