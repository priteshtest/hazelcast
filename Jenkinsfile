env.DOCKER_IMAGE = "pritesh"

pipeline {
    agent {
        label 'master' 
    }
    
    stages {
        stage('Example Deploy') {
            when {
                branch 'production'
            }
            steps {
                echo 'Deploying'
            }
    }  
        
        stage('Run Tests') {
            steps{ 
                sh 'echo "runScripts {}"'
            }
        }
    }
    
    post { 
        failure { 
            echo 'Build failed! :('
        }
        success { 
            echo 'Build Pass.'
        }
    }
    
}
        
        
// Scripted pipeline
/*
node('master') {
    stage('git clone') {
      checkout scm
    }

    try {
        
        //Runs the standard set of build scripts assuming they are in the /scripts folder in the repo.
        //if (env.JOB_BASE_NAME.contains('PR-') || env.JOB_BASE_NAME.contains('master')) {
        //  runBuildScripts {}
       // }
        
        //bakeImage "DockerImage"
        
        if (env.BRANCH_NAME == 'master') {
            stage('Maven') {
            
                sh 'echo  "Maven build"'
                def myVar = env.JOB_NAME
                println(myVar)
            }
            
         

          
        }
    
    } catch (Throwable t) {
        error('Build Failure: consoleText')


        throw t
    }
} */
