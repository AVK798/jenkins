def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP               : '172.31.14.124',
  ]
  args << params

  // End Default + Required Arguments

  pipeline {
     agent {

      //label "NODES"
      label "${args.Slave}"

     }

     environment {

      Service    = "${args.Service}"
      //Slave      = "${args.Slave}"
      App_type   = "${args.App_type}"

     }
     stages {
        stage ('make artificats - Nginx') {
           when {
          
           environment name: 'App_type', value: 'Nginx'

           }
         steps {
            sh '''
              zip -r ${Service}.zip *
             '''
            }
        }
        stage ('upload the Artifact to Nexus') {
           steps {
            sh'''
               curl -f -v -u admin:nexus123 --upload-file frontend.zip  http://3.227.249.190:8081/repository/frontend/frontend.zip

             '''
            }
        }   
      }
    }
}