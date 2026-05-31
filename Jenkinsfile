pipeline {
    agent { label 'docker-agent-01' }
    tools {
        jdk 'Java21'
        maven 'Maven3'
    }
    stages {
        stage ("cleanup workspace") {
            steps {
                cleanWs()
            }
        }
        stage ("checkout from SCM") {
            steps {
                url: 'https://github.com/Abdulrahman-Ibnaof/register-app.git',
                credentialsId: 'github'
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