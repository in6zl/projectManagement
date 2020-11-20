<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  // mounted(){
  //   window.addEventListener('beforeunload', e => {
  //     window.localStorage.removeItem('currentUser')
  //   });
  // }
}
</script>
