package co.empathy.academy.search.util;

import co.empathy.academy.search.models.Akas;
import co.empathy.academy.search.models.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ImbdReader {

    private BufferedReader basics;
    private BufferedReader akas;
    private BufferedReader ratings;

    private String basicsLine;
    private String akasLine;
    private String ratingsLine;



    public ImbdReader(MultipartFile basics, MultipartFile akas, MultipartFile ratings) throws IOException {
        this.basics = getBufferedReader(basics);
        this.akas = getBufferedReader(akas);
        this.ratings = getBufferedReader(ratings);
        getHeaders();

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

    public void getHeaders() throws IOException {
        String headers =
                basics.readLine() + akas.readLine() + ratings.readLine();
    }

    public void getLines() throws IOException {
        basicsLine = basics.readLine();
        akasLine = akas.readLine();
        ratingsLine = ratings.readLine();
    }


    public Movie getMovie() throws IOException {
        String[] parts = basicsLine.split("\t");
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

    private List<Akas> doAkas(String titleId) throws IOException {
        List<Akas> akasList = new ArrayList<>();
        String title;
        while((title = akasLine.split("\t")[0]).equals(titleId)){
            String[] parts = akasLine.split("\t");
            Akas aka = new Akas(parts[2], parts[3], parts[4], parts[7]);
            akasList.add(aka);
            akasLine = akas.readLine();
        }
        return akasList;
    }

    private String[] doRatings(String titleId) {
        String[] parts = ratingsLine.split("\t");
        if(parts[0].equals(titleId)){
            return parts;
        }
        return new String[] {"id", "0.0", "0"};
    }

    private String check(String part) {
        if(part.equals("\\N")){
            return "0";
        }
        return part;
    }
}
