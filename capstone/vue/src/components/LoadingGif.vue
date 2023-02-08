<template>
  <div class = "loading" v-if="isLoading">

      <img src ="../assets/Party.gif" />
      
 
  </div>
</template>

<script>
export default {
    data(){
        return{
            isLoading: true,
        }
    }

}
</script>

<style>

</style>