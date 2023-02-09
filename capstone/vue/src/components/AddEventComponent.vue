<!-- added this ENTIRE page 2/7 -->

<template>
<loader-layout> 
  <div>

      <div class="user-avatar">
        <div class="user-avatar-image"></div>
        <p> Welcome to the party, {{appUser.username}}!</p>
      </div>

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
</loader-layout>
</template>

<script>
import EventService from "../services/EventService";
export default {
  components: { },
    data(){
        return{
            event: {
                description: "",
                eventName: ""
            },
            selectedDJ:-1,
            djs:[],
            appUser: '',
            
        };
    },
    created(){
        EventService.getDjs()
        .then(response =>{
            this.djs = response.data;
        })
        
    },
    mounted: function () {
    const user = JSON.parse(window.localStorage.getItem('user'));
    this.appUser = user; 
    },
    methods:{
        saveEvent(){
            EventService.createEvent(this.event, this.selectedDJ).then(
                (response) =>{
                if(response.status==201){
                    this.$router.push("/hostEvents");
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
.user-avatar{
  position: absolute;
  right: 25px; 
  top: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 500px;
  font-weight: bolder;
}
</style>