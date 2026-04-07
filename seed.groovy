folder('Todo-App')

['frontend'].each { app ->
    pipelineJob("Todo-App/${app}") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/example/${app}.git")
                        }
                        branch('main')
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
    }
}