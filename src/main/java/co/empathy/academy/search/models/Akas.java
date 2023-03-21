package co.empathy.academy.search.models;

public class Akas {
    private String title;
    private String region;
    private String language;
    String isOriginal;

    public Akas(String title, String region, String language, String isOriginal) {
        this.title = title;
        this.region = region;
        this.language = language;
        this.isOriginal = isOriginal;
    }
}
