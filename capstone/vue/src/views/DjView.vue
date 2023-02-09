<template>
<loader-layout> 
  <div class="container">
    <playlist-component class="left"/>
    <spotify-embed class="middle"/>
    <suggested-component class="right" />
  </div>
  </loader-layout>
</template>

<script>
import PlaylistComponent from '../components/PlaylistComponent.vue'
import SpotifyEmbed from '../components/SpotifyEmbed.vue'
import SuggestedComponent from '../components/SuggestedComponent.vue'
import EventServices from '../services/EventService'
export default {
  data() {
      return {
          eventId: -1
      }
  },
  components: { 
      PlaylistComponent, SpotifyEmbed, SuggestedComponent 
  },
  created() {
    EventServices.getEventIdFromDj().then((response) => this.eventId = response.data)
  }

}

</script>

<style>
.container {
  display: flex;
  justify-content: space-between;
}

.left {
  width: 35%;
}

.middle {
  width: 25%;
}

.right {
  width: 35%;
}
</style>