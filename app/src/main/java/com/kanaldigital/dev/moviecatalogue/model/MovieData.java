package com.kanaldigital.dev.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieData implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String name;
    @SerializedName("overview")
    private String desc;
    @SerializedName("release_date")
    private String release;
    @SerializedName("runtime")
    private String duration;
    @SerializedName("vote_average")
    private String imdb;
    @SerializedName("poster_path")
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.desc);
        dest.writeString(this.release);
        dest.writeString(this.duration);
        dest.writeString(this.imdb);
    }

    public MovieData() {
    }

    private MovieData(Parcel in) {
        this.photo = in.readString();
        this.name = in.readString();
        this.desc = in.readString();
        this.release = in.readString();
        this.duration = in.readString();
        this.imdb = in.readString();
    }

    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
