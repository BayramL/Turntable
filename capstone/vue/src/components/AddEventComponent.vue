<!-- added this ENTIRE page 2/7 -->

<template>
  <div>
      <form >
          <input type="text" placeholder="eventTitle" v-model="event.eventName">
          <textarea placeholder="eventDescription" v-model="event.description"></textarea>
          <button v-on:click.prevent="saveEvent">Save</button>
          <!-- <button v-on:click="goBack">Go Back</button> -->
      </form>
      <!-- <div v-if="error">{{error}}</div> -->
  </div>
</template>
<script>
import EventService from "../services/EventService";
export default {
    data(){
        return{
            event: {
                description: "",
                eventName: ""
            }
        };
    },
    methods:{
        // goback(){
        //     this.$router.go(-1)
        // },
        saveEvent(){
            EventService.addEvent(this.event).then(
                (response) =>{
                if(response.status==201){
                    alert("success")
                    this.$router.push("/hostEvents");
                    // this.clearForm();
                }
            })
            .catch(error => {
                console.error("error saving event", error);
                this.error = "An error occurred while saving the event";
            });
        },
        clearForm(){
            this.eventTitle = "";
            this.eventDescription = "";
        }
    }
}
</script>
<style>

</style>