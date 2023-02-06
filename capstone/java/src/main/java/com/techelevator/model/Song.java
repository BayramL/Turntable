package com.techelevator.model;

public class Song {
    private String songId;
    private String name;
    private String artist;
    private int likes;

    public Song() {

    }

    public Song(String songId, String name, String artist, String suggested) {
        this.songId = songId;
        this.name = name;
        this.artist = artist;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
