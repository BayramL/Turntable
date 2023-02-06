import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
//use axios.get instead?
export default {
    
   getEvent(id) {
       return http.get(`/events/${id}`);
   },
   getAllEvents() {
       return http.get('/events/all-events');
   },
   createEvent(event) {
       return http.post('/events/newEvent', event)
   },
   updateEvent(id, event) {
        return http.put(`/events/update-event/${id}`, event);
   },
   deleteEvent(id) {
       return http.delete(`/events/delete-event/${id}`);
   }

}