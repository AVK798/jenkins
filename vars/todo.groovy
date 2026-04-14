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
        // stage ('Make artificats') {
        
       
        // //    when {
        // //    environment name: 'App_type', value: 'Nginx'

        // //    }
        //   steps {
        //         script {

        //          prepare = new group()
        //          prepare.Make_artifacts("${App_type}","${Service}")
        //         }
        // //     sh '''
        // //       zip -r ${Service}.zip *
        // //      '''
        //      }
        // }
      //   stage ('Download dependencies for - Golang') {
      //     when {
      //       environment name: 'App_type', value: 'Go' 
      //    }
      //    steps {
      //       sh '''
      //       go mod tidy
      //       go build -o login
      //       '''
      //    }
      //   }

        // stage ('Make artifacts for Go-lang') {
        //  when {
        //     environment name: 'App_type', value: 'login' 
        //  }
        //  steps {
        //     sh '''
        //     zip -r ${Service}.zip ${Service}

        //     '''
        //  }
        // }
      //   stage ("Download Dependices for NodeJs") {
      //    when {
      //           environment name: 'App_type', value: 'Nodejs'
      //       }
      //    steps {

      //       sh '''
      //         npm ci
      //       '''
      //    }
      //   }
        // stage ('make artificats for todo') {
        //     when {
        //         environment name: 'App_type', value: 'todo'
        //     }
        //  steps {
        //     sh '''
        //     zip -r ${Service}.zip node_modules server.js
        //     '''
        //  }
        // }

      //   stage ("mvn compile for JAVA") {
      //       when {
      //           environment name: 'App_type', value: 'users'
      //       }
      //       steps {
      //          sh '''
      //            mvn compile
      //          '''
      //       }
      //    }

      //    stage("mvn package") {
      //     when {
      //           environment name: 'App_type', value: 'users'
      //       }
      //        steps {

      //          sh '''
      //           mvn package
      //          '''
      //        }
      //    }
        // stage ('make artificats for users') {
        //      when {
        //         environment name: 'App_type', value: 'users'
        //     }
        //  steps {
        //     sh '''
        //     cp target/*.jar ${Servcie}.jar
        //     zip -r ${Service}.zip ${Service}.jar 
        //     '''
        //  }
        // }
          stage ('Code build & install dependinces ') {
          steps {
                script {
                 build = new group()
                 build.Code_builds("${App_type}","${Service}")
                }
             }
        }
         stage ('Make artificats') {
          steps {
                script {

                 Make = new group()
                 Make.Make_artifacts("${App_type}","${Service}")
                }
             }
        }
        stage ('Upload the Artifact to Nexus') {
          steps {
                script {

                 upload = new group()
                 upload.upload_artifacts("${Service}")
                }
             }
          //  steps {
          //   sh'''
          //      curl -f -v -u admin:nexus123 --upload-file frontend.zip  http://3.227.249.190:8081/repository/frontend/frontend.zip

          //    '''
          //   }
        }   
      }
    }
}