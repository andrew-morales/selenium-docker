pipeline {
    // master executor should be set to 0
    //test 1
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //sh
                sh "mvn clean package -DskipTests"
                args '-v $HOME/.m2:/root/.m2'
            }
        }
        stage('Build Image') {
            steps {
                //sh
                sh "docker build -t='asmorales/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push asmorales/selenium-docker:latest"
			    }                           
            }
        }
    }
}