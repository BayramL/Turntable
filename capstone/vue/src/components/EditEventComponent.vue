<template>
  <div>
      <edit-event-component></edit-event-component>
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
    //   EventService.updateEvent(this.eventId, this.events).then(
    //       (response) => {
    //           if(response.status === 201) {}
    //       }
    //   )
      
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
</style>