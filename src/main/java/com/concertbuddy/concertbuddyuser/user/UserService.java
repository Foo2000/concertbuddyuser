package com.concertbuddy.concertbuddyuser.user;
import com.concertbuddy.concertbuddyuser.song.Song;
import com.concertbuddy.concertbuddyuser.song.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public UserService(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        Optional<User> optionalUserById = userRepository.findById(userId);
        if (optionalUserById.isEmpty()) {
            throw new IllegalStateException(
                    "User with id " + userId + " does not exist"
            );
        }
        return optionalUserById.get();
    }

    public void addNewUser(User user) {
        Optional<User> optionalUserByEmail = userRepository.findUserByEmail(user.getEmail());
        if (optionalUserByEmail.isPresent()) {
            throw new IllegalStateException("This email is already taken by an existing user");
        }
        userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "User with id " + userId + " does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

    public void addNewUserSong(UUID userId, UUID songId) {
        Optional<User> optionalUserById = userRepository.findById(userId);
        if (optionalUserById.isEmpty()) {
            throw new IllegalStateException(
                    "User with id " + userId + " does not exist"
            );
        }
        User userById = optionalUserById.get();
        Optional<Song> optionalSongById = songRepository.findById(songId);
        if (optionalSongById.isEmpty()) {
            throw new IllegalStateException(
                    "Song with id " + songId + " does not exist"
            );
        }
        Song songById = optionalSongById.get();
        // add songById to songs of userById
        List<Song> newUserByIdSongs = userById.getSongs();
        newUserByIdSongs.add(songById);
        userById.setSongs(newUserByIdSongs);
        // save the updated userById to database
        userRepository.save(userById);
    }

    public void deleteUserSong(UUID userId, UUID songId) {
        Optional<User> optionalUserById = userRepository.findById(userId);
        if (optionalUserById.isEmpty()) {
            throw new IllegalStateException(
                    "User with id " + userId + " does not exist"
            );
        }
        User userById = optionalUserById.get();
        Optional<Song> optionalSongById = songRepository.findById(songId);
        if (optionalSongById.isEmpty()) {
            throw new IllegalStateException(
                    "Song with id " + songId + " does not exist"
            );
        }
        Song songById = optionalSongById.get();
        // delete songById from songs of userById
        List<Song> newUserByIdSongs = userById.getSongs();
        newUserByIdSongs.remove(songById);
        userById.setSongs(newUserByIdSongs);
        // save the updated userById to database
        userRepository.save(userById);
    }

    public void SpotifySync() {
        // Spotify API call goes here
    }
}
