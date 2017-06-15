node {
    stage('git clone') {
      checkout scm
    }

    try {
        stage('Build')
        {
           echo 'Build stage'
        }

        stage('Test') 
       {
          echo 'Test stage'
     
        }
        if (env.BRANCH_NAME == 'master') {
            echo 'This happens only on master'
            stage('bake') {

                 echo'Bake Stage'

            }
        }
        println('Build Success')
        
        echo 'slackSend Success Message: Build OK: ${env.JOB_NAME}\n${env.BUILD_URL}' 

    } catch (Throwable t) {
        error('Build Failure: ${env.JOB_NAME}: ${t.message}\n${env.BUILD_URL}consoleText')
        throw t
    }
    
}
