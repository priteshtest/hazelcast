node {

 stage('git clone') {
      checkout scm
    }
    try {
        stage('compile') {
            sh '/usr/local/bin/mvn compile'
        }
        stage('test') {
            sh '''echo "Unit Tests cases" '''
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        
       currentBuild.result = "SUCCESS" 


    } catch (Throwable t) {
        currentBuild.result = "FAILURE"
        throw t
    }
}