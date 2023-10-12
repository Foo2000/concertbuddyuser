package com.concertbuddy.concertbuddyuser.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="`User`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String name;
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String profilePictureUrl;
    @ElementCollection
    private List<UUID> songIds;
    @ElementCollection
    private List<UUID> concertIds;

    public User() {
    }

    public User(UUID id, String name,
                LocalDate dateOfBirth,
                String email, String password,
                String profilePictureUrl,
                List<UUID> songIds,
                List<UUID> concertIds) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.songIds = songIds;
        this.concertIds = concertIds;
    }

    public User(String name,
                LocalDate dateOfBirth,
                String email,
                String password,
                String profilePictureUrl,
                List<UUID> songIds,
                List<UUID> concertIds) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.songIds = songIds;
        this.concertIds = concertIds;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public List<UUID> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<UUID> songIds) {
        this.songIds = songIds;
    }

    public List<UUID> getConcertIds() {
        return concertIds;
    }

    public void setConcertIds(List<UUID> concertIds) {
        this.concertIds = concertIds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", songIds=" + songIds +
                ", concertIds=" + concertIds +
                '}';
    }
}
