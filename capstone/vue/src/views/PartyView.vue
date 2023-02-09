<template>
<div class="container">
    <div class="playlist">
        <ul>
        <li v-for="song in songs" :key="song.id">
            <img :src="song.picture" alt="album cover" class="albumImage">
            <div class="song-details">
            <p>{{ song.name }}</p>
            <p>{{ song.artist }}</p>
            </div>
        </li>
        </ul>
    </div>
  <div class="song-search">
      <div class="search-full-container">
      <div class="search-container">
        <input type="text" v-model="searchQuery">
        <button @click="searchSong">Search</button>
        </div>
        <p>Song Name: {{ name }}</p>
        <p>Song Artist: {{ artist }}</p>
        <img :src="picture" alt="Song picture">
        <button @click="addSong">Add Song</button>
      </div>
  </div>
  <div class="suggested">
    <ul>
      <li v-for="song in suggestedSongs" :key="song.id">
        <img :src="song.picture" alt="album cover" class="albumImage">
        <div class="song-details">
          <p>{{ song.name }}</p>
          <p>{{ song.artist }}</p>
        </div>
        <button v-bind:class="{ 'highlighted': highlighted }" @click="highlighted = !highlighted">
        </button>
      </li>
    </ul>
  </div>
  
</div>
</template>

<script>
import SongService from '../services/SongService'
export default {
    data() {
        return {
            songs: [],
            suggestedSongs: [],
            currentEventId: -1,
            searchQuery: '',
            songId: null,
            name: null,
            artist: null,
            picture: null,
        }
    },
    methods: {
    getPlaylist() {
      SongService.getPlaylist(this.$route.params.id)
        .then(response => {
          this.songs = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    getSuggested() {
      SongService.getSuggested(this.$route.params.id)
        .then(response => {
          this.suggestedSongs = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    searchSong() {
      SongService.searchSong(this.searchQuery)
        .then(response => {
          this.songId = response.data.songId;
          this.name = response.data.name;
          this.artist = response.data.artist;
          this.picture = response.data.picture;
        });
        
    },
    addSong() {
      const eventId = this.$route.params.id;
      const viewedSong = {
        songId: this.songId,
        name: this.name,
        artist: this.artist,
        picture: this.picture
      };
      SongService.addSongToSuggested(viewedSong, eventId)
        .then(() => {
          console.log('Song added successfully!');
        });
        this.getSuggested();
    }
  },
  created() {
    this.getPlaylist();
    this.getSuggested();
  },
}
</script>

<style scoped>
.container {
    display: flex;
    justify-content:space-evenly;
    align-content: center;
}

li {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  background-color: rgba(33, 27, 61, 0.5);
  border-radius: 10px;
  padding-left: 20px;
  border: 1px solid black;
}

.albumImage {
  width: 50px;
  height: 50px;
  margin-right: 20px;
}

.song-details {
  flex: 1;
}

.search-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.song-info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.song-id {
  font-weight: bold;
  margin-bottom: 10px;
}

.picture img {
  width: 200px;
  height: 200px;
  object-fit: cover;
  margin-bottom: 10px;
}

.search-full-container {
    display: flex;
  flex-direction: column;
  align-items: center;
}
</style>