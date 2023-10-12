package com.concertbuddy.concertbuddyuser.song;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name="`Song`")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String artist;
    private String genre;

    public Song() {
    }

    public Song(UUID id, String name, String artist, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    public Song(String name, String artist, String genre) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
