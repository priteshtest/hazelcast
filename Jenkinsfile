node('master') {
    stage('git clone') {
      checkout scm
    }

    try {
        
        //Runs the standard set of build scripts assuming they are in the /scripts folder in the repo.
        runBuildScripts {}

        if (env.BRANCH_NAME == 'master') {
            stage('Maven') {
            
                sh 'echo  "Maven build"'
                println(currentBuild.rawBuild.project.displayName)
            }

          
        }
    
    } catch (Throwable t) {
        error('Build Failure: ${t.message}\n${env.BUILD_URL}consoleText')


        throw t
    }
}
