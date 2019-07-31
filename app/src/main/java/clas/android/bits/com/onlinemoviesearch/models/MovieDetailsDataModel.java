package clas.android.bits.com.onlinemoviesearch.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieDetailsDataModel implements Parcelable {
    public String title;
    public String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    public String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    public String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    public String imdbID;
    private String type;
    private String response;

    @Override
    public String toString() {
        return "Detail{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    //Boilerplate code to make the object parcelable
    protected MovieDetailsDataModel(Parcel in) {
        title = in.readString();
        year = in.readString();
        rated = in.readString();
        released = in.readString();
        runtime = in.readString();
        genre = in.readString();
        director = in.readString();
        writer = in.readString();
        actors = in.readString();
        plot = in.readString();
        language = in.readString();
        country = in.readString();
        awards = in.readString();
        poster = in.readString();
        metascore = in.readString();
        imdbRating = in.readString();
        imdbVotes = in.readString();
        imdbID = in.readString();
        type = in.readString();
        response = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(rated);
        dest.writeString(released);
        dest.writeString(runtime);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(writer);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeString(language);
        dest.writeString(country);
        dest.writeString(awards);
        dest.writeString(poster);
        dest.writeString(metascore);
        dest.writeString(imdbRating);
        dest.writeString(imdbVotes);
        dest.writeString(imdbID);
        dest.writeString(type);
        dest.writeString(response);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieDetailsDataModel> CREATOR = new Parcelable.Creator<MovieDetailsDataModel>() {
        @Override
        public MovieDetailsDataModel createFromParcel(Parcel in) {
            return new MovieDetailsDataModel(in);
        }

        @Override
        public MovieDetailsDataModel[] newArray(int size) {
            return new MovieDetailsDataModel[size];
        }
    };
}