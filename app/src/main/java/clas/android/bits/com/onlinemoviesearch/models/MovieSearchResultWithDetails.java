package clas.android.bits.com.onlinemoviesearch.models;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResultWithDetails {
    private List<MovieDetailsDataModel> movieDetailList;
    private String totalResults;
    private String Response;

    public MovieSearchResultWithDetails(Result result) {
        this.totalResults = result.totalResults;
        this.Response = result.Response;
        movieDetailList = new ArrayList<>();
    }

    public void addToList(MovieDetailsDataModel detail) {
        movieDetailList.add(detail);
    }

    public List<MovieDetailsDataModel> getMovieDetailList() {
        return movieDetailList;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return Response;
    }
}
