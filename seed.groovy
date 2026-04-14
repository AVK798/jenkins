folder('Todo-App')

['frontend','login','todo','users'].each { app ->
    pipelineJob("Todo-App/${app}") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/AVK798/${app}.git")
                            refspec('+refs/heads/*:refs/remotes/origin/* +refs/tags/*:refs/tags/*')
                        }
                        branches('*/main', 'refs/tags/*')
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }

        triggers {
            githubPush()              // webhook trigger
            scm('H/2 * * * *')        // poll every 2 minutes
        }
    }
}