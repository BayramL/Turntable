<template>
  <div>
    <form>
      <h3> Edit Event</h3>
      <label for="eventTitle"> Event title: </label>
      <input type ="text" id="eventTitle">
     
      <label for="eventDescription">Event Description:</label>
      <textarea id="eventDescription"></textarea>
    <div class="buttons">
      <button v-on:click.prevent="saveEvent">Save</button>
      <button @click="addHost">Add Host</button>
    </div>
  
    </form>
  </div>
</template>

<script>
import EventService from "../services/EventService";
export default {
  data() {
    return {
        events: []
    };
  },

  methods: {
    saveEvent() {
      EventService.updateEvent(this.event).then((response) => {
        if (response.status === 201) {
          this.$router.push({ name: "hostEvents" });
        }
      });
    }
  },

  created() {
    EventService.updateEvent(this.eventId, this.event).then((response) => {
      if(response.status === 201) {
          this.$router.push({name: 'hostEvents'});
      }
    });
  },
  
    

};
</script>

<style>
.edit-event {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  margin: 10px 0;
}

input,
textarea {
  width: 50%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid gray;
  border-radius: 5px;
}

.buttons {
  display: flex;
  justify-content: space-around;
  width: 50%;
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  border-radius: 5px;
  border: 1px solid gray;
  background-color: lightgray;
  cursor: pointer;
}
</style>