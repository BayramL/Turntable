<template>
  <div>
    <ul>
      <li v-for="song in songs" :key="song.id">
        <img :src="song.picture" alt="album cover">
        <div class="song-details">
          <p>{{ song.name }}</p>
          <p>{{ song.artist }}</p>
        </div>
        <button @click="addSong">Add Song</button>
        <button @click="deleteSong">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import SongService from '../services/SongService'

export default {
  data() {
    return {
      songs: []
    }
  },
  created() {
    SongService.getSuggested(2)
      .then(response => {
        this.songs = response.data;
      })
      .catch(error => {
        console.error(error);
      });
  },
  method: {
      addSong() {
          console.log("hi");
      },
      deleteSong() {
          console.log("bye")
      }
  }
}
</script>

<style>
* {
    font-family: 'Righteous', cursive;
    font-size: 15px;
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

img {
  width: 50px;
  height: 50px;
  margin-right: 20px;
}

.song-details {
  flex: 1;
}
</style>