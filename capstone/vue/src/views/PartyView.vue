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
      <search-song/>
  </div>
  <div class="suggested">
    <ul>
      <li v-for="song in suggestedSongs" :key="song.id">
        <img :src="song.picture" alt="album cover" class="albumImage">
        <div class="song-details">
          <p>{{ song.name }}</p>
          <p>{{ song.artist }}</p>
        </div>
      </li>
    </ul>
  </div>
  
</div>
</template>

<script>
import SearchSong from '../components/SearchSong.vue'
import SongService from '../services/SongService'
export default {
    components: {
        SearchSong
    },
    data() {
        return {
            songs: [],
            suggestedSongs: [],
            currentEventId: -1
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
    }
  },
  created() {
    this.getPlaylist();
    this.getSuggested();
  },
}
</script>

<style>
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
</style>