package clas.android.bits.com.onlinemoviesearch.models;

public class MovieDataModel {
    private String Title;
    private String Year;
    public String imdbID;
    private String Type;
    private String Poster;

    @Override
    public String toString() {
        return "\nMovie{" +
                "title='" + Title + '\'' +
                ", year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", poster='" + Poster + '\'' +
                '}';
    }

}
