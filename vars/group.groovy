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


