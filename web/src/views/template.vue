<template>
  <div class="table_container">
    <div class="filter-container">
      首页
    </div>
  </div>
</template>
<style scoped>

</style>
<script>
  export default {
    components: {},
    directives: {},
    computed: {},
    methods: {},
    data() {
      return {}
    },
    created() {
      console.log("【view created】->" + this.$route.path)
    },
    mounted() {
      console.log("【view mounted】->" + this.$route.path)
    },
    destroyed() {
      console.log("【view destroyed】->" + this.$route.path)
    }
  }
</script>
