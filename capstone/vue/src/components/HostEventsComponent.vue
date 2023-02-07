<template>
    
  <div class="upcomingEvents">
       
      <ul v-for="event in events" :key="event.id">

          <div class="eventDiv">
            <li>{{event.eventName}}</li>            
            <button @click="editEvent(event.id)">edit</button>

         </div>
      </ul>
      <!-- added this button below 2/7
        addEventComponent is basically inside this so if you add hostevents component
        it also includes addEventComponent. We can remove this.
      -->
      <button @click="addEvent" type="submit">Add a party!</button>
      </div>
  
</template>

<script>
import EventService from "../services/EventService"
export default {
        data(){
            return{
                events:[]
            }
        },
        created(){
            EventService.getAllEvents().then(
                (response) =>{
                    this.events=response.data;
                }
            );
        },
        methods: {
            editEvent(id) {
                this.$router.push({name: 'editEvent', params:{id}});
            },
            //added this below 2/7
            addEvent(){
                this.$router.push({name:'addEvent'})
            }
        }
}
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
</style>