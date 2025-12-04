pipeline {
    agent any
    tools {

        maven 'Maven'
        jdk 'JDK_17'
    }

    environment {
        SCANNER_TOKEN = credentials('tokenn')
        APP_NAME = 'ventas-bolivar'
        APP_PORT = '8000' 
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/xxrobert666xx-bot/Repositorio.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Iniciando compilación y pruebas con Maven...'
                sh 'mvn clean package'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
               withSonarQubeEnv('Sonarqube_jenkins') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=my-java-app -Dsonar.login=$SCANNER_TOKEN"
               }
            }
        }
                    
        stage('Quality Gate Check') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true 
                }
            }
        }

        stage('Build & Run Docker Container') {
            agent {
                docker {
                    image 'docker:latest'
                    args '-v /var/run/docker.sock:/var/run/docker.sock' 
                }
            }
            steps {
                script {
                    def imageTag = "${env.APP_NAME}:${env.BUILD_NUMBER}"
                    def containerName = "${env.APP_NAME}-container"
                    echo "Building image: ${imageTag}"
                    sh "docker build -t ${imageTag} ."
                    echo "Checking for old container: ${containerName}"
                    sh "docker rm -f ${containerName} || true"
                    sh "docker run -d -p 8090:${env.APP_PORT} --name ${containerName} ${imageTag}"
                    echo "Application deployed and running on http://localhost:8090"
                }
            }
        }
    } 

    post {
        always {
            echo 'Pipeline finalizada. Revisar estado.'
        }
        success {
            echo 'CI/CD completado exitosamente.'
        }
        failure {
            echo '¡ERROR! La etapa de despliegue falló.'
        }
    }

}






