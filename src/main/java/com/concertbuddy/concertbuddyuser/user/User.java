package com.concertbuddy.concertbuddyuser.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name="`User`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    private String email;
    private String password;
    private String profilePictureUrr;

    public User() {
    }

    public User(UUID id, String name,
                LocalDate dateOfBirth,
                String email, String password,
                String profilePictureUrr) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.profilePictureUrr = profilePictureUrr;
    }

    public User(String name,
                LocalDate dateOfBirth,
                String email,
                String password,
                String profilePictureUrr) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.profilePictureUrr = profilePictureUrr;
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

    public String getProfilePictureUrr() {
        return profilePictureUrr;
    }

    public void setProfilePictureUrr(String profilePictureUrr) {
        this.profilePictureUrr = profilePictureUrr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profilePictureUrr='" + profilePictureUrr + '\'' +
                '}';
    }
}
