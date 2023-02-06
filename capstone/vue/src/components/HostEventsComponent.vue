<template>
  <div>
      
      <ul v-for="event in events" :key="event.id">
            <li>{{event.name}}</li>            
            <button @click="editEvent(event.id)">edit</button>
      </ul>
      {{events}}
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
                    console.log(response)
                    this.events=response.data;
                }
            );
        }
}
</script>

<style>

</style>