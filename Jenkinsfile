pipeline {
    agent { label 'docker-agent-01' }

    tools {
        jdk 'Java21'
        maven 'Maven3'
    }
	environment {
			APP_NAME = "register-app-pipeline"
			RELEASE = "1.0.0"
			DOCKER_USER = "abdoshabakat"
			DOCKER_PASS = 'dockerhub'
			IMAGE_NAME = "${DOCKER_USER}" + "/" + "${APP_NAME}"
			IAMGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
	}
    stages {
        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github',
                    url: 'https://github.com/Abdulrahman-Ibnaof/register-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar, **/target/*.war', fingerprint: true
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-server') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }
		stage("Build & Push Docker Image")
			steps {
				script {
					docker.withRegistry('',DOCKER_PASS)
						docker_image = docker.build "${IMAGE_NAME}"
					}
					docker.withRegistry('',DOCKER_PASS) { 
						docker_image.push("${IMAGE_TAG})
						docker_image.push('latest)
					}
				}
    }

    post {
        success {
            echo 'Pipeline completed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }

        always {
            cleanWs()
        }
    }
}