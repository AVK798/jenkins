// folder('TodoApp')
// ['frontend','login','todo','users'].each { app ->
//     pipelineJob("TodoApp/${app}") {
//         definition {
//             cpsScm {
//                 scm {
//                     git {
//                         remote {
//                             url("https://github.com/AVK798/${app}.git")
//                             refspec('+refs/heads/*:refs/remotes/origin/* +refs/tags/*:refs/tags/*')
//                         }
//                         branches('*/main', 'refs/tags/*')
//                     }
//                 }
//                 scriptPath('Jenkinsfile-Docker')
//             }
//         }

//         triggers {
//             githubPush()              // webhook trigger
//             scm('H/2 * * * *')        // poll every 2 minutes
//         }
//     }
// }
folder('TodoApp-K8S')
['frontend','login','todo','users'].each { app ->
    pipelineJob("TodoApp-K8S/${app}") {
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
                scriptPath('Jenkinsfile-K8S')
            }
        }

    }
}