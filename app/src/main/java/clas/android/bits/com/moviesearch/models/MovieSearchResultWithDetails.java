package clas.android.bits.com.moviesearch.models;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResultWithDetails {
    private List<MovieDetailsModel> movieDetailList;
    private String totalResults;
    private String Response;

    public MovieSearchResultWithDetails(Result result) {
        this.totalResults = result.totalResults;
        this.Response = result.Response;
        movieDetailList = new ArrayList<>();
    }

    public void addToList(MovieDetailsModel detail) {
        movieDetailList.add(detail);
    }

    public List<MovieDetailsModel> getMovieDetailList() {
        return movieDetailList;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return Response;
    }
}
