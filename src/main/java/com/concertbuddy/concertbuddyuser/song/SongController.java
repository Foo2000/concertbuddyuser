package com.concertbuddy.concertbuddyuser.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping(path="{songId}")
    public Song getSongById(@PathVariable("songId") UUID songId) {
        return songService.getSongById(songId);
    }

    @PostMapping
    public void registerNewSong(@RequestBody Song song) {
        songService.addNewSong(song);
    }

    @PutMapping
    public void updateSong(@RequestBody Song song) {
        songService.addNewSong(song);
    }

    @DeleteMapping(path="{songId}")
    public void deleteSong(@PathVariable("songId") UUID songId) {
        songService.deleteSong(songId);
    }
}
