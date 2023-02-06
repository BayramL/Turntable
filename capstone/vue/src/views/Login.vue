<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
     <!--  <router-link :to="{name:'hostEvents'}"><button type = "submit">Sign in</button></router-link> -->

      <button type="submit">Sign in</button> 
    </form>

    <form>
      <label for="eventID" >Event number:</label>
      <input 
      type ="number"
      id="guestLogin"
      placeholder="Your party ID"
      v-model="idNumber"
      />
      <button type="submit" 
      @click="goToPage"
      > Go to event</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
//import EventService from "../services/EventService";
export default {
  name: "login",
  components: {},
  data() {
    return {
      idNumber:"",
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            // this.$router.push("/hostEvents");
             if(response.data.user.role === 'ROLE_DJ'){
               this.$router.push("/djEvents");
            }
             else if(response.data.user.role==='ROLE_HOST'){
              this.$router.push("/hostEvents");
            }
        
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });

    },
    goToPage(){
       this.$router.push({path:`/events/${this.idNumber}`});
      //  EventService.getEvent(this.idNumber).then(
      //     (response) =>{
      //        if (response.status === 200) {
      //          this.$router.push({path:`/events/${this.idNumber}`});
      //        }
         
      //     }
      // );
    }


  }
};
</script>
