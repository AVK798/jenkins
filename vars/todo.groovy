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
        stage ('Make artificats - Nginx') {
           when {
          
           environment name: 'App_type', value: 'Nginx'

           }
         steps {
            sh '''
              zip -r ${Service}.zip *
             '''
            }
        }
        stage ('Download dependencies for - Golang') {
         
         steps {
            sh '''
            go mod tidy
            go build -o login
            '''
         }
        }

        stage ('Make artifacts for Go-lang') {
         when {
            environment name: 'App_type', value: 'login' 
         }
         steps {
            sh '''
            zip -r ${Service}.zip ${Service}

            '''
         }
        }
        stage ('Upload the Artifact to Nexus') {
           steps {
            sh'''
               curl -f -v -u admin:nexus123 --upload-file frontend.zip  http://3.227.249.190:8081/repository/frontend/frontend.zip

             '''
            }
        }   
      }
    }
}