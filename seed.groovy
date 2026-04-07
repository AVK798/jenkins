folder('Todo-App')

['frontend','login','todo','users'].each { app ->
    pipelineJob("Todo-App/${app}") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/AVK798/${app}.git")
                        }
                        branch('main')
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
    }
}