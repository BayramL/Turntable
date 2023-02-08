<!-- added this ENTIRE page 2/7 -->

<template>
  <div>
      <form >
          <input type="text" placeholder="eventTitle" v-model="event.eventName">
          <textarea placeholder="eventDescription" v-model="event.description"></textarea>
          <button v-on:click.prevent="saveEvent">Save</button>
          <!-- <button v-on:click="goBack">Go Back</button> -->
            <select v-model="selectedDJ">
        <option disabled value="">Select a DJ</option>
         <option v-for="dj in djs" :key="dj" :value="dj.id">{{dj.username}}</option>
      </select>
      </form>
      <!-- <div v-if="error">{{error}}</div> -->
        <div>
    
    </div>
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
            },
           
            selectedDJ:-1,
            djs:[]
            
        };
    },
    created(){
        EventService.getDjs()
        .then(response =>{
            this.djs = response.data;
        })
        
    },
    methods:{
      
        saveEvent(){
            EventService.createEvent(this.event, this.selectedDJ).then(
                (response) =>{
                    console.log("YAYAYAY")
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
        },
      
    }
}
</script>
<style>

</style>