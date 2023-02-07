<!-- added this ENTIRE page 2/7 -->

<template>
  <div>
      <form>
          <input type="text" placeholder="eventTitle" v-model="eventTitle">
          <textarea placeholder="eventDescription" v-model="eventDescription"></textarea>
          <button @click="saveEvent">Save</button>
          <button @click="goBack">Go Back</button>
      </form>
      <div v-if="error">{{error}}</div>
  </div>
</template>
<script>
import EventService from "../services/EventService";
export default {
    data(){
        return{
            eventTitle:"",
            eventDescription:"",
            error: ""
        };
    },
    methods:{
        goback(){
            this.$router.go(-1)
        },
        saveEvent(){
            EventService.addEvent({
                eventName: this.eventTitle,
                eventDescription: this.eventDescription
            })
            .then(response=>{
                if(response.status===201){
                    this.$router.push({name:"hostEvents"});
                    this.clearForm();
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