pipeline {
    agent { label 'docker-agent-01' }
    tools {
        jdk 'Java21'
        maven 'Maven 3'
    }
    stages {
        stage ("cleanup workspace") {
            steps {
                cleanWs()
            }
        }
        stage ("checkout from SCM") {
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/example/register-app.git'
            }
        }
        stage ("build Application") {
            steps {
                sh 'mvn clean package'
            }
        }
         stage ("run tests") {
            steps {
                sh 'mvn test'
            }
        }
    }
}