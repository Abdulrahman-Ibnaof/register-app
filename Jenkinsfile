
pipeline {
    agent { label 'Jenkins-Agent' }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }
    stages {
        stage("Clean Workspace") {
            steps {
                cleanWs()
            }
        }
        stage("Checkout From SCM") {
                steps {
                git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/Abdulrahman-Ibnaof/register-app'
            }
        }
        stage("Build") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage("Test") {
            steps {
                sh "mvn test"
            }
        }
        stage("sonarQube analysis") {
            steps {
                withSonarQubeEnv(credentialId: 'jenkins-sonarqube-token') {
                    sh "mvn sonar:sonar"
                }
            }
        }
    }
}


