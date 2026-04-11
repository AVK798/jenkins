def Make_artifacts(App_type, Service) {

    if(App_type == 'Nginx') {
        command = 'zip -r ${Service}.zip *'
        def excute=sh(returnStdout: true, script: command)
        print excute
    }
}

