package com.techelevator.model;

public class Song {
    private String songId;
    private String name;
    private String artist;
    private String picture;

    public Song() {

    }

    public Song(String songId, String name, String artist, String picture) {
        this.songId = songId;
        this.name = name;
        this.artist = artist;
        this.picture = picture;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
