node('master') {
    stage('git clone') {
      checkout scm
    }

    try {
        
        //Runs the standard set of build scripts assuming they are in the /scripts folder in the repo.
        runBuildScripts {}

        if (env.BRANCH_NAME == 'master') {
            stage('Maven') {
            
                sh 'echo  "Maven"'
                
            }
             dockerBuildPushAndClean 'abc:5000/' + env.JOB_NAME + ':' + env.BUILD_NUMBER
          
        }
    
    } catch (Throwable t) {
        error('Build Failure: ${env.JOB_NAME}: ${t.message}\n${env.BUILD_URL}consoleText')


        throw t
    }
}
