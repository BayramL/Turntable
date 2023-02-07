import axios from "axios";


//use axios.get instead?
export default {
    
   getEvent(id) {
       return axios.get(`/events/${id}`);
   },
   getAllEvents() {
       return axios.get('/events/all-events');
    },
   createEvent(event) {
       return axios.post('/events/newEvent', event)
   },
   updateEvent(id, event) {
        return axios.put(`/events/update-event/${id}`, event);
   },
   deleteEvent(id) {
       return axios.delete(`/events/delete-event/${id}`);
   },
 
   //added code below 2/7
   addEvent(event){
       return axios.post('/events/newEvent',event);
   }

}