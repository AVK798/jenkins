def Make_artifacts(App_type, Service) {
    if(App_type == 'Nginx') {
        command = 'zip -r ${Service}.zip *'
        def excute=sh(returnStdout: true, script: command)
            print excute
    } else if(App_type == 'Go') {
        command = 'zip -r ${Service}.zip ${Service}'
        def excute=sh(returnStdout: true, script: command)
            print excute
    } else if(App_type == 'Nodejs') {
        command = 'zip -r ${Service}.zip node_modules server.js'
        def excute=sh(returnStdout: true, script: command)
            print excute
    } else if(App_type == 'Java') {
        command = 'cp target/*.jar ${Servcie}.jar && zip -r ${Service}.zip ${Service}.jar'
        def excute=sh(returnStdout: true, script: command)
            print excute
    }
}

def Code_builds(App_type, Service) {
    if(App_type == 'Go') {
        command = 'go build -o login'
        def excute=sh(returnStdout: true, script: command)
            print excute
    } else if(App_type == 'Nodejs') {
        command = 'npm ci'
        def excute=sh(returnStdout: true, script: command)
            print excute
    } else if(App_type == 'java') {
        command = 'mvn clean package'
        def excute=sh(returnStdout: true, script: command)
            print excute
    }
}
def upload_artifacts(Service) {

  curl -f -v -u admin:nexus123 --upload-file ${Service}.zip  http://172.31.72.40:8081/repository/${Service}/${Service}.zip

}


