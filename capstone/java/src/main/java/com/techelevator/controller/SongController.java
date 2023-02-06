package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Event;
import com.techelevator.model.Song;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/songs")
public class SongController {
    private EventDao eventDao;
    private SongDao songDao;

    public SongController(EventDao eventDao, SongDao songDao) {
        this.eventDao = eventDao;
        this.songDao = songDao;
    }

    @RequestMapping(path = "/playlist/{eventId}", method = RequestMethod.GET)
    public List<Song> getPlaylistSongs(@PathVariable int eventId) {
        Event event = eventDao.getEventById(eventId);
        return songDao.getPlaylistSongs(event);
    }

    @RequestMapping(path = "/suggested/{eventId}", method = RequestMethod.GET)
    public List<Song> getSuggestedSongs(@PathVariable int eventId) {
        Event event = eventDao.getEventById(eventId);
        return songDao.getSuggestedSongs(event);
    }

    @RequestMapping(path = "/addsong/{eventId}/{songId}", method = RequestMethod.PUT)
    public void addSongToPlaylist(@PathVariable int eventId, @PathVariable String songId) {
        songDao.addSongToPlaylist(songId, eventId);
    }

    @RequestMapping(path = "/addsong/suggested/{eventId}", method = RequestMethod.POST)
    public void addSongToSuggested(@RequestBody Song song, @PathVariable int eventId) {
        songDao.addSongToSuggested(song, eventId);
    }

    @RequestMapping(path = "/deleteSong/playlist/{eventId}/{songId}", method = RequestMethod.DELETE)
    public void deletePlaylistSong(@PathVariable String songId, @PathVariable int eventId) {
        songDao.deletePlaylistSong(songId, eventId);
    }

    @RequestMapping(path = "/deleteSong/suggested/{eventId}/{songId}", method = RequestMethod.DELETE)
    public void deleteSuggestedSong(@PathVariable String songId, @PathVariable int eventId) {
        songDao.deleteSuggestedSong(songId, eventId);
    }


}
