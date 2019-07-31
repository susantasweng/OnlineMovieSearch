package clas.android.bits.com.onlinemoviesearch.models;

import java.util.List;

public class Result {
    public List<MovieDataModel> Search;
    public String totalResults;
    public String Response;

    @Override
    public String toString() {
        return "Result{" +
                "Search=" + Search +
                ", totalResults='" + totalResults + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }

}
