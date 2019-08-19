//Importing Line class from the vue-chartjs wrapper
import {Line} from 'vue-chartjs'
//Exporting this so it can be used in other components
export default { 
  extends: Line,
  data () {
    return {
      datacollection: {
        //Data to be represented on x-axis
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 
                  'August', 'September', 'October', 'November', 'December'], 
        datasets: [
          {
            label: 'Data 1',
            borderColor: '#f80909',
            //backgroundColor: '#f80909',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            fill: false,
            //Data to be represented on y-axis
            data: [40, 20, 30, 50, 90, 10, 20, 40, 50, 70, 90, 100]
          },
          {
            label: 'Data 2',
            backgroundColor : '#0062ff',
        pointBackgroundColor: '#0062ff',
        pointHoverBackgroundColor: '#0062ff',
        borderColor: '#0062ff',
        pointBorderColor: '#0062ff',
        pointHoverBorderColor: '#0062ff',
        fill: false, /* this option hide background-color */
            data: [50, 30, 20, 10, 70, 40, 40, 80, 80, 40, 20, 90]
          },
          {
            label: 'Data 3',
            backgroundColor: '#187979',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            //Data to be represented on y-axis
            data: [60, 20, 20, 30, 40, 50, 80, 80, 70, 50, 20, 10]
          }
        ]
      },
      //Chart.js options that controls the appearance of the chart
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            },
            gridLines: {
              display: true
            }
          }],
          xAxes: [ {
            gridLines: {
              display: false
            }
          }]
        },
        legend: {
          display: true
        },
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  mounted () {
    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.datacollection, this.options)
  }
}