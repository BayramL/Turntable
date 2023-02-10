<template>
<loader-layout> 
  <div class="container">

    <div class="user-avatar">
        <p>Bring on the beats, {{ appUser.username }}!</p>
    </div>

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
          eventId: -1,
          appUser: ""
      }
  },
  components: { 
      PlaylistComponent, SpotifyEmbed, SuggestedComponent 
  },
  created() {
    EventServices.getEventIdFromDj().then((response) => this.eventId = response.data)
  },

  mounted: function () {
    const user = JSON.parse(window.localStorage.getItem("user"));
    this.appUser = user;
  }

}

</script>

<style>
.container {
  display: flex;
  justify-content: space-between;
  margin-top: 70px;
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
.user-avatar {
  position: absolute;
  right: 25px;
  top: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 500px;
  font-size: 18px;
}
</style>