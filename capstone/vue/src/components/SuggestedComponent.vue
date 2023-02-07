<template>
  <div>
    <ul>
      <li v-for="song in songs" :key="song.id">
        <img :src="song.picture" alt="album cover" class="albumImage">
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
import EventServices from '../services/EventService'
export default {
  data() {
    return {
      songs: [],
      currentEventId: -1
    }
  },
  created() {
    EventServices.getEventIdFromDj().then((response) => {
      this.currentEventId = response.data;
      SongService.getSuggested(this.currentEventId)
        .then(response => {
          this.songs = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    });
  },
  method: {
      addSong() {
          console.log("hi");
      },
      deleteSong() {
          console.log("bye");
      }
  }
  
}
</script>

<style scoped>
* {
  font-family: "Righteous", cursive;
  font-size: 15px;
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
