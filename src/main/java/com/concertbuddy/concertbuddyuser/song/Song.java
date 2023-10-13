package com.concertbuddy.concertbuddyuser.song;

import com.concertbuddy.concertbuddyuser.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="`Song`")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String artist;
    @Column(nullable = false)
    private String genre;
    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<User> users;

    public Song() {
    }

    public Song(UUID id, String name, String artist, String genre, List<User> users) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.users = users;
    }

    public Song(String name, String artist, String genre, List<User> users) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", users=" + users +
                '}';
    }
}
