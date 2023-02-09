<template>

  <div>
    <header>
      <!-- <img src="../assets/turntableLogo-v2.png" alt ="turntable logo" class ="turntable-logo"/> -->
    
      <!-- <div class="user-avatar">
        <div class="user-avatar-image"></div>
        <p>{{appUser.username}}</p>
      </div> -->
    </header>

    <main>
      <slot v-if="showLoader === false" />
      <loading-gif v-if="showLoader === true"> </loading-gif>
    </main>
    <footer> </footer>
  </div>
</template>

<script>

import LoadingGif from '../components/LoadingGif.vue';
export default {
 components: { LoadingGif },
 data(){
   return {
     showLoader: true,
    //  appUser: ''
   }
 },

 mounted: function () {
    // const user = JSON.parse(window.localStorage.getItem('user'));
    // this.appUser = user;

    window.setTimeout(() => {
      this.showLoader = false;
    }, 2500);
  },
};
</script>
<style>
/* .user-avatar{
  position: absolute;
  right: 25px; 
  top: 25px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100px;
}
.user-avatar-image {
  background-color: white;
  height: 50px;
  width: 50px;
  border-radius: 50%;
} */
</style>
