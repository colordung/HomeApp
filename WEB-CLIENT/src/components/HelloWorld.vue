<template>
  <section class="container">
    
    <h1>Demo examples of vue-chartjs</h1>
    <div class="columns">
      <div class="column">
        <h3>Line Chart {{token}}</h3>
        <line-chart></line-chart>
      </div>
    </div>
  </section>
</template>

<script>
import auth from '@/common/auth'
import LineChart from '@/common/line-chart'

export default {
  name: 'HelloWorld',
  components: {
      LineChart
  },
  created: function() {
           this.token = auth.getDate(); 
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      token: '',
    }
  },
  methods: {
    
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
