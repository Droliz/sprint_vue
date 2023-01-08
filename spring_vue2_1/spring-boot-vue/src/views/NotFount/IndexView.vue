<template>
<div class="main">
  <h1>404</h1>
  <h2>Sh*t!</h2>
  <p class="text">
    Looks like the page you're looking for has been <i>stoolen</i>.<br />
    I hope it didn't get <i>dumped</i> somewhere we can't find.<br />
    It will return in <strong><i>{{s}}</i></strong> seconds!
  </p>
</div>
</template>

<script>
export default {
  name: "IndexView",
  data() {
    return {
      s: 3
    }
  },
  mounted() {
    const it = setInterval(() => {
      if (this.s === 0) {
        clearInterval(it)
        this.$router.go(-1)
      } else {
        this.s--
      }
    }, 1000)
  }
}
</script>

<style scoped>
@import "./css";
</style>