<template>
  <div class="container">
    <playlist-component class="left"/>
    <spotify-embed class="middle"/>
    <suggested-component class="right"/>
  </div>
</template>

<script>
import PlaylistComponent from '../components/PlaylistComponent.vue'
import SpotifyEmbed from '../components/SpotifyEmbed.vue'
import SuggestedComponent from '../components/SuggestedComponent.vue'

export default {
  components: { PlaylistComponent, SpotifyEmbed, SuggestedComponent },

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