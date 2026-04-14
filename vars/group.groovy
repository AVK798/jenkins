def Make_artifacts(App_type, Service) {
    if(App_type == 'Nginx') {
        command = ' echo env && zip -r ${FILENAME}.zip *'
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

// def upload_artifacts(Service) {
//     def name = ""
//     if (env.TAG_NAME) {
//         name = env.TAG_NAME
//     } else {
//         name = env.BRANCH_NAME ?: "main"
//     }
//   //get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
//   def get_branch_exec=sh(returnStdout: true, script: name)
//   def FILENAME=Service+'-'+get_branch_exec+'.zip'
//    command = "curl -f -v -u admin:nexus123 --upload-file ${FILENAME} http://172.31.72.40:8081/repository/${Service}/${FILENAME}"
//    def execute_state=sh(returnStdout: true, script: command)
//   //manager.addShortText("deployed")
// }

def upload_artifacts(Service) {
    def name = ""
    if (env.TAG_NAME) {
        name = env.TAG_NAME
    } else {
        name = env.BRANCH_NAME ?: "main"
    }
    def FILENAME = "${Service}-${name}.zip"
    echo "Artifact Name: ${FILENAME}"
    command = "curl -f -v -u admin:nexus123 --upload-file ${FILENAME} http://100.54.105.237:8081/repository/${Service}/${FILENAME}"
    def execute_state=sh(returnStdout: true, script: command)
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


