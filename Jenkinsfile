
pipeline {
    agent { label 'Jenkins-Agent' }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }
    environment {
            APP_NAME = "register-app"
            RELEASE_VERSION = "1.0.0"
            DOCKER_USER = "abdoshabakat"
            DOCKER_PASSWORD = 'dockerhub'
            IMAGE_NAME = "${DOCKER_USER}/${APP_NAME}"
            IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
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
                withSonarQubeEnv('sonarqube-server') {
                    sh "mvn sonar:sonar"
                }
            }
        }

        stage("Build Docker Image") {
            steps {
                script {
                    docker.withRegistry('','dockerhub') {
                        def docker_image = docker.build("${IMAGE_NAME}") 
                    
                        docker_image.push("${IMAGE_TAG}")
                        docker_image.push("latest")
                }
            }
        }

      }
    }
}

