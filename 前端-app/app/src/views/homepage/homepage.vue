<template>
  <div id="backg">
    <login></login>
  </div>
</template>

<script>
import login from "@/views/homepage/chil/login";
export default {
  name: "homepage",
  components:{login},
}
</script>

<style scoped>
#backg{
  background: url("../../../public/picture/background.png");
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%;
}
</style>
