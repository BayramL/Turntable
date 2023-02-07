<template>
  <div class="upcomingEvents">
       
      <ul v-for="event in events" :key="event.id">

          <div class="eventDiv">
            <li>{{event.eventName}}</li>            
            <button @click="editEvent(event.id)">edit</button>

         </div>
      </ul>
      </div>
      <!-- {{events}} -->
      
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
        }
}
</script>

<style>

.eventDiv {
    background-color: rgba(33, 27, 61, 0.5);
    width: 75%;
    display: flex;
    justify-content: space-between;
    border-radius: 30px;
    margin-bottom: 15px;
    padding: 20px 20px;

}

li {
    text-align: center;
    list-style-type: none;
    

}
</style>