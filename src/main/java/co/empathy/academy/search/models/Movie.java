package co.empathy.academy.search.models;

import java.util.List;

public class Movie {
    private String titleId;
    private String primaryTitle;
    private String originalTitle;
    private int isAdult;
    private int startYear;
    private int endYear;

    private String[] genres;
    double avgRating;
    int numVotes;
    List<Akas> akas;

    public Movie(String titleId, String primaryTitle, String originalTitle,
                 int isAdult, int startYear, int endYear, String[] genres,
                 double avgRating, int numVotes, List<Akas> akas) {
        this.titleId = titleId;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.genres = genres;
        this.avgRating = avgRating;
        this.numVotes = numVotes;
        this.akas = akas;
    }
}
