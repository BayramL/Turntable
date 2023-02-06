package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSongDao implements SongDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> getPlaylistSongs(Event event) {
        List<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM songs JOIN event_songs ON songs.song_id = event_songs.song_id WHERE event_songs.event_id = ? AND suggested = 'playlist'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, event.getEventId());
        while (results.next()) {
            list.add(mapToSongSet(results));
        }

        return list;
    }

    @Override
    public List<Song> getSuggestedSongs(Event event) {
        List<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM songs JOIN event_songs ON songs.song_id = event_songs.song_id WHERE event_songs.event_id = ? AND suggested = 'suggested'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, event.getEventId());
        while (results.next()) {
            list.add(mapToSongSet(results));
        }

        return list;
    }

    public void addSongToPlaylist(String songId, int eventId) {
        String sql = "UPDATE event_songs SET suggested = 'playlist' WHERE song_id = ? AND event_id = ?";
        jdbcTemplate.update(sql, songId, eventId);
    }

    public void addSongToSuggested(Song song, int eventId) {
        String sql = "INSERT INTO songs(song_id, name, artist) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO event_songs(song_id, event_id, suggested) VALUES (?, ?, 'suggested')";
        jdbcTemplate.update(sql, song.getSongId(), song.getName(), song.getArtist());
        jdbcTemplate.update(sql2, song.getSongId(), eventId);
    }

    public void deletePlaylistSong(String songId, int eventId) {
        String sql = "DELETE FROM event_songs WHERE song_id = ? AND event_id = ? AND suggested = 'playlist'";
        jdbcTemplate.update(sql, songId, eventId);
    }

    public void deleteSuggestedSong(String songId, int eventId) {
        String sql = "DELETE FROM event_songs WHERE song_id = ? AND event_id = ? AND suggested = 'suggested'";
        jdbcTemplate.update(sql, songId, eventId);
    }

    public Song mapToSongSet(SqlRowSet results) {
        Song song = new Song();
        song.setSongId(results.getString("song_id"));
        song.setName(results.getString("name"));
        song.setArtist(results.getString("artist"));
        song.setLikes(results.getInt("likes"));
        return song;
    }


}
