package com.concertbuddy.concertbuddyuser.user;

import com.concertbuddy.concertbuddyuser.song.Song;
import com.concertbuddy.concertbuddyuser.song.SongController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers() {
        List<EntityModel<User>> usersWithLinks = userService.getUsers().stream().map(
                user -> EntityModel.of(
                        user,
                        // list of song links
                        user.getSongs().stream().map(
                                // map song to link
                                song -> linkTo(methodOn(SongController.class).getSongById(song.getId())).withRel("song: "+song.getName())
                        ).toList())
        ).toList();

        return ResponseEntity.ok(CollectionModel.of(usersWithLinks));
    }

    @GetMapping(path="{userId}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable("userId") UUID userId) {
        User user = userService.getUserById(userId);
        EntityModel<User> userWithLinks = EntityModel.of(
                user,
                // list of song links
                user.getSongs().stream().map(
                        // map song to link
                        song -> linkTo(methodOn(SongController.class).getSongById(song.getId())).withRel("song: "+song.getName())
                ).toList());
        return ResponseEntity.ok(userWithLinks);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
    }
}
