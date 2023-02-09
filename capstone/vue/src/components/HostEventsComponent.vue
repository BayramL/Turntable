<template>
<loader-layout>
  <div class="upcomingEvents">
    <div class="user-avatar">
        <div class="user-avatar-image"></div>
        <p> Welcome to the party, {{appUser.username}}!!!</p>
      </div>
       
    <ul v-for="event in events" :key="event.id">

      <div class="eventDiv">
        <li>{{ event.eventName }}</li>

        <button v-on:click.prevent="editEvent(event.eventId)">edit</button>

        <button v-on:click.prevent="removeEvent(event.eventId)">delete</button>
  
      </div>
    </ul>

    
    <!-- added this button below 2/7
        addEventComponent is basically inside this so if you add hostevents component
        it also includes addEventComponent. We can remove this.
      -->

     <button v-on:click.prevent="addEvent" type="submit">Add a party!</button>
  </div>  </loader-layout>
</template>

<script>
import EventService from "../services/EventService";
import LoaderLayout from '../layout/LoaderLayout.vue';
export default {
  components: {  LoaderLayout },

  name: "host-events-component",

  data() {
    return {
      events: [],
      appUser: '',
    };
  },
  created() {
    this.populateEvents();
    this.isLoading = false;
  },
  mounted: function () {
    const user = JSON.parse(window.localStorage.getItem('user'));
    this.appUser = user; 
    },
  methods: {
    editEvent(id) {
      this.$router.push({ name: `editEvent`, params: { id } });
    },
    // added this below 2/7
    addEvent() {
      this.$router.push({ name: "addEvent" });
    },
    removeEvent(eventId) {
      EventService
        .deleteEvent(eventId)
        .then((response) => {
          if (response.status === 200) {
            this.populateEvents();
          }
        })
        .catch((error) => {
          if (error.response.status === 404) {
            this.$router.push("/404");
          } else {
            console.error(error);
          }
        });
    },

    populateEvents() {
      EventService.getAllEvents().then((response) => {
      this.events = response.data;
    });
    }
  },
};

</script>

<style>
.eventDiv {
  background-color: rgba(33, 27, 61, 0.5);
  width: 75%;
  display: flex;
  justify-content: space-between;
  border-radius: 50px;
  margin-bottom: 15px;
  padding: 15px 15px;
  text-align: center;
}

li {
  text-align: center;
  list-style-type: none;
}

.user-avatar{
  position: absolute;
  right: 25px; 
  top: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 400px;
}
/* .user-avatar-image {
  
  height: 50px;
  width: 100px;
  
} */
</style>