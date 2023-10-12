package com.concertbuddy.concertbuddyuser.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        Optional<User> optionalUserByEmail =  userRepository.findUserByEmail(user.getEmail());
        if (optionalUserByEmail.isPresent()) {
            throw new IllegalStateException("This email is already taken by an existing user");
        }
        userRepository.save(user);
    }

    public void updateUser(UUID userId, User user) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "User with id " + userId + " does not exist"
            );
        }
        user.setId(userId);
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

}
